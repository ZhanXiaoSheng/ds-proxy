package com.dscomm.proxy.vo;

import lombok.Data;

/**
 * @author zhangsheng
 * Time 2024/11/26 18:08
 * 高德请求 入参
 */
@Data
public class MapRequestInfo {

    private Double originLongitude;//起点经度

    private Double originLatitude;//起点纬度

    private Double destinationLongitude;//终点经度

    private Double destinationLatitude;//终点纬度

    private String extensions = "all";
}
