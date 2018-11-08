package com.aka.mvp;

import com.aka.mvp.root.IMvpPresenter;
import com.aka.mvp.root.IMvpView;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

/**
 * Created by Aka on 2018/11/8
 * Presenter基础实现
 */
public class MvpPresenter<V extends IMvpView> implements IMvpPresenter<V> {
    //View弱引用
    private WeakReference<V> viewRef;

    /**
     * 获取view
     *
     * @return
     */
    @UiThread
    public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    /**
     * 判断View是否已经添加
     *
     * @return
     */
    @UiThread
    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    /**
     * 绑定View
     *
     * @param view
     */
    @UiThread
    @Override
    public void attachView(@NonNull V view) {
        viewRef = new WeakReference<V>(view);
    }

    /**
     * 移除View
     */
    @Override
    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    @Override
    public void destroy() {

    }
}
