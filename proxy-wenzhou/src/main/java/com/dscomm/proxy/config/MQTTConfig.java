package com.dscomm.proxy.config;

import com.dscomm.proxy.service.MQttConfigService;
import com.dscomm.proxy.vo.MqttConnectionInfo;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangsheng
 * Time 2024/10/28 15:57
 */
@Slf4j
@Configuration
public class MQTTConfig {

    private final MQttConfigService MQttConfigService;
    private final MessageListener messageListener; // 注入 MessageListener

    public MQTTConfig(MQttConfigService MQttConfigService,MessageListener messageListener) {
        this.MQttConfigService = MQttConfigService;
        this.messageListener = messageListener;
    }

    @Bean
    public MqttClient mqttClient() {
        try {
            MqttConnectionInfo connectionInfo = MQttConfigService.getRabbitMqConfig();
            // 获取动态配置的连接信息
            String brokerUrl = connectionInfo.getHost();
            String clientId = connectionInfo.getClientId();
            String username = connectionInfo.getUserName();
            String password = connectionInfo.getPassword();

            MqttClient client = new MqttClient(brokerUrl, clientId, new MemoryPersistence());

            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            client.connect(options);
            // 设置消息监听器
            /**
             * MessageListener 是通过 newc创建的，这会导致spirng无法注入CacheService，
             * 从而导致NullPointerException。这个是因为直接通过new创建的MessageListener实例不会被
             * spring容器管理，因此@Autowired 的依赖不会被注入。
             * 解决方法：使用spring容器管理MessageListener ，通过将MessageListener 注入到MQTTConfig
              */
            client.setCallback(messageListener);

            // 订阅动态获取的 topic
            client.subscribe(connectionInfo.getTopicName());
            log.info("Connected to {}", brokerUrl);
            log.info("Subscribed to topic {}", connectionInfo.getTopicName());

            return client;
        } catch (MqttException e) {
            log.error(" connect error {}", e.getMessage());
        }
        return null;
    }
}