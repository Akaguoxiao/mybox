package com.aka.mybox.LoginTest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.aka.mvp.root.IMvpPresenter;
import com.aka.mybox.R;
import com.aka.mybox.core.base.BaseFragmentActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class LoginActivity extends BaseFragmentActivity {
    private FragmentManager manager;
    private static Class<?> mCls;
    private Fragment target;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_load_fragment;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        manager = getSupportFragmentManager();
        if (mCls == null) {
            return;
        }
        try {
            target = (Fragment) mCls.newInstance();
            target.setArguments(getIntent().getExtras());
            switchContent(target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开Fragment
     *
     * @param context
     * @param target
     * @param bundle
     */
    public static void lunchFragment(Context context, Class<?> target, Bundle bundle) {
        mCls = target;
        Intent intent = new Intent(context, LoginActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    private Fragment current;

    /**
     * 切换当前显示的fragment
     */
    public void switchContent(Fragment to) {
        if (current != to) {
            FragmentTransaction transaction = manager.beginTransaction();

            if (current != null) {
                transaction.hide(current);
            }
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.add(R.id.login_fragment, to).commit();
            } else {

                transaction.show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            current = to;
        }
    }
    @Override
    protected IMvpPresenter[] getPresenterArray() {
        return new IMvpPresenter[0];
    }
}
