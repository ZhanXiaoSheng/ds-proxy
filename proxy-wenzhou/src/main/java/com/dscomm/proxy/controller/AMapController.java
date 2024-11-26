package com.dscomm.proxy.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dscomm.proxy.service.AMapService;
import com.dscomm.proxy.utils.AjaxResult;
import com.dscomm.proxy.vo.MapRequestInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangsheng
 * Time 2024/11/26 18:25
 */
@RestController
@RequestMapping("/amap")
@Slf4j
public class AMapController extends BaseController {

    @Autowired
    private AMapService aMapService;

    @PostMapping("driving")
    public AjaxResult driving(@RequestBody MapRequestInfo info) {
        if (aMapService.getWay(info)==null){
            return AjaxResult.error("参数错误",null);
        }
        return AjaxResult.success(aMapService.getWay(info));
    }
}
