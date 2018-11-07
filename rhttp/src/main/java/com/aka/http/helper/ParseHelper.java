package com.aka.http.helper;

/**
 * 数据解析helper
 *
 *  created by aka
 */
public interface ParseHelper<T> {
    /*解析数据*/
    T parse(String data);
}
