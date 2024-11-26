package com.dscomm.proxy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zhangsheng
 * Time 2024/10/28 16:04
 * rabbitmq 发送消息的服务(可选)
 */
@Service
public class MessageSenderService {

    private final JmsTemplate jmsTemplate;
    private final MQttConfigService MQttConfigService;

    @Autowired
    public MessageSenderService(JmsTemplate jmsTemplate, MQttConfigService MQttConfigService) {
        this.jmsTemplate = jmsTemplate;
        this.MQttConfigService = MQttConfigService;
    }

    public void sendMessage(String message) {
        //jmsTemplate.convertAndSend(activeMQConfigService.getRabbitMqConfig().getQueueName(), message);
        //todo
        System.out.println("Sent message: " + message);
    }
}
