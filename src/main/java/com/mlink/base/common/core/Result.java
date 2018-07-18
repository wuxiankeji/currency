package com.mlink.base.common.core;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 统一API响应结果封装
 */


@ApiModel(value = "响应结果实体类")
public class Result {
    @ApiModelProperty(value = "返回结果码，200 成功、400失败、401未认证、500、服务器内部错误")
    private int code;
    @ApiModelProperty(value = "返回结果描述")
    private String message;
    @ApiModelProperty(value = "返回结果数据")
    private Object data;

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
