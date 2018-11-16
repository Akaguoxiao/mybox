package com.aka.mybox.Login.modle;

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

    private String username;
    private String mobile;
    private String password;

    public UserBean(String userName, String password) {
        username = userName;
        this.password = password;
        mobile = "123456";//服务器默认123456
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
