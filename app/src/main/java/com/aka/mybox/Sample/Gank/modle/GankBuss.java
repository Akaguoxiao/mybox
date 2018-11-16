package com.aka.mybox.Sample.Gank.modle;

import com.aka.http.RHttp;
import com.aka.http.callback.HttpCallback;
import com.aka.mybox.core.base.BaseBuss;
import com.trello.rxlifecycle3.LifecycleProvider;

import java.util.TreeMap;

/**
 * Created by Aka on 2018/11/12
 */
public class GankBuss extends BaseBuss {

    /**
     * map功能资源API
     */
    private final String BASE_URL = "http://gank.io/api/";
    //private final String API_MAP_GANK = "data/福利/{number}/{page}";


    public void loadPage(int number, int page, LifecycleProvider lifecycle, HttpCallback callback) {
        final String API_MAP_GANK = "data/福利/" + number + "/" + page;
        /**
         * 构建参数,当前页面无参数，不需构建。这里只是提供标准模板
         */
        TreeMap<String, Object> request = new TreeMap<>();

        RHttp http = new RHttp.Builder().post()
                .baseUrl(BASE_URL)
                .apiUrl(API_MAP_GANK)
                .addParameter(request)
                .lifecycle(lifecycle)
                .build();

        http.request(callback);
    }
}
