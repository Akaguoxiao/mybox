package com.aka.mybox.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Aka on 2018/11/8
 * Glide加载图片
 */
public class ImageLoaderUtils {

    public static void load(Context context, String url, ImageView imageView) {
        if (context != null) {
            //Glide.with(context).load(url).placeholder(R.mipmap.ic_launcher).dontAnimate().error
            // (R.mipmap.ic_launcher).into(imageView);
            Glide.with(context).load(url).into(imageView);
        }
    }
}
