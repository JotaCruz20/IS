package streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Properties;

public class KafkaStreams {

    public static Double getEuro(String data,String debug) {
        JSONObject json = new JSONObject(data);
        //System.out.println(debug+ " "+ data);
        return json.getDouble("value") / json.getDouble("exchangeRate");
    }

    public static String jsonForBD(String column, Double value,String key){
        return "{\"schema\":{\"type\":\"struct\",\"fields\":"+
                "["+
                "{\"type\":\"int64\",\"optional\":false,\"field\":\"id\"},"+
                "{\"type\":\"double\",\"optional\":false,\"field\":\""+column+"\"}"+
                "],"+
                "\"optional\":false},"+
                "\"payload\":{\"id\":"+key+",\""+column+"\":"+value+"}}";
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        String topicName1 = "Credits";
        String topicName2 = "Payments";
        String outTopicClient = "client";
        String outTopicTotals = "totals";
        java.util.Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "projeto");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> lines = builder.stream(topicName1);
        KStream<String, String> lines1 = builder.stream(topicName2);

        KTable<String, Double> creditsPerClient = lines
                .map((k, v) -> new KeyValue<>(k, getEuro(v,"Credit")))
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Double()))  //perguntar ao eurico oq isto faz
                .reduce(Double::sum);

        KTable<String, Double> paymentsPerClient = lines1
                .map((k, v) -> new KeyValue<>(k, getEuro(v,"Payment")))
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Double())) //perguntar ao eurico oq isto faz
                .reduce(Double::sum);

        KTable<String, Double> allCredits = lines
                .mapValues( v -> getEuro(v,"Total"))
                .groupBy((k,v) -> "1", Grouped.with(Serdes.String(), Serdes.Double())) //definir que a chave é a mesma para todas
                .reduce(Double::sum);

        KTable<String, Double> allPayments = lines1
                .mapValues( v -> getEuro(v,"Total"))
                .groupBy((k,v) -> "1", Grouped.with(Serdes.String(), Serdes.Double())) //definir que a chave é a mesma para todas
                .reduce(Double::sum);


        ValueJoiner<Double, Double, Double> balancoTotal = (left, right) -> right - left;

        creditsPerClient.toStream().mapValues((k, v) ->jsonForBD("credit",v,k)).to(outTopicClient);

        paymentsPerClient.mapValues((k, v) -> jsonForBD("payment",v,k)).toStream().to(outTopicClient);

        creditsPerClient.join(paymentsPerClient,balancoTotal).mapValues((k, v) -> jsonForBD("balance",v,k)).toStream().to(outTopicClient);

        allCredits.toStream().mapValues((k, v) ->jsonForBD("credit",v,k)).to(outTopicTotals);
        allPayments.mapValues((k, v) ->jsonForBD("payment",v,k)).toStream().to(outTopicTotals);
        allCredits.join(allPayments,balancoTotal).mapValues((k, v) -> jsonForBD("balance",v,k)).toStream().to(outTopicTotals);

        org.apache.kafka.streams.KafkaStreams streams = new org.apache.kafka.streams.KafkaStreams(builder.build(), props);
        streams.start();
        System.out.println("Reading stream from topic " + topicName1);
    }
}
