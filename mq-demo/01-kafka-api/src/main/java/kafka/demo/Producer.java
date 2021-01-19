package kafka.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Producer extends Thread{
    KafkaProducer<Integer, String> producer;
    String topic;

    public Producer(String topic) {
        //配置
        Properties properties = new Properties();
        //连接地址
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //客户端id
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "producer");
        //分片方式
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "kafka.demo.MYpartition");
        //key序列化方式
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        //value序列化方式
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producer = new KafkaProducer<Integer, String>(properties);
        this.topic = topic;
    }

    @Override
    public void run() {
        try {
            int num = 0;
            while (num < 20) {
                String msg = "kafka 练习 msg: " + num;
                producer.send(new ProducerRecord<>(topic, 1, msg), (metadata, exception) -> {
                    System.out.println(metadata.offset() + "->" + metadata.partition() + "->" + metadata.topic());
                });
                TimeUnit.SECONDS.sleep(2);
                num++;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Producer("test_kafka").start();
    }
}
