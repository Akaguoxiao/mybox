package com.aka.mvp;

import android.os.Bundle;

import com.aka.mvp.delegate.ActivityMvpDelegate;
import com.aka.mvp.delegate.ActivityMvpDelegateImpl;
import com.aka.mvp.delegate.MvpDelegateCallback;
import com.aka.mvp.root.IMvpPresenter;
import com.aka.mvp.root.IMvpView;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

/**
 * Created by Aka on 2018/11/8
 * 带toolbar的Activity
 * 1、XXActivity继承MvpAppCompatActivity,当页面存在Presenter时，具体Activity需要调用setPresenter(P... presenter)
 * 2、由于此框架集合了 RxLifecycle 因此本 Activity 继承自 RxActivity
 * 3、支持一个 Activity 存在多个 Presenter
 */
public abstract class MvpAppCompatActivity<V extends IMvpView, P extends IMvpPresenter<V>>
        extends RxAppCompatActivity implements IMvpView, MvpDelegateCallback<V, P> {

    protected ActivityMvpDelegate mvpDelegate;

    /**
     * 获取 Presenter 数组
     */
    protected abstract P[] getPresenterArray();

    @Override
    public P[] getPresenter() {
        return getPresenterArray();
    }

    @Override
    public V[] getMvpView() {
        V[] view = null;
        P[] pArray = getPresenter();
        if (pArray != null) {
            view = (V[]) new IMvpView[pArray.length];
            for (int i = 0; i < pArray.length; i++) {
                view[i] = (V) this;
            }
        }
        return view;
    }


    protected ActivityMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new ActivityMvpDelegateImpl(this, this);
        }
        return mvpDelegate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMvpDelegate().onRestart();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getMvpDelegate().onContentChanged();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getMvpDelegate().onPostCreate(savedInstanceState);
    }

}
