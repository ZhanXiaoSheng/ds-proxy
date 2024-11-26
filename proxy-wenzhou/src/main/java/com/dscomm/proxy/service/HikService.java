package com.dscomm.proxy.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dscomm.proxy.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author zhangsheng
 * Time 2024/10/28 11:17
 * 海康服务
 */
@Slf4j
@Service
public class HikService {

    @Autowired
    private ArtemisPostTestService artemisPostTestService;
    /**
     * 分页获取车辆信息
     */
    public JSONObject findVehiclePage(FindVehiclePageRequest findVehiclePageRequest) {
        //设置为查询全部车辆
        ExactCondition exactCondition = new ExactCondition();
        exactCondition.setOrgIndexCodes(getRegions());
        findVehiclePageRequest.setExactCondition(exactCondition);
        String res = artemisPostTestService.findVehiclePage(findVehiclePageRequest);
        log.info("findVehiclePage res:{}", res);
        return JSONObject.parseObject(res);
    }

    /**
     * 获取全部区域信息
     */
    public ArrayList<String> getRegions(){
        ArrayList<String>res = new ArrayList<>();
        //获取全部区域
        RegionsRequest region = new RegionsRequest();
        region.setTreeCode("0");
        region.setPageNo(1);
        region.setPageSize(500);//一次性查询全部region
        String regionList = regions(region);
        if (regionList!=null){
            JSONObject jsonObject = JSONObject.parseObject(regionList);
            if (jsonObject.get("msg").equals("success")){
                JSONObject data = jsonObject.getJSONObject("data");
                JSONArray list = data.getJSONArray("list");
                for (int i = 0; i < list.size(); i++) {
                    JSONObject index = list.getJSONObject(i);
                    res.add(index.getString("indexCode"));
                }
            }
        }
        return res;
    }

    /**
     * 获取区域列表
     */
    public String regions(RegionsRequest regionsRequest) {
        return artemisPostTestService.regions(regionsRequest);
    }

    /**
     * 获取订阅信息 (用于接口测试)
     * @param getTopicInfoRequest
     * @return
     */
    public JSONObject getTopicInfo(GetTopicInfoRequest getTopicInfoRequest){
        return JSONObject.parseObject(artemisPostTestService.getTopicInfo(getTopicInfoRequest));
    }

    /**
     * 根据车牌号码获取车辆信息(含设备)
     * @param findVehicleByLicensePlateRequest
     * @return
     */
    public JSONObject findVehicleByLicensePlate(FindVehicleByLicensePlateRequest findVehicleByLicensePlateRequest){
        String res = artemisPostTestService.findVehicleByLicensePlate(findVehicleByLicensePlateRequest);
        log.info("findVehicleByLicensePlateRequest res:{}", res);
        return JSONObject.parseObject(res);
    }
}
