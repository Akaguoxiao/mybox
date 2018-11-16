package com.aka.mybox.core.base;

import java.util.TreeMap;

/**
 * Created by Aka on 2018/11/9
 * 基础业务类
 */
public class BaseBuss {

    /**
     * 获取基础header参数
     */
    public TreeMap<String, Object> getBaseHeader() {
        TreeMap<String, Object> header = new TreeMap<>();
        return header;
    }

    /**
     * 获取基础request参数
     */
    public TreeMap<String, Object> getBaseRequest() {
        TreeMap<String, Object> map = new TreeMap<>();
        return map;
    }

}
