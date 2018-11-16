package com.aka.mybox.Login.view;

import com.aka.mvp.MvpView;

/**
 * Created by Aka on 2018/11/9
 * 登录View
 */
public interface ILoginView extends MvpView {

    /**
     * 额外方法显示吐司
     *
     * @param msg
     */
    void showToast(String msg);
}
