package com.aka.mybox.Login.modle;

import android.content.Context;

import com.aka.http.RHttp;
import com.aka.http.callback.HttpCallback;
import com.aka.mybox.core.base.BaseBuss;
import com.aka.mybox.utils.Base64Utils;
import com.aka.mybox.utils.SharedPreferenceUtils;
import com.google.gson.Gson;
import com.trello.rxlifecycle3.LifecycleProvider;

/**
 * Created by Aka on 2018/11/9
 * 用户相关业务
 */
public class LoginBuss extends BaseBuss {

    /**
     * 登录api
     */
    private static final String API_LOGIN = "app/login";

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

        if (userName == null || password == null) return;
        UserBean user = new UserBean(userName, password);

        /**
         * 发送请求
         */
        RHttp http = new RHttp.Builder()
                .post()
                .baseUrl("http://10.0.46.159:8080/mybox/")
                .apiUrl(API_LOGIN)
                .setHeader(getBaseHeader())
                .setBodyString(new Gson().toJson(user), true)
                .lifecycle(lifecycle)
                .build();

        http.request(callback);
    }

    /**
     * 登陆成功后，保存token
     */
    public void saveUser(Context context, String token) {
        SharedPreferenceUtils helper = new SharedPreferenceUtils(context, "user");
        helper.putValues(new SharedPreferenceUtils.ContentValue("token", Base64Utils
                        .encryptBASE64(token)),
                new SharedPreferenceUtils.ContentValue("hasLogin", true));

    }


}
