package rabbitmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 消息推送确认机制配置文件
 */
@Component
@Slf4j
public class PublisherConfirmAndReturnConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 初始化方法
     */
    @PostConstruct
    public void initMethod() {
        rabbitTemplate.setConfirmCallback(this::confirm);
        rabbitTemplate.setReturnsCallback(this::returnedMessage);
    }
    /**
     *
     * @param correlationData 当前消息唯一关联数据
     * @param ack   是否成功收到
     * @param cause 失败的原因
     */
    // 消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        Integer receivedDelay = null;
        if(null != correlationData && correlationData.getReturned()!=null){
            Message message = correlationData.getReturned().getMessage();
            if(message!=null) {
                receivedDelay = message.getMessageProperties().getReceivedDelay();
            }
        }
        if (receivedDelay != null && receivedDelay > 0) {
            // 是一个延迟消息，忽略这个错误提示
            return;
        }
        if (ack) {
            //correlationData 为空
            log.info("消息已经送达Exchange，ack已确认，correlationData:{}", correlationData);
        } else {
            log.error("消息没有送达Exchange,messageId:{},失败原因：{}", correlationData.getId(), cause);
        }
    }

    // 通过实现 ReturnsCallback 接口，启动消息失败返回，如果正确到达队列不执行。
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        //只要消息没有投递到指定队列,就回触发这个失败回调

        /**
         *
         * @param returnedMessage 该类有以下变量
         *  Message message; 投递失败的消息详细信息
         *  replyCode;       回复的状态码
         *  replyText;       回复的文本内容
         *  exchange;        当时这个消息发给那个交换机
         *  routingKey;      当时这个消息用那个路由键
         */
        log.info("消息主体 message : " + returnedMessage.getMessage());
        log.info("状态码 replyCode : " + returnedMessage.getReplyCode());
        log.info("描述：" + returnedMessage.getReplyText());
        log.info("消息使用的交换器 exchange : " + returnedMessage.getExchange());
        log.info("消息使用的路由键 routing : " + returnedMessage.getRoutingKey());
        log.info("消息没有送到队列中");
    }
}
