package com.aka.mybox.Login.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.aka.mybox.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

/**
 * Created by Aka on 2018/11/13
 * 自定义登录图标textview 可在布局文件设置drawableLeft等大小
 */
public class LoginIconTextView extends AppCompatTextView {
    private Context mContext;

    /**
     * resID
     */
    private int iconLeft;
    private int iconRight;
    private int iconTop;
    private int iconBottom;
    /**
     * 图标宽度
     */
    private float iconWidth;
    /**
     * 图标高度
     */
    private float iconHeight;

    public LoginIconTextView(Context context) {
        this(context, null);
    }

    public LoginIconTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoginIconTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LoginIconTextView);
            try {
                iconLeft = array.getResourceId(R.styleable.LoginIconTextView_left, 0);
                iconRight = array.getResourceId(R.styleable.LoginIconTextView_right, 0);
                iconTop = array.getResourceId(R.styleable.LoginIconTextView_top, 0);
                iconBottom = array.getResourceId(R.styleable.LoginIconTextView_bottom, 0);

                iconWidth = array.getResourceId(R.styleable.LoginIconTextView_Width, 500);
                iconHeight = array.getResourceId(R.styleable.LoginIconTextView_Height, 100);
            } finally {
                if (array != null) {
                    array.recycle();
                }
            }
        }
        init();
    }

    private void init() {
        Drawable left = null;
        if (iconLeft != 0) {
            left = ContextCompat.getDrawable(mContext, iconLeft);
            left.setBounds(0, 0, (int) iconWidth, (int) iconHeight);
        }
        Drawable right = null;
        if (iconRight != 0) {
            right = ContextCompat.getDrawable(mContext, iconRight);
            right.setBounds(0, 0, (int) iconWidth, (int) iconHeight);
        }
        Drawable top = null;
        if (iconTop != 0) {
            top = ContextCompat.getDrawable(mContext, iconTop);
            top.setBounds(0, 0, (int) iconWidth, (int) iconHeight);
        }
        Drawable bottom = null;
        if (iconBottom != 0) {
            bottom = ContextCompat.getDrawable(mContext, iconBottom);
            bottom.setBounds(0, 0, (int) iconWidth, (int) iconHeight);
        }

        setCompoundDrawables(left, top, right, bottom);
    }


}
