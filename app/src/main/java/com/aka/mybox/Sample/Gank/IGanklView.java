package com.aka.mybox.Sample.Gank;

import com.aka.mvp.MvpView;

/**
 * Created by Aka on 2018/11/12
 * map页面view接口
 */
public interface IGanklView extends MvpView {
    /**
     * 额外方法显示吐司
     *
     * @param msg
     */
    void showToast(String msg);
}
