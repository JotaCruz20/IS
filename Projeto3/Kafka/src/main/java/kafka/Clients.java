package kafka;

import Models.Currency;
import Models.TopicSendClient;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import com.google.gson.Gson;
import org.json.JSONObject;

public class Clients {
    public static void main(String[] args) throws Exception {
        //Assign topicName to string variable
        String topicNameConsumer1 = "DBInfo";
        String topicNameConsumer2 = "DBInfoCurrency";
        String topicNameProduce1 = "Credits";
        String topicNameProduce2 = "Payments";
        // create instance for properties to access producer configs
        Properties propsProducer = new Properties();
        //Assign localhost id
        propsProducer.put("bootstrap.servers", "localhost:9092");
        //Set acknowledgements for producer requests.
        propsProducer.put("acks", "all");
        //If the request fails, the producer can automatically retry,
        propsProducer.put("retries", 0);
        //Specify buffer size in config
        propsProducer.put("batch.size", 16384);
        //Reduce the no of requests less than 0
        propsProducer.put("linger.ms", 1);
        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        propsProducer.put("buffer.memory", 33554432);
        propsProducer.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        propsProducer.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(propsProducer);

        Properties propsConsumer = new Properties();
        //Assign localhost id
        propsConsumer.put("bootstrap.servers", "localhost:9092");
        //Set acknowledgements for producer requests.
        propsConsumer.put("acks", "all");
        //If the request fails, the producer can automatically retry,
        propsConsumer.put("retries", 0);
        //Specify buffer size in config
        propsConsumer.put("batch.size", 16384);
        //Reduce the no of requests less than 0
        propsConsumer.put("linger.ms", 1);
        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        propsConsumer.put("buffer.memory", 33554432);
        propsConsumer.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleConsumer1");
        propsConsumer.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        propsConsumer.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");

        Properties propsConsumer2 = new Properties();
        //Assign localhost id
        propsConsumer2.put("bootstrap.servers", "localhost:9092");
        //Set acknowledgements for producer requests.
        propsConsumer2.put("acks", "all");
        //If the request fails, the producer can automatically retry,
        propsConsumer2.put("retries", 0);
        //Specify buffer size in config
        propsConsumer2.put("batch.size", 16384);
        //Reduce the no of requests less than 0
        propsConsumer2.put("linger.ms", 1);
        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        propsConsumer2.put("buffer.memory", 33554432);
        propsConsumer2.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleConsumer2");
        propsConsumer2.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        propsConsumer2.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");

        Consumer<String, String> consumerClients = new KafkaConsumer<>(propsConsumer);
        Consumer<String, String> consumerCurrencies = new KafkaConsumer<>(propsConsumer2);

        consumerClients.subscribe(Collections.singletonList(topicNameConsumer1));
        consumerCurrencies.subscribe(Collections.singletonList(topicNameConsumer2));



        while (true) {
            ArrayList<Models.Clients> clients = new ArrayList<>();
            ArrayList<Models.Currency> currencies = new ArrayList<>();

            System.out.println("LER Clients");
            ConsumerRecords<String, String> records = consumerClients.poll(Long.MAX_VALUE);
            for (ConsumerRecord<String, String> record : records) {
                //System.out.println(record.key() + " => " + record.value());
                JSONObject json = new JSONObject(record.value());
                String name = json.getJSONObject("payload").getString("name");
                Double credit = json.getJSONObject("payload").getDouble("credit");
                Double payment = json.getJSONObject("payload").getDouble("payment");
                int admin_id = json.getJSONObject("payload").getInt("admin_id");
                int client_id = json.getJSONObject("payload").getInt("id");
                Models.Clients c = new Models.Clients(client_id,name,credit,payment,admin_id);
                System.out.println(c);
                clients.add(c);
            }

            System.out.println("LER CURRENCIES");
            ConsumerRecords<String, String> records2 = consumerCurrencies.poll(Long.MAX_VALUE);
            for (ConsumerRecord<String, String> record : records2) {
                JSONObject json = new JSONObject(record.value());
                String name = json.getJSONObject("payload").getString("name");
                Double credit = json.getJSONObject("payload").getDouble("exchangerate");
                Currency c = new Currency(name,credit);
                System.out.println(c);
                currencies.add(c);
            }
            int r = ThreadLocalRandom.current().nextInt(5,10);

            //Credits
            for (int i = 0; i < r; i++) {
                int r1 = ThreadLocalRandom.current().nextInt(0, currencies.size());
                Currency c = currencies.get(r1);

                int r2 = ThreadLocalRandom.current().nextInt(0, clients.size());
                Models.Clients clients1 = clients.get(r2);

                double d = ThreadLocalRandom.current().nextDouble(0,100);

                TopicSendClient topicSendClient = new TopicSendClient(c.getExchangeRate(),d);

                String jsonStr = new Gson().toJson(topicSendClient);
                System.out.println("Credit: "+clients1.getId()+"->"+jsonStr);
                producer.send(new ProducerRecord<String, String>(topicNameProduce1, Integer.toString(clients1.getId()),
                        jsonStr));
            }

            // Payments
            r = ThreadLocalRandom.current().nextInt(1,10);
            for (int i = 0; i < r; i++) {
                int r1 = ThreadLocalRandom.current().nextInt(0, currencies.size());
                Currency c = currencies.get(r1);

                int r2 = ThreadLocalRandom.current().nextInt(0, clients.size());
                Models.Clients clients1 = clients.get(r2);

                double d = ThreadLocalRandom.current().nextDouble(0,100);

                TopicSendClient topicSendClient = new TopicSendClient(c.getExchangeRate(),d);

                String jsonStr = new Gson().toJson(topicSendClient);
                System.out.println("Payment: "+clients1.getId()+"->"+jsonStr);
                producer.send(new ProducerRecord<String, String>(topicNameProduce2, Integer.toString(clients1.getId()),
                        jsonStr));
            }
            Thread.sleep(50000);
        }
        //producer.close();
    }
}
