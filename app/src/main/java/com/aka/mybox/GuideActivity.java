package com.aka.mybox;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aka.mvp.root.IMvpPresenter;
import com.aka.mybox.Login.LoginActivity;
import com.aka.mybox.core.adpter.GuideViewPagerAdapter;
import com.aka.mybox.core.base.BaseActivity;
import com.aka.mybox.utils.LoginApplication;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Aka on 2018/11/15
 */
public class GuideActivity extends BaseActivity {
    @BindView(R.id.guide_viewpager)
    ViewPager mPager;
    @BindView(R.id.guide_dots)
    LinearLayout mDotsLayout;
    @BindView(R.id.btn_guide)
    Button mBtnClose;
    List<View> viewList;

    @OnClick(R.id.btn_guide)
    void onClick() {
        //跳转MainActivity
        Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
        LoginApplication.getInstance().setIsLogin(LauncherActivity.RESULT_LOGIN);
        startActivity(intent);
        finish();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initBundleData() {
    }

    @Override
    protected void initView() {
        initPager();
        mPager.setAdapter(new GuideViewPagerAdapter(viewList));
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                pageSelected(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void pageSelected(int sp) {
        if (mDotsLayout != null && mDotsLayout.getChildCount() > 0) {
            for (int i = 0; i < mDotsLayout.getChildCount(); i++) {
                if (i == sp) {
                    mDotsLayout.getChildAt(i).setSelected(true);
                } else {
                    mDotsLayout.getChildAt(i).setSelected(false);
                }
            }
        }
        if (sp == viewList.size() - 1) {
            updateBtnVisible(View.VISIBLE);
        } else {
            updateBtnVisible(View.GONE);
        }
    }

    private void updateBtnVisible(int visibility) {
        mBtnClose.setVisibility(visibility);

    }

    private int[] getWelcomePagesByVersion() {
        return new int[]{R.drawable.page1, R.drawable.page2, R.drawable.page3};
    }

    private void initPager() {
        viewList = new ArrayList<View>();
        int[] images = getWelcomePagesByVersion();
        for (int i = 0; i < images.length; i++) {
            viewList.add(initView(images[i]));
        }
    }

    private View initView(int res) {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_guide, null);
        ImageView imageView = view.findViewById(R.id.guide_img);
        imageView.setImageResource(res);
        return view;
    }

    @Override
    protected IMvpPresenter[] getPresenterArray() {
        return new IMvpPresenter[0];
    }
}
