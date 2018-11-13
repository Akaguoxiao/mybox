package com.aka.mybox.core.net;

import com.aka.http.callback.HttpCallback;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * Created by Aka on 2018/11/8
 * 根据业务进一步封装(模板）
 * 在具体业务里，根据服务器返回结果格式进行更改，extends HttpCallback<T>
 */
public abstract class RHttpCallbcak<T> extends HttpCallback<T> {

    /**
     * 接口响应数据格式如@Response
     * 根据业务封装:
     * 1. response.isSuccess() (code==0) 业务逻辑成功回调convert()=>onSuccess()，否则失败回调onError()
     * 2.统一处理接口逻辑 例如:code==101 token过期等等
     */
    @Override
    public T onConvert(String data) {
        T t = null;
        Response response = new Gson().fromJson(data, Response.class);
        int code = response.getCode();
        String msg = response.getMsg();
        JsonElement result = response.getResult();
        switch (code) {
            case 101://token过期，跳转登录页面重新登录(示例)
                break;
            case 102://系统公告(示例)
                break;
            default:
                if (response.isSuccess()) {//与服务器约定成功逻辑
                    t = convert(result);
                } else {//统一为错误处理
                    onError(code, msg);
                }
                break;
        }
        return t;
    }

    /**
     * 数据转换/解析
     *
     * @param data
     * @return
     */
    public abstract T convert(JsonElement data);

    /**
     * 成功回调
     *
     * @param value
     */
    public abstract void onSuccess(T value);

    /**
     * 失败回调
     *
     * @param code
     * @param desc
     */
    public abstract void onError(int code, String desc);

    /**
     * 取消回调
     */
    public abstract void onCancel();


}
