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

    public static void main(String[] args) throws InterruptedException, IOException {
        String topicName1 = "Credits";
        String topicName2 = "Payments";
        String outtopicname = "Results";
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
                .map((k,v) -> new KeyValue<>("0", getEuro(v,"Total"))) //definir que a chave é a mesma para todas
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Double()))
                .reduce(Double::sum);

        KTable<String, Double> allPayments = lines1
                .map((k,v) -> new KeyValue<>("0", getEuro(v,"Total"))) //definir que a chave é a mesma para todas
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Double()))
                .reduce(Double::sum);


        ValueJoiner<Double, Double, Double> balancoTotal = (left, right) -> {
            return right - left;
        };

        creditsPerClient.toStream().mapValues((k, v) -> k + "- Credits->" + v).to(outtopicname);

        paymentsPerClient.mapValues((k, v) -> k + "- Payments->" + v).toStream().to(outtopicname);

        paymentsPerClient.join(creditsPerClient,balancoTotal).mapValues((k, v) -> k + "Balance->" + v).toStream().to(outtopicname);

        allCredits.toStream().mapValues((k, v) ->"- Total Credits->" + v).to(outtopicname);
        allPayments.mapValues((k, v) ->"Total Payments->" + v).toStream().to(outtopicname);
        allCredits.join(allPayments,balancoTotal).mapValues((k, v) -> "Total Balance->" + v).toStream().to(outtopicname);

        org.apache.kafka.streams.KafkaStreams streams = new org.apache.kafka.streams.KafkaStreams(builder.build(), props);
        streams.start();
        System.out.println("Reading stream from topic " + topicName1);
    }
}
