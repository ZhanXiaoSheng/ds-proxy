package com.dscomm.proxy.config;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dscomm.proxy.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangsheng
 * Time 2024/10/28 16:02
 */
@Component
@Slf4j
public class MessageListener implements MqttCallback {

    @Autowired
    private CacheService cacheService;  // 注入 CacheService

    @Override
    public void connectionLost(Throwable cause) {
        log.error("Connection lost: {}", cause.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        log.info("Received message on topic {}: {}", topic, new String(message.getPayload()));
        try {
            // 将接收到的 MQTT 消息转换为 JSON
            JSONObject jsonObject = JSONObject.parseObject(new String(message.getPayload()));
            if (jsonObject != null && jsonObject.getJSONObject("params") != null) {
                JSONObject params = jsonObject.getJSONObject("params");
                if (params != null && params.getJSONArray("events") != null) {
                    JSONArray events = params.getJSONArray("events");
                    JSONObject event = events.getJSONObject(0);
                    if (event != null && event.getString("srcIndex") != null) {
                        cacheService.put(event.getString("srcIndex"), event);
                    }
                }
            }
        } catch (Exception e) {
            log.error("transform message fail: {}, message is {}", e.getMessage(), new String(message.getPayload()), e);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        try {
            log.info("Message delivered: {}", token.getMessage());
        } catch (MqttException e) {
            log.error("Error getting delivered message: {}", e.getMessage(), e);
        }
    }
}
