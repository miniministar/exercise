package kafka.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

public class Producer extends Thread{
    KafkaProducer<Integer, String> producer;
    String topic;

    public Producer(String topic) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:2181");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "producer");
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "");

        this.topic = topic;

    }
}
