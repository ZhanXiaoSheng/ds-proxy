package com.dscomm.proxy.vo;

import lombok.Data;

/**
 * @author zhangsheng
 * Time 2024/11/18 15:10
 * mqtt连接信息
 */
@Data
public class MqttConnectionInfo {

    private String host;
    private String clientId;
    private String userName;
    private String password;
    private String topicName;
}
