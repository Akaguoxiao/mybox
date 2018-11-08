package com.aka.mvp.delegate;

import android.app.Activity;
import android.os.Bundle;

import com.aka.mvp.root.IMvpPresenter;
import com.aka.mvp.root.IMvpView;

/**
 * Created by Aka on 2018/11/8
 * 实现目标对象ActivityMvpDelegateImpl
 * 备注:主要是连接 Activity 的生命周期与 Presenter 实现特定生命周期绑定与解除 V
 */
public class ActivityMvpDelegateImpl<V extends IMvpView, P extends IMvpPresenter<V>> implements
        ActivityMvpDelegate {
    protected Activity activity;

    /**
     * V & P
     */
    private MvpDelegateCallback<V, P> delegateCallback;

    public ActivityMvpDelegateImpl(Activity activity, MvpDelegateCallback<V, P> delegateCallback) {
        if (activity == null) {
            throw new NullPointerException("Activity is null!");
        }
        if (delegateCallback == null) {
            throw new NullPointerException("MvpDelegateCallback is null!");
        }
        this.activity = activity;
        this.delegateCallback = delegateCallback;
    }

    /**
     * 是否保留V&P实例
     *
     * @return
     */
    private static boolean retainVPInstance(Activity activity) {
        return activity.isChangingConfigurations() || !activity.isFinishing();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        P[] pArray = delegateCallback.getPresenter();
        if (pArray != null) {
            V[] vArray = delegateCallback.getMvpView();
            P presenter;
            V view;
            for (int i = 0; i < pArray.length; i++) {
                presenter = pArray[i];
                view = vArray[i];
                if (presenter != null && view != null) {
                    //关联view
                    presenter.attachView(view);
                }
            }
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        P[] pArray = delegateCallback.getPresenter();
        if (pArray != null) {
            P presenter;
            for (int i = 0; i < pArray.length; i++) {
                presenter = pArray[i];
                if (presenter != null) {
                    //解除View
                    presenter.detachView();
                    if (!retainVPInstance(activity)) {
                        //销毁 V & P 实例
                        presenter.destroy();
                    }
                }
            }
        }
    }
}
