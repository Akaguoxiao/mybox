package com.aka.mybox.core.base;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by Aka on 2018/11/8
 * FragmentPager适配器
 */
public class BasePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;

    public BasePagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
