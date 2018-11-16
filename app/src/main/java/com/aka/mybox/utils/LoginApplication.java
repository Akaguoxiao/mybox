package com.aka.mybox.utils;

import android.app.Application;

/**
 * Created by Aka on 2018/11/15
 */
public class LoginApplication extends Application {
    private SharedPreferenceUtils helper;
    private static LoginApplication instance;
    private static int isLogin = 0;//判断是否登录,0:未登录，1：已登录
    private static boolean isFirstApp;

    public int getIsLogin() {
        isLogin = helper.getInt("isLogin");
        return isLogin;
    }

    public void setIsLogin(int isLogin) {
        helper.putValues(new SharedPreferenceUtils.ContentValue("isLogin", isLogin));
        this.isLogin = isLogin;
    }

    public boolean isIsFirstApp() {
        isFirstApp = helper.getBoolean("First", true);
        return isFirstApp;
    }

    public void setIsFirstApp(boolean isFirstApp) {
        helper.putValues(new SharedPreferenceUtils.ContentValue("First", isFirstApp));
        this.isFirstApp = isFirstApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        helper = new SharedPreferenceUtils(getApplicationContext(), "user");
    }

    public static LoginApplication getInstance() {
        return instance;
    }
}
