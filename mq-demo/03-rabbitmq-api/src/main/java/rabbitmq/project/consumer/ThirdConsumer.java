package rabbitmq.project.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:mq.properties")
@RabbitListener(queues = "${rabbitmq.thirdqueue}", containerFactory="rabbitListenerContainerFactory")
public class ThirdConsumer {
    @RabbitHandler
    public void process(String msg){
        System.out.println("Third Queue received msg : " + msg);
    }
}
