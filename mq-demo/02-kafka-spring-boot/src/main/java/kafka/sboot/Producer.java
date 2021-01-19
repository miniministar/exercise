package kafka.sboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;
    public void send() {
        kafkaTemplate.send("test", 1,"msgData");
    }
}
