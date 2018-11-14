package com.aka.mybox.LoginTest;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aka.mvp.root.IMvpPresenter;
import com.aka.mybox.LoginTest.view.ILoginView;
import com.aka.mybox.LoginTest.view.KeyboardWatcher;
import com.aka.mybox.LoginTest.view.LoginIconTextView;
import com.aka.mybox.R;
import com.aka.mybox.core.base.BaseFragment;
import com.aka.mybox.utils.LogUtils;
import com.aka.mybox.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by Aka on 2018/11/14
 * 登录页面
 */
public class LoginFragment extends BaseFragment implements KeyboardWatcher
        .SoftKeyboardStateListener, ILoginView {
    @BindView(R.id.login_logo)
    LoginIconTextView logo;
    @BindView(R.id.login_username)
    EditText et_username;
    @BindView(R.id.login_password)
    EditText et_password;
    @BindView(R.id.login_username_clean)
    ImageView clean_username;
    @BindView(R.id.login_password_clean)
    ImageView clean_password;
    @BindView(R.id.login_password_show)
    ImageView show_password;
    @BindView(R.id.login_btn)
    Button btn_login;
    @BindView(R.id.forget_password)
    TextView forget_password;
    @BindView(R.id.login_body)
    View body;
    @BindView(R.id.login_root)
    View root;

    private int screenHeight = 0;//屏幕高度
    private float scale = 0.8f;//logo缩放比例
    boolean flag = false;//密码是否可见

    private KeyboardWatcher keyboardWatcher;


    private LoginPresenter mLoginPresenter = new LoginPresenter(this);


    @Override
    protected View getContentView() {
        return LayoutInflater.from(mContext).inflate(R.layout.activity_login, null);
    }

    @Override
    protected void initBundleData() {
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        keyboardWatcher = new KeyboardWatcher(getActivity().findViewById(Window
                .ID_ANDROID_CONTENT));
        keyboardWatcher.addSoftKeyboardStateListener(this);
        screenHeight = getResources().getDisplayMetrics().heightPixels;//获取屏幕高度
    }

    @OnClick({R.id.login_close, R.id.login_username_clean, R.id.login_password_clean, R.id
            .login_password_show})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_close:
                getActivity().finish();
                break;
            case R.id.login_username_clean:
                //清除用户名
                et_username.setText("");
                break;
            case R.id.login_password_clean:
                //清除密码
                et_password.setText("");
                break;
            case R.id.login_password_show:
                //密码是否可见
                if (flag) {
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    show_password.setImageResource(R.drawable.pass_gone);
                    flag = false;
                } else {
                    et_password.setTransformationMethod(HideReturnsTransformationMethod
                            .getInstance());
                    show_password.setImageResource(R.drawable.pass_visuable);
                    flag = true;
                }
                String pwd = et_password.getText().toString();
                if (!TextUtils.isEmpty(pwd)) et_password.setSelection(pwd.length());
                break;
            default:
                break;

        }
    }

    @OnTextChanged(value = R.id.login_username, callback = OnTextChanged.Callback
            .AFTER_TEXT_CHANGED)
    void afterUsernameTextChanged(Editable s) {
        if (!TextUtils.isEmpty(s) && clean_username.getVisibility() == View.GONE) {
            clean_username.setVisibility(View.VISIBLE);
        } else if (TextUtils.isEmpty(s)) {
            clean_username.setVisibility(View.GONE);
        }
    }

    @OnTextChanged(value = R.id.login_password, callback = OnTextChanged.Callback
            .AFTER_TEXT_CHANGED)
    void afterPasswordTextChanged(Editable s) {
        if (!TextUtils.isEmpty(s) && clean_password.getVisibility() == View.GONE) {
            clean_password.setVisibility(View.VISIBLE);
        } else if (TextUtils.isEmpty(s)) {
            clean_password.setVisibility(View.GONE);
        }
        if (s.toString().isEmpty()) return;
        et_password.setSelection(s.length());
    }


    @Override
    public void mvpLoading(String action, boolean show) {

    }

    @Override
    public <M> void mvpShowData(String action, M data) {

    }

    @Override
    public void mvpError(String action, int code, String msg) {

    }


    /**
     * 缩小
     *
     * @param view
     */
    public void zoomIn(final View view, float dist) {
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, scale);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, scale);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", 0.0f,
                -dist);

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX).with(mAnimatorScaleY);

        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();

    }

    /**
     * f放大
     *
     * @param view
     */
    public void zoomOut(final View view) {
        if (view.getTranslationY() == 0) {
            return;
        }
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();

        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", scale, 1.0f);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", scale, 1.0f);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", view
                .getTranslationY(), 0);

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();

    }

    @Override
    public void onSoftKeyboardOpened(int keyboardHeightInPx) {
        LogUtils.e("----->show" + keyboardHeightInPx);
        int[] location = new int[2];
        body.getLocationOnScreen(location); //获取body在屏幕中的坐标,控件左上角
        int x = location[0];
        int y = location[1];
        LogUtils.e("y = " + y + ",x=" + x);
        int bottom = screenHeight - (y + body.getHeight());
        LogUtils.e("bottom = " + bottom);
        LogUtils.e("con-h = " + body.getHeight());
        if (keyboardHeightInPx > bottom) {
            ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(body, "translationY",
                    0.0f, -(keyboardHeightInPx - bottom));
            mAnimatorTranslateY.setDuration(300);
            mAnimatorTranslateY.setInterpolator(new AccelerateDecelerateInterpolator());
            mAnimatorTranslateY.start();
            zoomIn(logo, keyboardHeightInPx - bottom);

        }
    }

    @Override
    public void onSoftKeyboardClosed() {
        LogUtils.e("----->hide");
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(body, "translationY", body
                .getTranslationY(), 0);
        mAnimatorTranslateY.setDuration(300);
        mAnimatorTranslateY.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimatorTranslateY.start();
        zoomOut(logo);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        keyboardWatcher.removeSoftKeyboardStateListener(this);
    }

    @Override
    protected IMvpPresenter[] getPresenterArray() {
        return new IMvpPresenter[]{mLoginPresenter};
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(mContext, msg);
    }

}
