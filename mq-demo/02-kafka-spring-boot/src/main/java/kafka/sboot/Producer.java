package kafka.sboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    public void send() {
        String msgData= "msgData";
        kafkaTemplate.send("test", "1", msgData);
        System.out.println("发送消息成功："+msgData);
    }
}
