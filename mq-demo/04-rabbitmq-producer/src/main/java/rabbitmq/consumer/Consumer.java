package rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import rabbitmq.entity.Merchant;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class Consumer {

    //@RabbitHandler接收序列化后的消息
    @RabbitHandler
    @RabbitListener(queues = QueueConfig.FANOUT_QUEUE1)
    public void processFanoutQueue1(String message, @Headers Map<String, Object> headers) {
      log.info("queue-{},headers-{}, message-{}", QueueConfig.FANOUT_QUEUE1, headers, message);
    }

    @RabbitHandler
    @RabbitListener(queues = QueueConfig.FANOUT_QUEUE2)
    public void processFanoutQueue2(@Payload String message, @Headers Map<String, Object> headers) {
        log.info("queue-{},headers-{}, message-{}", QueueConfig.FANOUT_QUEUE2, headers, message);
    }

    @RabbitHandler
    @RabbitListener(queues = QueueConfig.TOPIC_QUEUE1)
    public void processTopicQueue1(String message, @Headers Map<String, Object> headers, Channel channel) {
        log.info("queue-{},headers-{}, message-{}, channel-{}", QueueConfig.TOPIC_QUEUE1, headers, message, channel);
    }

    @RabbitHandler
    @RabbitListener(queues = QueueConfig.TOPIC_QUEUE2)
    public void processTopicQueue2(String message, @Headers Map<String, Object> headers) {
        log.info("queue-{},headers-{}, message-{}", QueueConfig.TOPIC_QUEUE2, headers, message);
    }

    @RabbitHandler
    @RabbitListener(queues = QueueConfig.TOPIC_QUEUE3)
    public void processTopicQueue3(String message, @Headers Map<String, Object> headers) {
        log.info("queue-{},headers-{}, message-{}", QueueConfig.TOPIC_QUEUE3, headers, message);
    }

    @RabbitHandler
    @RabbitListener(queues = QueueConfig.DIRECT_QUEUE1)
    public void processDirectQueue1(Merchant bean, Channel channel, Message message) {
        //deliveryTag channel内按顺序自增
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("queue-{}, message-{}", QueueConfig.DIRECT_QUEUE1, bean);
            //do something ...
            if(bean.getId()<0) {
                throw new RuntimeException("ID不正确");
            }
            /**签收消息
             * long deliveryTag, boolean multiple
             * multiple 是否批量签收模式 false代表我只签收我当前这条消息
             *  签收需要给broker回复ack,所以有可能网络中断 需要捕获异常或者抛出异常
             */
            channel.basicAck(deliveryTag, false);
            log.info("消息签收成功");
        } catch (Exception e) {
            e.printStackTrace();
            /**
             * long deliveryTag, boolean multiple, boolean requeue
             * multiple 是否批量拒绝签收
             * requeue true 发回服务器,重新入队  false 丢弃
             */
            try {
                channel.basicNack(deliveryTag, false, false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            log.info("消息签收失败丢弃消息，message:{}", message);
        }
    }

    @RabbitListener(queues = QueueConfig.DIRECT_QUEUE2)
    public void processDirectQueue2(Message message, @Headers Map<String, Object> headers) {
        log.info("queue-{},headers-{}, message-{}", QueueConfig.DIRECT_QUEUE2, headers, message);
    }
}
