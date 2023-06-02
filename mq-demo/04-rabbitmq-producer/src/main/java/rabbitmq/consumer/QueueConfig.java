package rabbitmq.consumer;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:mq.properties")
public class QueueConfig {

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

    public static final String FANOUT_QUEUE1 = "fanout_queue_01";
    public static final String FANOUT_QUEUE2 = "fanout_queue_02";
    public static final String TOPIC_QUEUE1 = "topic_queue_01";
    public static final String TOPIC_QUEUE2 = "topic_queue_02";
    public static final String TOPIC_QUEUE3 = "topic_queue_03";
    public static final String DIRECT_QUEUE1 = "direct_queue_01";
    public static final String DIRECT_QUEUE2 = "direct_queue_02";

    // 创建队列
    @Bean
    public Queue createFanoutQueue1() {
        return new Queue(FANOUT_QUEUE1);
    }

    @Bean
    public Queue createFanoutQueue2() {
        return new Queue(FANOUT_QUEUE2);
    }

    @Bean
    public Queue createTopicQueue1() {
        return new Queue(TOPIC_QUEUE1);
    }
    @Bean
    public Queue createTopicQueue2() {
        return new Queue(TOPIC_QUEUE2);
    }
    @Bean
    public Queue createTopicQueue3() {
        return new Queue(TOPIC_QUEUE3);
    }

    @Bean
    public Queue createDirectQueue1() {
        return new Queue(DIRECT_QUEUE1);
    }
    @Bean
    public Queue createDirectQueue2() {
        return new Queue(DIRECT_QUEUE2);
    }

    // 创建交换机
    @Bean
    public FanoutExchange defFanoutExchange() {
        return new FanoutExchange(fanoutExchange);
    }

    @Bean
    public DirectExchange dfDirectExchange() {
        return new DirectExchange(directExchange);
    }

    @Bean
    public TopicExchange dfTopicExchange() {
        return new TopicExchange(topicExchange);
    }

    // 队列与交换机进行绑定
    @Bean
    public Binding bindingFanout1() {
        return BindingBuilder.bind(this.createFanoutQueue1()).to(defFanoutExchange());
    }
    @Bean
    public Binding bindingFanout2() {
        return BindingBuilder.bind(createFanoutQueue2()).to(defFanoutExchange());
    }

    //队列与交换机绑定并添加路由key（direct和topic模式）
    @Bean
    public Binding bindingDirect1() {
        return BindingBuilder.bind(createDirectQueue1()).to(dfDirectExchange()).with(directRoutingKey);
    }
    @Bean
    public Binding bindingDirect2() {
        return BindingBuilder.bind(createDirectQueue2()).to(dfDirectExchange()).with(directRoutingKey + "_02");
    }

    @Bean
    public Binding bindingTopic1() {
        return BindingBuilder.bind(createTopicQueue1()).to(dfTopicExchange()).with(topicRoutingKey1);
    }
    @Bean
    public Binding bindingTopic2() {
        return BindingBuilder.bind(createTopicQueue2()).to(dfTopicExchange()).with(topicRoutingKey2);
    }
    @Bean
    public Binding bindingTopic3() {
        return BindingBuilder.bind(createTopicQueue3()).to(dfTopicExchange()).with("#.rabbitmq.*");
    }
}
