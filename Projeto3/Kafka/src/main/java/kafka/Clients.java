package kafka;

import Models.Currency;
import Models.TopicSendClient;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import com.google.gson.Gson;

public class Clients {
    public static void main(String[] args) throws Exception {
        //Assign topicName to string variable
        String topicName1 = "Credits";
        String topicName2 = "Payments";
        // create instance for properties to access producer configs
        Properties props = new Properties();
        //Assign localhost id
        props.put("bootstrap.servers", "localhost:9092");
        //Set acknowledgements for producer requests.
        props.put("acks", "all");
        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);
        //Specify buffer size in config
        props.put("batch.size", 16384);
        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);
        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);

        //MUDAR DEPOIS QND TIVERMOS UM BD

        ArrayList<Models.Clients> clients = new ArrayList<>();
        clients.add(new Models.Clients(1,"Joao",0,0));
        clients.add(new Models.Clients(2,"Andre",0,0));
        clients.add(new Models.Clients(3,"Mariana",0,0));
        clients.add(new Models.Clients(4,"Rita",0,0));

        ArrayList<Models.Currency> currencies = new ArrayList<>();
//        currencies.add(new Models.Currency("USD",0.89));
//        currencies.add(new Models.Currency("Pounds",1.17));
//        currencies.add(new Models.Currency("Euro",1));
//        currencies.add(new Models.Currency("Rupia",84.73));
        currencies.add(new Models.Currency("USD",1));
        currencies.add(new Models.Currency("Pounds",1));
        currencies.add(new Models.Currency("Euro",1));
        currencies.add(new Models.Currency("Rupia",1));

        while (true) {

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
                System.out.println(clients1.getId()+"->"+jsonStr);
                producer.send(new ProducerRecord<String, String>(topicName1, Integer.toString(clients1.getId()),
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
                producer.send(new ProducerRecord<String, String>(topicName2, Integer.toString(clients1.getId()),
                        jsonStr));
            }
            Thread.sleep(5000);
        }
        //producer.close();
    }
}
