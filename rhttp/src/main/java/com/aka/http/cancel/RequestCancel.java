package com.aka.http.cancel;

/**
 * 请求取消接口
 *
 *  created by aka
 */
public interface RequestCancel {

    /**
     * 取消请求
     */
    void cancel();

    /**
     * 请求被取消
     */
    void onCanceled();
}
