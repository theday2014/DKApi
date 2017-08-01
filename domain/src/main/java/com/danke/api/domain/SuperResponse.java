package com.danke.api.domain;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by wanghao on 2017/7/31.
 */
public class SuperResponse {
    /**
     * 请求服务器ip
     */
    @JsonProperty(value = "server_ip")
    protected String serverIp;

    /**
     * 返回码
     */
    @JsonProperty(value = "rep_code")
    protected String responseCode;

    /**
     * 错误信息
     */
    @JsonProperty(value = "error_info")
    protected String errorInfo;

    public String toString() {
        return "\"rep_code\":" + responseCode + ",\"error_info\":\"" + errorInfo + "\"";
    }

    public String getServerIp() {
        return serverIp;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }


}
