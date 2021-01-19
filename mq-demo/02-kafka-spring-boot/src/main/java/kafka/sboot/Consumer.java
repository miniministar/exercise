package kafka.sboot;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Consumer {

    @KafkaListener(topics = {"test", "test_kafka"})
    public void listener(ConsumerRecord record) {
        Optional<Object> msg = Optional.ofNullable(record.value());
        if(msg.isPresent()) {
            System.out.println(msg.get());
        }
    }
}
