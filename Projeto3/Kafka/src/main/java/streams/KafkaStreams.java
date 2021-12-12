package streams;

import Models.CustomSerdes;
import Models.Mapper;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.StreamsUncaughtExceptionHandler;
import org.apache.kafka.streams.kstream.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class KafkaStreams {

    public static Double getEuro(String data, String debug, String key) {
        JSONObject json = new JSONObject(data);
        //System.out.println(debug + " " + key +"->" + data);
        return json.getDouble("value") / json.getDouble("exchangeRate");
    }

    public static int getAdminId(String data, String debug) {
        JSONObject json = new JSONObject(data);
        return json.getInt("adminID");
    }

    public static String jsonForBD(String column, Double value, String key) {
        return "{\"schema\":{\"type\":\"struct\",\"fields\":" +
                "[" +
                "{\"type\":\"int64\",\"optional\":false,\"field\":\"id\"}," +
                "{\"type\":\"double\",\"optional\":false,\"field\":\"" + column + "\"}" +
                "]," +
                "\"optional\":false}," +
                "\"payload\":{\"id\":" + key + ",\"" + column + "\":" + value + "}}";
    }

    public static String jsonForBDCounter(String column, int value, String key) {
        return "{\"schema\":{\"type\":\"struct\",\"fields\":" +
                "[" +
                "{\"type\":\"int64\",\"optional\":false,\"field\":\"id\"}," +
                "{\"type\":\"int64\",\"optional\":false,\"field\":\"" + column + "\"}" +
                "]," +
                "\"optional\":false}," +
                "\"payload\":{\"id\":" + key + ",\"" + column + "\":" + value + "}}";
    }

    public static String jsonForBDDebt(String column, String value, String key) {
        return "{\"schema\":{\"type\":\"struct\",\"fields\":" +
                "[" +
                "{\"type\":\"int64\",\"optional\":false,\"field\":\"id\"}," +
                "{\"type\":\"int64\",\"optional\":false,\"field\":\"" + column + "\"}" +
                "]," +
                "\"optional\":false}," +
                "\"payload\":{\"id\":" + key + ",\"" + column + "\":" + value + "}}";
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Logger log = LoggerFactory.getLogger(KafkaStreams.class);

        String topicName1 = "Credits";
        String topicName2 = "Payments";
        String outTopicClient = "client";
        String outTopicTotals = "totals";
        String outTopicDebug = "Results";
        java.util.Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "proj3-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass()); //MUDAR PARA STRING
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> lines = builder.stream(topicName1);
        KStream<String, String> lines1 = builder.stream(topicName2);

        // CREDITS PER CLIENT
        KTable<String, Double> creditsPerClient = lines
                .mapValues((k, v) -> getEuro(v, "Credit", k))
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Double()))
                .reduce(Double::sum);

        creditsPerClient.mapValues((k, v) -> jsonForBD("credit", v, k)).toStream().to(outTopicClient);

        //PAYMENTS PER CLIENT
        KTable<String, Double> paymentsPerClient = lines1
                .mapValues((k, v) -> getEuro(v, "Payment", k))
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Double()))
                .reduce(Double::sum);

        paymentsPerClient.mapValues((k, v) -> jsonForBD("payment", v, k)).toStream().to(outTopicClient);

        //ALL CREDITS
        KTable<String, Double> allCredits = lines
                .mapValues((k,v) -> getEuro(v, "Total", k))
                .groupBy((k, v) -> "1", Grouped.with(Serdes.String(), Serdes.Double())) //definir que a chave é a mesma para todas
                .reduce(Double::sum);
        allCredits.toStream().mapValues((k, v) -> jsonForBD("credit", v, k)).to(outTopicTotals);

        //ALL PAYMENTS
        KTable<String, Double> allPayments = lines1
                .mapValues((k,v) -> getEuro(v, "Total", k))
                .groupBy((k, v) -> "1", Grouped.with(Serdes.String(), Serdes.Double())) //definir que a chave é a mesma para todas
                .reduce(Double::sum);
        allPayments.mapValues((k, v) -> jsonForBD("payment", v, k)).toStream().to(outTopicTotals);

        //BALANCE PER CLIENT
        ValueJoiner<Double, Double, Double> balancoTotal = (left, right) -> right - left;
        KTable<String, Double> balancePerClient = creditsPerClient.join(paymentsPerClient, balancoTotal);
        balancePerClient.mapValues((k, v) -> jsonForBD("balance", v, k)).toStream().to(outTopicClient);

        //ALL BALANCE
        allCredits.join(allPayments, balancoTotal).mapValues((k, v) -> jsonForBD("balance", v, k)).toStream().to(outTopicTotals);

        //HIGHEST DEBT
        balancePerClient
                .toStream()
                .map((k,v) -> new KeyValue<>("-1", new Mapper(k,v)))
                .groupByKey(Grouped.with(Serdes.String(), CustomSerdes.Mapper()))
                .aggregate(() -> new Mapper("-1",Double.MAX_VALUE),
                        (key, value, aggregate) -> {
                            if(value.getValue() < aggregate.getValue()){
                                return new Mapper(value.getKey(), value.getValue());
                            }
                            else{
                                return aggregate;
                            }
                        }, Materialized.with(Serdes.String(), CustomSerdes.Mapper()))
                .mapValues((k, v) -> {
                    //System.out.println(v.getKey()+"->"+v.getValue());
                    return jsonForBDDebt("highestdebt_id", v.getKey(), "1");
                })
                .toStream()
                .to(outTopicTotals);

        //ADMIN WITH MOST PAYMENTS
        KTable<String, Double> paymentsPerAdmin = lines1
                .map((k, v) -> new KeyValue<>(Integer.toString(getAdminId(v, "admin")), getEuro(v, "Payment", k)))
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Double()))
                .reduce(Double::sum);

        paymentsPerAdmin
                .toStream()
                .map((k,v) -> new KeyValue<>("-1", new Mapper(k,v)))
                .groupByKey(Grouped.with(Serdes.String(), CustomSerdes.Mapper()))
                .aggregate(() -> new Mapper("-1",Double.MAX_VALUE),
                        (key, value, aggregate) -> {
                            if(value.getValue() < aggregate.getValue()){
                                return new Mapper(value.getKey(), value.getValue());
                            }
                            else{
                                return aggregate;
                            }
                        }, Materialized.with(Serdes.String(), CustomSerdes.Mapper()))
                .mapValues((k, v) -> {
                    //System.out.println(v.getKey()+"->"+v.getValue());
                    return jsonForBDDebt("highestrevenue_id", v.getKey(), "1");
                })
                .toStream()
                .to(outTopicTotals);


        //LAST MONTH BILL
        KTable<Windowed<String>, Double> creditBill = lines
                .mapValues((k,v) -> getEuro(v,"bill", k))
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Double()))
                .windowedBy(TimeWindows.of(Duration.ofMinutes(1)))
                .reduce(Double::sum, Materialized.as("billCredit"));

        KTable<Windowed<String>, Double> paymentBill = lines1
                .mapValues((k,v) -> getEuro(v,"bill", k))
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Double()))
                .windowedBy(TimeWindows.of(Duration.ofMinutes(1)))
                .reduce(Double::sum, Materialized.as("paymentCredit"));

        creditBill.join(paymentBill, balancoTotal).mapValues((k, v) -> jsonForBD("lastmonthbill", v, k.key())).toStream().to(outTopicClient);

        //COUNT PAYMENTS
        KTable<Windowed<String>, Long> paymentCounter = lines1
                .mapValues((k,v) -> getEuro(v,"bill", k))
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Double()))
                .windowedBy(TimeWindows.of(Duration.ofMinutes(30)))
                .count();

        paymentCounter.mapValues((k, v) -> jsonForBDCounter("numofpayments", Math.toIntExact(v), k.key())).toStream().to(outTopicClient);



        //START STREAMS
        org.apache.kafka.streams.KafkaStreams streams = new org.apache.kafka.streams.KafkaStreams(builder.build(), props);
        streams.setUncaughtExceptionHandler(ex -> {
            //log.error("Kafka-Streams uncaught exception occurred. Stream will be replaced with new thread", ex);
            return StreamsUncaughtExceptionHandler.StreamThreadExceptionResponse.REPLACE_THREAD;
        });

        streams.cleanUp();
        streams.start();

        System.out.println("Reading stream from topic " + topicName1);
    }
}
