package com.aka.mybox.core.net;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Aka on 2018/11/8
 * http响应参数实体类
 * 通过Gson解析属性名称需要与服务器返回字段对应,或者使用注解@SerializedName
 * 备注:这里与服务器约定返回格式
 */
public class Response implements Serializable {
    /**
     * 描述信息
     */
    @SerializedName("msg")
    private String msg;

    /**
     * 状态码
     */
    @SerializedName("code")
    private int code;

    /**
     * 数据对象/成功返回对象
     */
    @SerializedName("results")
    private JsonElement results;

    /**
     * 是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return code == 200;
    }

    @Override
    public String toString() {
        String response = "[http response]" + "{\"code\": " + code + ",\"msg\":" + msg + "," + "\"result\":" + new Gson().toJson(results) + "}";
        return response;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JsonElement getResult() {
        return results;
    }

    public void setResult(JsonElement result) {
        results = result;
    }
}
