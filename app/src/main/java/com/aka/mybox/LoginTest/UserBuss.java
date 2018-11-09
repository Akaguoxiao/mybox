package com.aka.mybox.LoginTest;

import com.aka.http.RHttp;
import com.aka.http.callback.HttpCallback;
import com.aka.mybox.core.base.BaseBuss;
import com.trello.rxlifecycle3.LifecycleProvider;

import java.util.TreeMap;

/**
 * Created by Aka on 2018/11/9
 * 用户相关业务
 */
public class UserBuss extends BaseBuss {

    /**
     * 登录api
     */
    private final String API_LOGIN = "sys/login";
    //private final String API_LOGIN = "user/login";

    /**
     * 用户登录
     *
     * @param userName
     * @param password
     * @param lifecycle
     * @param callback
     */
    public void login(String userName, String password, LifecycleProvider lifecycle, HttpCallback
            callback) {
        /**
         * 构建请求参数
         */
        TreeMap<String, Object> request = new TreeMap<>();
        request.put("username", userName);
        request.put("password", password);
        request.putAll(getBaseRequest());

        /**
         * 发送请求
         */
        RHttp http = new RHttp.Builder().post()
                .baseUrl("http://10.0.46.159:8080/mybox/")
                //.baseUrl("http://apicloud.mob.com/")
                .apiUrl(API_LOGIN)
                .addParameter(request)
                .lifecycle(lifecycle)
                .build();

        http.request(callback);
    }
}
