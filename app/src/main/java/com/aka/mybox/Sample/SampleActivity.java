package com.aka.mybox.Sample;


import com.aka.mvp.root.IMvpPresenter;
import com.aka.mybox.R;
import com.aka.mybox.Sample.Gank.GanklFragment;
import com.aka.mybox.core.base.BaseActivity;
import com.aka.mybox.core.base.BasePagerAdapter;
import com.ruffian.library.RVPIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * Fragment
 */
public class SampleActivity extends BaseActivity {

    @BindView(R.id.sample_vp_indicator)
    RVPIndicator vpIndicator;
    @BindView(R.id.sample_viewPager)
    ViewPager viewPager;

    private BasePagerAdapter mPagerAdapter;
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mList = Arrays.asList("Gank", "图片选择库");

    @Override
    protected int getContentViewId() {
        return R.layout.activity_sample;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mFragmentList.add(new GanklFragment());
        mPagerAdapter = new BasePagerAdapter(getSupportFragmentManager(), mFragmentList);

        //设置指示器title
        vpIndicator.setTitleList(mList);

        //设置关联的ViewPager
        vpIndicator.setViewPager(viewPager, 0);

        //设置Adapter
        viewPager.setAdapter(mPagerAdapter);
    }

    @Override
    protected IMvpPresenter[] getPresenterArray() {
        return new IMvpPresenter[0];
    }
}
