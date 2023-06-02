package rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 延迟队列
 * @author
 */
@Slf4j
@Configuration
public class DirectTtlConfig {

    /**
     * direct路由模式-交换机
     */
    public static final String  DIRECT_EXCHANGE = "direct_ttl_exchange";
    /**
     * direct路由模式-队列
     */
    public static final String DIRECT_QUEUE = "direct_ttl_queue";
    /**
     * direct路由模式-路由键
     */
    public static final String DIRECT_ROUTING = "direct.ttl.routing";


    /**
     * direct路由模式-死信交换机
     */
    public static final String  DIRECT_DLX_EXCHANGE = "direct_ttl_dlx_exchange";
    /**
     * direct路由模式-死信队列
     */
    public static final String DIRECT_DLX_QUEUE = "direct_ttl_dlx_queue";
    /**
     * direct路由模式-路由键
     */
    public static final String DIRECT_DLX_ROUTING = "direct.ttl.dlx.routing";

    /**
     * 1、声明交换机
     * direct路由模式，默认持久化，非自动删除
     * @return
     */
    @Bean(DIRECT_EXCHANGE)
    public Exchange directTtlExchange(){
        return ExchangeBuilder.directExchange(DIRECT_EXCHANGE).build();
    }

    /**
     * 2、声明队列
     * direct路由模式
     * @return
     */
    @Bean(DIRECT_QUEUE)
    public Queue directTtlQueue(){
        // ttl：延迟队列时间，超时为消费则进入死信队列中
        // deadLetterExchange：绑定死信交换机
        // deadLetterRoutingKey：绑定死信路由
        return QueueBuilder.durable(DIRECT_QUEUE).ttl(5000).deadLetterExchange(DIRECT_DLX_EXCHANGE).deadLetterRoutingKey(DIRECT_DLX_ROUTING).build();
    }

    /**
     * 3、队列与交换机进行绑定
     * direct路由模式
     * @param queue @Qualifier 将 value 对应的bean 注入到参数中
     * @param exchange @Qualifier 将 value 对应的bean 注入到参数中
     * @return
     */
    @Bean
    public Binding directTtlQueueExchange(@Qualifier(DIRECT_QUEUE) Queue queue, @Qualifier(DIRECT_EXCHANGE) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DIRECT_ROUTING).noargs();
    }



    /**
     * 1、声明死信交换机
     * direct路由模式，默认持久化，非自动删除
     * @return
     */
    @Bean(DIRECT_DLX_EXCHANGE)
    public Exchange directDlxExchange(){
        return ExchangeBuilder.directExchange(DIRECT_DLX_EXCHANGE).build();
    }

    /**
     * 2、声明死信队列
     * direct路由模式
     * @return
     */
    @Bean(DIRECT_DLX_QUEUE)
    public Queue directDlxQueue(){
        return QueueBuilder.durable(DIRECT_DLX_QUEUE).build();
    }

    /**
     * 3、死信队列与死信交换机进行绑定
     * direct路由模式
     * @param queue @Qualifier 将 value 对应的bean 注入到参数中
     * @param exchange @Qualifier 将 value 对应的bean 注入到参数中
     * @return
     */
    @Bean
    public Binding directDlxQueueExchange(@Qualifier(DIRECT_DLX_QUEUE) Queue queue, @Qualifier(DIRECT_DLX_EXCHANGE) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DIRECT_DLX_ROUTING).noargs();
    }
}
