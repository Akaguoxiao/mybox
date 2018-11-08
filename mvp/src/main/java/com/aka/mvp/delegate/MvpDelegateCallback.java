package com.aka.mvp.delegate;

import com.aka.mvp.root.IMvpPresenter;
import com.aka.mvp.root.IMvpView;

/**
 * Created by Aka on 2018/11/8
 * P层代理
 */
public interface MvpDelegateCallback<V extends IMvpView, P extends IMvpPresenter<V>> {
    /**
     * Gets the presenter.
     */
    P[] getPresenter();

    /**
     * Gets the MvpView for the presenter
     *
     * @return The view associated with the presenter
     */
    V[] getMvpView();

}
