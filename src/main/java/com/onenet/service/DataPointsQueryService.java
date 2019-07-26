package com.onenet.service;

import cmcc.iot.onenet.javasdk.api.datapoints.GetDatapointsListApi;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.datapoints.DatapointsList;
import com.alibaba.fastjson.JSONObject;
import com.onenet.config.OneNetConfig;
import com.onenet.model.DataPointsQueryRequestBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by thinker on 2019/5/2.
 */
@Service
public class DataPointsQueryService {
    @Resource
    OneNetConfig oneNetConfig;

    public final Logger logger = LoggerFactory.getLogger(getClass());

    public JSONObject query(DataPointsQueryRequestBean requestBean) {

        // 调用 OneNET SDK 查询数据点
        GetDatapointsListApi api = new GetDatapointsListApi(
                requestBean.getDatastreamId(),
                requestBean.getStart(),
                requestBean.getEnd(),
                oneNetConfig.getDeviceId(),
                null,
                Integer.parseInt(requestBean.getLimit()),
                requestBean.getCursor(),
                null,
                null,
                null,
                null,
                oneNetConfig.getApiKey());

        BasicResponse<DatapointsList> response = api.executeApi();
        logger.info(response.toString());

        return JSONObject.parseObject(response.getJson());
    }
}
