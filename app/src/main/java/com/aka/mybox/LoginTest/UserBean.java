package com.aka.mybox.LoginTest;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Aka on 2018/11/9
 * 用户实体
 */
public class UserBean implements Serializable {
    //token	string	用户登录生成的token
    //uid	string	用户Id
    @SerializedName("token")
    private String token;
    @SerializedName("uid")
    private String uid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
