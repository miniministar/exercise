package rabbitmq.producer;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import rabbitmq.entity.Merchant;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Slf4j
@Configuration
@PropertySource("classpath:mq.properties")
public class RabbitSender {

    @Value("${com.rabbitmq.directexchange}")
    private String directExchange;

    @Value("${com.rabbitmq.topicexchange}")
    private String topicExchange;

    @Value("${com.rabbitmq.fanoutexchange}")
    private String fanoutExchange;

    @Value("${com.rabbitmq.directroutingkey}")
    private String directRoutingKey;

    @Value("${com.rabbitmq.topicroutingkey1}")
    private String topicRoutingKey1;

    @Value("${com.rabbitmq.topicroutingkey2}")
    private String topicRoutingKey2;


    // 自定义的模板，所有的消息都会转换成JSON发送
    @Autowired
    private RabbitTemplate rabbitmqTemplate;

    @PostConstruct
    public void init() {

        try {
            send();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public void send() throws JsonProcessingException {
        Merchant merchant = new Merchant(1001, "a direct msg : queue1", "汉中省解放路266号");
        Merchant merchant2 = new Merchant(1002, "a direct msg : queue2", "汉中省解放路266号");
        // 发送JSON字符串
        String json = JSON.toJSONString(merchant);
        String json2 = JSON.toJSONString(merchant2);

        rabbitmqTemplate.convertAndSend(directExchange, directRoutingKey, merchant);
        rabbitmqTemplate.convertAndSend(directExchange, directRoutingKey + "_02", merchant2);

        rabbitmqTemplate.convertAndSend(topicExchange, topicRoutingKey1, "a topic msg : shanghai.rabbitmq.teacher");
        rabbitmqTemplate.convertAndSend(topicExchange, topicRoutingKey2, "a topic msg : changsha.rabbitmq.student");
        //{"address":"汉中省解放路266号","id":1001,"name":"a direct msg : queue1","stateStr":""}
        log.info(json);
        rabbitmqTemplate.convertAndSend(fanoutExchange, "", json);
        //失败测试只需要写错路由，或者队列，测试发送：
        rabbitmqTemplate.convertAndSend("exchange_error", "routing_error", new HashMap(), new CorrelationData());
    }
}
