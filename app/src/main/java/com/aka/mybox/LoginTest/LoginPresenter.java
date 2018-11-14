package com.aka.mybox.LoginTest;

import com.aka.mvp.MvpPresenter;
import com.aka.mybox.LoginTest.view.ILoginView;
import com.aka.mybox.core.GlobalConstants;
import com.aka.mybox.core.net.RHttpCallbcak;
import com.aka.mybox.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle3.LifecycleProvider;

/**
 * Created by Aka on 2018/11/9
 * 登录Presenter
 * 备注:继承 MvpPresenter 指定 View 类型
 */
public class LoginPresenter extends MvpPresenter<ILoginView> {
    private String ACTION_LOGIN = GlobalConstants.ACTION_LOGIN;
    private LifecycleProvider lifecycle;

    public LoginPresenter(LifecycleProvider activity) {
        lifecycle = activity;
    }

    /**
     * 登录
     */
    public void login(String userName, String password) {
        if (isViewAttached()) {
            getView().mvpLoading(ACTION_LOGIN, true);
        }

        RHttpCallbcak httpCallbcak = new RHttpCallbcak<UserBean>() {

            @Override
            public UserBean convert(JsonElement data) {
                return new Gson().fromJson(data, UserBean.class);
            }

            @Override
            public void onSuccess(UserBean value) {
                if (isViewAttached()) {
                    getView().mvpLoading(ACTION_LOGIN, false);
                    getView().mvpShowData(ACTION_LOGIN, value);
                }
            }

            @Override
            public void onError(int code, String desc) {
                if (isViewAttached()) {
                    getView().mvpLoading(ACTION_LOGIN, false);
                    getView().mvpError(ACTION_LOGIN, code, desc);
                }
            }

            @Override
            public void onCancel() {
                LogUtils.e("请求取消");
                if (isViewAttached()) {
                    getView().mvpLoading(ACTION_LOGIN, false);
                }
            }
        };

        new UserBuss().login(userName, password, lifecycle, httpCallbcak);
    }
}
