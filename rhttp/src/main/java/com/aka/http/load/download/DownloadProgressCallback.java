package com.aka.http.load.download;


/**
 * 下载回调接口
 *
 *  created by aka
 */
public interface DownloadProgressCallback {

    /**
     * 下载进度回调
     *
     * @param currentSize 当前值
     * @param totalSize   总大小
     */
    void progress(long currentSize, long totalSize);

}
