package com.aka.mybox.core.base;

import java.util.TreeMap;

/**
 * Created by Aka on 2018/11/9
 * 基础业务类
 */
public class BaseBuss {
    public final String appKey = "1889b37351288";
    public final String k_key = "key";
//    public final String appToken = "e90267e856f3d97844874f0a97bb263e";
//    public final String k_token = "token";


    /**
     * 获取基础request参数
     */
    public TreeMap<String, Object> getBaseRequest() {
        TreeMap<String, Object> map = new TreeMap<>();
        //map.put(k_key, appKey);
        //map.put(k_token, appToken);
        return map;
    }
}
