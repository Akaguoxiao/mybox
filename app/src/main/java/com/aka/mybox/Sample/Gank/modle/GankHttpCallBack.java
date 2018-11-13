package com.aka.mybox.Sample.Gank.modle;

import com.aka.http.callback.HttpCallback;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

/**
 * Created by Aka on 2018/11/13
 * Gank服务器回调
 */
public abstract class GankHttpCallBack<T> extends HttpCallback<T> {
    @Override
    public T onConvert(String data) {
        T t = null;
        GankResponse response = new Gson().fromJson(data, GankResponse.class);
        String error = response.getError();
        JsonArray results = response.getResults();
        if (error.equals("false")) {//与服务器约定成功逻辑
            t = convert(results);
        }
        return t;
    }

    /**
     * 数据转换/解析
     *
     * @param data
     * @return
     */
    public abstract T convert(JsonArray data);


}
