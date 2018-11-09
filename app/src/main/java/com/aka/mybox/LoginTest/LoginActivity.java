package com.aka.mybox.LoginTest;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.aka.mvp.root.IMvpPresenter;
import com.aka.mybox.R;
import com.aka.mybox.core.RLoadingDialog;
import com.aka.mybox.core.base.BaseActivity;
import com.aka.mybox.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView {
    @BindView(R.id.login_username)
    EditText etUserName;
    @BindView(R.id.login_password)
    EditText etPassword;

    private LoginPresenter mLoginPresenter = new LoginPresenter(this);
    private RLoadingDialog mLoadingDialog;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initBundleData() {
    }

    @Override
    protected void initView() {
        mLoadingDialog = new RLoadingDialog(this, true);
    }

    @Override
    protected void initData() {
    }

    @OnClick(R.id.login_btn)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                String userName = etUserName.getText()
                        .toString();
                String password = etPassword.getText()
                        .toString();
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
                    return;
                }
                mLoginPresenter.login(userName, password);
                break;
        }
    }

    @Override
    protected IMvpPresenter[] getPresenterArray() {
        return new IMvpPresenter[]{mLoginPresenter};
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(mContxet, msg);
    }

    @Override
    public void mvpLoading(String action, boolean show) {
        if (show) {
            mLoadingDialog.show();
        } else {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public <M> void mvpData(String action, M data) {
        if (data == null) return;
        UserBean bean = (UserBean) data;
        showToast(bean.getUid());
    }

    @Override
    public void mvpError(String action, int code, String msg) {
        showToast(msg);
    }
}
