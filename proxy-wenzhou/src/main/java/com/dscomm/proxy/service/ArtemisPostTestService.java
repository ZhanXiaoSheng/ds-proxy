package com.dscomm.proxy.service;

import com.alibaba.fastjson2.JSON;
import com.dscomm.proxy.config.ArtemisProperties;
import com.dscomm.proxy.vo.FindVehicleByLicensePlateRequest;
import com.dscomm.proxy.vo.FindVehiclePageRequest;
import com.dscomm.proxy.vo.GetTopicInfoRequest;
import com.dscomm.proxy.vo.RegionsRequest;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ArtemisPostTestService {

    /**
     * 设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
     * ip:port : 平台门户/nginx的IP和端口（必须使用https协议，https端口默认为443）
     * appKey : 请填入appKey
     * appSecret : 请填入appSecret
     */

    private  final    ArtemisConfig artemisConfig ;
    //设置OpenAPI接口的上下文
    private static final String ARTEMIS_PATH = "/artemis";

    @Autowired
    public ArtemisPostTestService(ArtemisProperties artemisProperties) {
        this.artemisConfig = new ArtemisConfig(
                artemisProperties.getHost(),
                artemisProperties.getAppKey(),
                artemisProperties.getAppSecret()
        );
    }

    //1.分页获取区域列表
    public  String regions(RegionsRequest regionsRequest) {
        String regionsDataApi = ARTEMIS_PATH + "/api/resource/v1/regions";
        Map<String, String> path = getPath(regionsDataApi);
        log.info("regionsDataApi:{}", JSON.toJSONString(path));
        String body = JSON.toJSONString(regionsRequest);
        log.info("regionsRequest:{}", JSON.toJSONString(body));
        try {
            return ArtemisHttpUtil.doPostStringArtemis(artemisConfig,path, body, null, null, "application/json");
        } catch (Exception e) {
            log.error(String.format("regions fail,%s", e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    //2.分页查询车辆信息
    public  String findVehiclePage(FindVehiclePageRequest findVehiclePageRequest) {
        String findVehiclePageDataApi = ARTEMIS_PATH + "/api/rtsm/v1/resource/findVehiclePage";
        Map<String, String> path = getPath(findVehiclePageDataApi);
        log.info("findVehiclePageDataApi:{}", JSON.toJSONString(path));
        String body = JSON.toJSONString(findVehiclePageRequest);
        log.info("findVehiclePageRequest:{}", JSON.toJSONString(body));
        try {
            return ArtemisHttpUtil.doPostStringArtemis(artemisConfig,path, body, null, null, "application/json");
        } catch (Exception e) {
            log.error(String.format("findVehiclePage fail,%s", e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    //根据车牌号码获取车辆信息(含设备)
    public  String findVehicleByLicensePlate(FindVehicleByLicensePlateRequest findVehicleByLicensePlateRequest)  {
        String findVehicleByLicensePlateDataApi = ARTEMIS_PATH +"/api/rtsm/v1/resource/findVehicleByLicensePlate";
        Map<String, String> path = getPath(findVehicleByLicensePlateDataApi);
        log.info("findVehicleByLicensePlateDataApi:{}", JSON.toJSONString(path));
        String body = JSON.toJSONString(findVehicleByLicensePlateRequest);
        log.info("findVehicleByLicensePlateRequest:{}", JSON.toJSONString(body));
        try {
            return ArtemisHttpUtil.doPostStringArtemis(artemisConfig,path, body, null, null, "application/json");
        } catch (Exception e) {
            log.error(String.format("findVehicleByLicensePlateRequest fail,%s", e.getMessage()));
            throw new RuntimeException(e);
        }
    }


    //按事件类型获取事件订阅信息
    public  String getTopicInfo(GetTopicInfoRequest getTopicInfoRequest) {
        String getTopicInfoDataApi = ARTEMIS_PATH +"/api/common/v1/event/getTopicInfo";
        Map<String,String> path = getPath(getTopicInfoDataApi);
        log.info("getTopicInfoDataApi:{}", JSON.toJSONString(path));
        String body = JSON.toJSONString(getTopicInfoRequest);
        log.info("getTopicInfoRequest:{}", JSON.toJSONString(body));
        try {
            return ArtemisHttpUtil.doPostStringArtemis(artemisConfig, path, body, null, null, "application/json");
        }catch (Exception e){
            log.error(String.format("getTopicInfo fail,%s", e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    private static HashMap<String, String> getPath(String findCameraPageDataApi) {
        return new HashMap<String, String>(2) {
            {
                put("https://", findCameraPageDataApi);
            }
        };
    }

}
