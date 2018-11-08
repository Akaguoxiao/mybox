package com.aka.mvp.root;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

/**
 * Created by Aka on 2018/11/8
 * 根Presenter
 */
public interface IMvpPresenter<V extends IMvpView> {
    /**
     * 将View添加到当前Presenter
     */
    @UiThread
    void attachView(@NonNull V view);

    /**
     * 将View从Presenter移除
     */
    @UiThread
    void detachView();

    /**
     * 销毁V实例
     */
    @UiThread
    void destroy();
}
