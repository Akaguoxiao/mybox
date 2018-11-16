package com.aka.mybox;

import android.Manifest;
import android.content.Intent;
import android.view.View;

import com.aka.http.RHttp;
import com.aka.mvp.root.IMvpPresenter;
import com.aka.mybox.Login.LoginActivity;
import com.aka.mybox.Sample.SampleActivity;
import com.aka.mybox.core.base.BaseActivity;
import com.aka.mybox.utils.LogUtils;

import java.util.List;

import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity {
    /*http请求基础路径*/
    public static final String BASE_API = "http://10.0.46.159/mybox";

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        String[] per = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission
                .READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, per)) {
            EasyPermissions.requestPermissions(this, "申请读写权限", 101, per);
        }
        RHttp.Configure.get()
                .baseUrl(BASE_API)
                .init(getApplication());
    }

    @OnClick({R.id.main_login, R.id.sample})
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.main_login:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.sample:
                intent = new Intent(this, SampleActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        LogUtils.e("申请权限成功");
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        LogUtils.e("申请权限失败");
    }


    @Override
    protected IMvpPresenter[] getPresenterArray() {
        return new IMvpPresenter[0];
    }

}
