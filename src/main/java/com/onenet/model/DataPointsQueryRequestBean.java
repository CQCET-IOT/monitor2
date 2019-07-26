package com.onenet.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.MapKey;

/**
 * Created by thinker on 2019/5/2.
 */
@ApiModel
public class DataPointsQueryRequestBean {

    @ApiModelProperty(value = "查询的数据流，多个数据流之间用逗号分隔（可选）")
    @MapKey(name = "datastream_Id")
    private String datastreamId;

    @ApiModelProperty(value = "提取数据点的开始时间（可选）")
    private String start;

    @ApiModelProperty(value = "提取数据点的结束时间（可选）")
    private String end;

    @ApiModelProperty(value = "限定本次请求最多返回的数据点数，0<n<=6000（可选，默认1440）")
    private String limit = "1440";

    @ApiModelProperty(value = "指定本次请求继续从cursor位置开始提取数据（可选）")
    private String cursor;

    public String getDatastreamId() {
        return datastreamId;
    }

    public void setDatastreamId(String datastreamId) {
        this.datastreamId = datastreamId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    @Override
    public String toString() {
        return "DataPointsQueryRequestBean{" +
                "datastreamId='" + datastreamId + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", limit='" + limit + '\'' +
                ", cursor='" + cursor + '\'' +
                '}';
    }
}
