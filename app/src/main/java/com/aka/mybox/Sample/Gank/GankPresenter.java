package com.aka.mybox.Sample.Gank;

import com.aka.mvp.MvpPresenter;
import com.aka.mybox.Sample.Gank.modle.GankBean;
import com.aka.mybox.Sample.Gank.modle.GankHttpCallBack;
import com.aka.mybox.core.GlobalConstants;
import com.aka.mybox.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle3.LifecycleProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aka on 2018/11/12
 * map转化页面presentre
 */
public class GankPresenter extends MvpPresenter<IGanklView> {
    private String ACTION_GANK = GlobalConstants.ACTION_GANK;
    private LifecycleProvider lifecycle;

    public GankPresenter(LifecycleProvider activity) {
        lifecycle = activity;
    }

    /**
     * 获取信息
     */
    public void loadPage(final int number, int page) {
        //loading
        if (isViewAttached()) getView().mvpLoading(ACTION_GANK, true);

        GankHttpCallBack httpCallbcak = new GankHttpCallBack<List<GankBean>>() {

            @Override
            public List<GankBean> convert(JsonArray data) {
                List<GankBean> lists = new ArrayList<>();
                for (JsonElement element : data) {
                    GankBean gankBean = new Gson().fromJson(element, GankBean.class);
                    lists.add(gankBean);
                }
                return lists;
            }

            @Override
            public void onSuccess(List<GankBean> value) {
                if (isViewAttached()) {
                    getView().mvpLoading(ACTION_GANK, false);
                    getView().mvpShowData(ACTION_GANK, value);
                }
            }

            @Override
            public void onError(int code, String desc) {
                if (isViewAttached()) {
                    getView().mvpLoading(ACTION_GANK, false);
                    getView().mvpError(ACTION_GANK, code, desc);
                }
            }

            @Override
            public void onCancel() {
                LogUtils.e("请求取消了");
                if (isViewAttached()) {
                    getView().mvpLoading(ACTION_GANK, false);
                }
            }


        };

        //请求
        new GankBuss().loadPage(number, page, lifecycle, httpCallbcak);
    }


}
