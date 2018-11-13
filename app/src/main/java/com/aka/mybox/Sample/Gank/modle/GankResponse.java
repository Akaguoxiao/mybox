package com.aka.mybox.Sample.Gank.modle;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Aka on 2018/11/13
 * 服务器返回数据格式
 */
public class GankResponse implements Serializable {

    @SerializedName("error")
    private String error;
    @SerializedName("results")
    private JsonArray results;

    public String getError() {
        return error;
    }

    public JsonArray getResults() {
        return results;
    }


}
