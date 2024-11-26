package com.dscomm.proxy.controller;

import com.dscomm.proxy.service.HikService;
import com.dscomm.proxy.utils.AjaxResult;
import com.dscomm.proxy.vo.FindVehicleByLicensePlateRequest;
import com.dscomm.proxy.vo.FindVehiclePageRequest;
import com.dscomm.proxy.vo.GetTopicInfoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangsheng
 * Time 2024/10/28 11:02
 */
@RestController
@RequestMapping("/hik")
@Slf4j
public class HikController extends BaseController {

    @Autowired
    private HikService hikService;

    @PostMapping("findVehiclePage")
    public AjaxResult findVehiclePage(@RequestBody FindVehiclePageRequest findVehiclePageRequest) {
        return success(hikService.findVehiclePage(findVehiclePageRequest));
    }

    @PostMapping("findVehicleByLicensePlate")
    public AjaxResult findVehicleByLicensePlate(@RequestBody FindVehicleByLicensePlateRequest findVehicleByLicensePlateRequest){
        return success(hikService.findVehicleByLicensePlate(findVehicleByLicensePlateRequest));
    }

    @PostMapping("getTopicInfo")
    public AjaxResult getTopicInfo(@RequestBody GetTopicInfoRequest topicInfoRequest) {
        return success(hikService.getTopicInfo(topicInfoRequest));
    }
}
