package com.aka.mybox.utils;

import android.util.Log;

import com.aka.mybox.BuildConfig;

/**
 * Created by Aka on 2018/11/8
 * LOG工具类
 */
public class LogUtils {
    private static final String TAG = "Mybox";
    private static boolean allowD = true;
    private static boolean allowE = true;
    private static boolean allowI = true;
    private static boolean allowV = true;
    private static boolean allowW = true;

    static {
        allowD = allowE = allowI = allowV = allowW = BuildConfig.LOG_DEBUG;
    }

    private LogUtils() {
    }

    /**
     * 开启Log
     */
    public static void openLog(boolean flag) {
        allowD = flag;
        allowE = flag;
        allowI = flag;
        allowV = flag;
        allowW = flag;
    }

    public static void d(String content) {
        if (!allowD) return;
        Log.d(TAG, content);
    }

    public static void e(String content) {
        if (!allowE) return;
        Log.e(TAG, content);
    }

    public static void i(String content) {
        if (!allowI) return;
        Log.i(TAG, content);
    }

    public static void v(String content) {
        if (!allowV) return;
        Log.v(TAG, content);
    }

    public static void w(String content) {
        if (!allowW) return;
        Log.w(TAG, content);
    }
}
