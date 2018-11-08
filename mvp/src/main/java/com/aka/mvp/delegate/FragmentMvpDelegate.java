package com.aka.mvp.delegate;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.View;

import com.aka.mvp.root.IMvpPresenter;
import com.aka.mvp.root.IMvpView;

/**
 * Created by Aka on 2018/11/8
 * Fragment生命周期
 */
public interface FragmentMvpDelegate<V extends IMvpView, P extends IMvpPresenter<V>> {
    void onCreate(Bundle saved);

    void onDestroy();

    void onViewCreated(View view, @Nullable Bundle savedInstanceState);

    void onDestroyView();

    void onPause();

    void onResume();

    void onStart();

    void onStop();

    void onActivityCreated(Bundle savedInstanceState);

    void onAttach(Activity activity);

    void onDetach();

    void onSaveInstanceState(Bundle outState);
}
