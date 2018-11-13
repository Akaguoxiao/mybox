package com.aka.mybox.Sample.Gank;

import android.app.AlertDialog;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aka.mvp.root.IMvpPresenter;
import com.aka.mybox.R;
import com.aka.mybox.Sample.Gank.modle.GankBean;
import com.aka.mybox.Sample.adapter.GankListAdapter;
import com.aka.mybox.core.base.BaseFragment;
import com.aka.mybox.utils.ToastUtils;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Aka on 2018/11/12
 */
public class GanklFragment extends BaseFragment implements IGanklView {
    private int number = 16;
    private int page = 1;

    @BindView(R.id.sample_map_pageTv)
    TextView pageTv;
    @BindView(R.id.sample_map_previous)
    Button previousPageBt;
    @BindView(R.id.sample_map_swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.sample_map_gridRv)
    RecyclerView gridRv;

    @OnClick(R.id.sample_map_previous)
    void previousPage() {
        loadPage(number, --page);
        if (page == 1) {
            previousPageBt.setEnabled(false);
        }
    }

    @OnClick(R.id.sample_map_next)
    void nextPage() {
        loadPage(number, ++page);
        if (page == 2) {
            previousPageBt.setEnabled(true);
        }
    }

    //问号小提示
    @OnClick(R.id.tipButton)
    void tip() {
        new AlertDialog.Builder(getActivity()).setTitle(R.string.title_map)
                .setView(getActivity().getLayoutInflater().inflate(R.layout.dialog_map, null))
                .show();
    }

    private GankPresenter mGankPresenter = new GankPresenter(this);
    //  private RLoadingDialog mLoadingDialog;
    GankListAdapter adapter = new GankListAdapter();

    @Override
    protected View getContentView() {
        return LayoutInflater.from(mContext).inflate(R.layout.fragment_sample_map, null);
    }


    private void loadPage(int number, int page) {
        swipeRefreshLayout.setRefreshing(true);
        mGankPresenter.loadPage(number, page);
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void initView() {
        loadPage(16, 1);
        gridRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager
                .VERTICAL));
        gridRv.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected IMvpPresenter[] getPresenterArray() {
        return new IMvpPresenter[]{mGankPresenter};
    }

    @Override
    public void mvpLoading(String action, boolean show) {
        if (show) {
            swipeRefreshLayout.setRefreshing(true);
        } else {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public <M> void mvpShowData(String action, M data) {
        if (data == null) return;
        List<GankBean> lists = (List<GankBean>) data;
        pageTv.setText(getString(R.string.page_with_number, page));
        adapter.setImages(lists);
    }

    @Override
    public void mvpError(String action, int code, String msg) {
        swipeRefreshLayout.setRefreshing(false);
        showToast(msg);
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(mContext, msg);
    }


}
