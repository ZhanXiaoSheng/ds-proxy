package com.dscomm.proxy.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dscomm.proxy.service.CacheService;
import com.dscomm.proxy.service.HikService;
import com.dscomm.proxy.utils.AjaxResult;
import com.dscomm.proxy.vo.FindVehicleByLicensePlateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangsheng
 * Time 2024/10/29 9:34
 */
@RestController
@RequestMapping("/cache")
@Slf4j
public class CacheController {

    private final CacheService cacheService;


    private final HikService hikService;

    @Autowired
    public CacheController(CacheService cacheService,HikService hikService) {
        this.cacheService = cacheService;
        this.hikService = hikService;
    }

    @GetMapping(value = "/{vehicleId}")
    public AjaxResult getVehicleGps(@PathVariable String vehicleId) {
        if (cacheService.get(vehicleId)==null){
            return AjaxResult.error("数据为空",null);
        }
        return AjaxResult.success(cacheService.get(vehicleId));
    }

    /**
     * 由于 接处警的车辆id  和海康返回的对不上
     * 这里使用车牌号作为对应关系：
     * 1.根据车牌号获取车辆的 indexCode
     * 2. 根据 indexCode  获取缓存的实时位置信息
     */
    @PostMapping(value = "/findVehicleGpsByPlate")
    public AjaxResult findVehicleGpsByPlate(@RequestBody FindVehicleByLicensePlateRequest findVehicleByLicensePlateRequest) {
        JSONObject jsonObject = hikService.findVehicleByLicensePlate(findVehicleByLicensePlateRequest);
        if (jsonObject!=null&&jsonObject.get("msg").equals("SUCCESS")&&jsonObject.getJSONObject("data")!=null){
            JSONObject data = jsonObject.getJSONObject("data");
            String indexCode = data.getString("indexCode");
            if (cacheService.get(indexCode)==null){
                return AjaxResult.error("定位数据为空",null);
            }
            return AjaxResult.success(cacheService.get(indexCode));
        }
        return AjaxResult.error("查询不到车牌",null);
    }

}
