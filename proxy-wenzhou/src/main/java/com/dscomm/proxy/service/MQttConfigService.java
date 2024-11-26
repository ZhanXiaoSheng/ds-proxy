package com.dscomm.proxy.service;

import com.alibaba.fastjson2.JSONObject;
import com.dscomm.proxy.vo.GetTopicInfoRequest;
import com.dscomm.proxy.vo.MqttConnectionInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author zhangsheng
 * Time 2024/10/28 15:44
 * 从接口获取 ActiveMQ 的连接信息
 */
@Slf4j
@Service
public class MQttConfigService {
    @Value("${artemis.eventType}")
    private Long eventType;

    @Autowired
    private ArtemisPostTestService artemisPostTestService;

    // 获取事件订阅信息 并装配到mq
    public MqttConnectionInfo getRabbitMqConfig() {
        MqttConnectionInfo mqttConnectionInfo = new MqttConnectionInfo();
        try {
            GetTopicInfoRequest topicInfoRequest = new GetTopicInfoRequest();
            ArrayList<Long> eventTypes = new ArrayList<>();
            eventTypes.add(eventType);
            topicInfoRequest.setEventTypes(eventTypes);
            //  ArtemisPostTest.getTopicInfo 可能会抛出异常
            String res = artemisPostTestService.getTopicInfo(topicInfoRequest);
            log.info("artemisPostTest.getTopicInfo():{}", JSONObject.parseObject(res));
            JSONObject jsonObject = JSONObject.parseObject(res);
            if (jsonObject != null && jsonObject.get("msg").equals("success")) {
                JSONObject data = jsonObject.getJSONObject("data");
                //String url = data.getString("host");
//                // 去掉协议头 "tcp://"
//                String addressWithoutProtocol = url.replace("tcp://", "");
//                // 根据 ":" 分隔 IP 和端口
//                String[] parts = addressWithoutProtocol.split(":");
//                if (parts.length == 2) {
//                    activeMQConfig.setHost(parts[0]);
//                    activeMQConfig.setPort(Integer.parseInt(parts[1]));
//                }else {
//                    throw new RuntimeException("Invalid address format");
//                }
                mqttConnectionInfo.setHost(data.getString("host"));
                mqttConnectionInfo.setPassword(data.getString("password"));
                mqttConnectionInfo.setUserName(data.getString("userName"));
                mqttConnectionInfo.setClientId(data.getString("clientId"));


                JSONObject topic = data.getJSONObject("topicName");
               mqttConnectionInfo.setTopicName(topic.getString(eventType.toString()));
                log.info("activeMQConfig is {}", JSONObject.toJSONString(mqttConnectionInfo));
            }

        } catch (Exception e) {
            // 记录异常日志
            log.error(String.format("getRabbitMqConfig error,%s", e.getMessage()));
            throw new RuntimeException(e);
        }

        return mqttConnectionInfo;
    }

}
