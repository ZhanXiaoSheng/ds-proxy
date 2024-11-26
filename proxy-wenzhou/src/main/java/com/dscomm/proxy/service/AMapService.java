package com.dscomm.proxy.service;

import com.alibaba.fastjson2.JSONObject;
import com.dscomm.proxy.utils.HttpClientUtil;
import com.dscomm.proxy.vo.MapRequestInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author zhangsheng
 * Time 2024/11/26 18:02
 * 高德地图服务
 */
@Slf4j
@Service
public class AMapService {
    @Value("${amap.url}")
    private String host;

    @Value("${amap.key}")
    private String key;

    public JSONObject getWay(MapRequestInfo info) {
        if (info == null
                || info.getOriginLatitude() == null
                || info.getOriginLongitude() == null
                || info.getDestinationLatitude() == null
                || info.getDestinationLongitude() == null) {
            log.error("MapRequestInfo is null");
            return null;
        }
        try {
            String url = host + "/v3/direction/driving";
            url += "?origin=" + info.getOriginLongitude() + "," + info.getOriginLatitude();
            url += "&destination=" + info.getDestinationLongitude() + "," + info.getDestinationLatitude();
            url += "&extensions=" + info.getExtensions();
            url += "&key=" + key;

            log.info("getWay url is :{}", url);

            // 创建 HttpClient 实例
            String res = HttpClientUtil.doGet(url);
            if (res != null) {
                return JSONObject.parseObject(res);
            }
            return null;
        } catch (Exception ex) {
            log.error("getWay fail", ex);
        }
        return null;
    }
}
