package com.aka.mybox.core.base;

import android.content.Context;
import android.os.Bundle;

import com.aka.mvp.MvpAppCompatActivity;
import com.aka.mybox.utils.ActivityStackManager;

import java.util.List;

import androidx.annotation.NonNull;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Aka on 2018/11/8
 * 基类Activity,实现EasyPermissions权限管理接口
 * 备注:所有的Activity都继承自此Activity
 * 1.规范团队开发
 * 2.统一处理Activity所需配置,初始化
 */
public abstract class BaseActivity extends MvpAppCompatActivity implements EasyPermissions
        .PermissionCallbacks {
    protected Context mContxet;
    protected Unbinder unBinder;//view 依赖注入：butterknife

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStackManager.getManager().push(this);
        setContentView(getContentViewId());
        mContxet = this;
        unBinder = ButterKnife.bind(this);
        initBundleData();
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除View绑定
        if (unBinder != null) {
            unBinder.unbind();
        }
        ActivityStackManager.getManager().remove(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    /**
     * 获取显示view的xml文件ID
     */
    protected abstract int getContentViewId();

    /**
     * 获取上一个界面传送过来的数据
     */
    protected abstract void initBundleData();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化Data
     */
    protected abstract void initData();

}
