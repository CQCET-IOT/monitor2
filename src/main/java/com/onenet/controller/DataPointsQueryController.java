package com.onenet.controller;

import com.alibaba.fastjson.JSONObject;
import com.onenet.model.DataPointsQueryRequestBean;
import com.onenet.service.DataPointsQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 数据点请求转发控制器
 * Created by thinker on 2019/5/2.
 */
@RestController
@Api(value = "数据点请求转发", produces = "application/json")
public class DataPointsQueryController {
    @Resource
    DataPointsQueryService dataPointsQueryService;

    @ApiOperation(value = "查询数据点", notes = "查询数据点，注意后端已经固定了产品ApiKey和设备ID，所以本例仅能查询指定设备的数据点")
    @RequestMapping(value = "/datapoints", method = RequestMethod.GET)
    public ResponseEntity<JSONObject> query(DataPointsQueryRequestBean requestBean) {
        System.out.println(requestBean.toString());
        return ResponseEntity.ok(dataPointsQueryService.query(requestBean));
    }

}
