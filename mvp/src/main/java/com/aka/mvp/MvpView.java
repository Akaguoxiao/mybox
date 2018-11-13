package com.aka.mvp;

import com.aka.mvp.root.IMvpView;

import androidx.annotation.UiThread;

/**
 * Created by Aka on 2018/11/8
 * 基础View接口
 * 备注:loading/data/error
 * 1. lde 思想: 页面通用  加载中/展示数据/错误处理
 * 2. action 方式: 考虑多个请求时 根据 action 区分处理
 */
public interface MvpView extends IMvpView {
    /**
     * MVP加载中
     *
     * @param action 区分不同事件
     * @param show   开启：true/关闭:false
     */
    @UiThread
    void mvpLoading(String action, boolean show);

    /**
     * mvp 展示数据
     *
     * @param action 区分不同事件
     * @param data   数据
     * @param <M>
     */
    @UiThread
    <M> void mvpShowData(String action, M data);

    /**
     * mvp 错误处理
     *
     * @param action 区分不同事件
     * @param code   错误码
     * @param msg    错误信息
     */
    @UiThread
    void mvpError(String action, int code, String msg);

}
