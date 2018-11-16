package com.aka.mybox.core.adpter;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * Created by Aka on 2018/11/15
 */
public class GuideViewPagerAdapter extends PagerAdapter {
    private List<View> data;

    public GuideViewPagerAdapter(List<View> data) {
        super();
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if (data == null) return null;
        container.addView(data.get(position));
        return data.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (data != null) {
            container.removeView(data.get(position));
        }
    }
}
