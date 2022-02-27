package com.njcit.robotquery.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.njcit.robotquery.base.BaseApplication;

/**
 * Created by youxuan on 2017/3/17 0017.
 *获取原生资源
 */

public class CommonUtil {
    public static Drawable getDrawable(int resid){
        return getResource().getDrawable(resid);
    }

    public static int getColor(int resid) {
        return getResource().getColor(resid);
    }

    public static String[] getArray(int resid){
        return getResource().getStringArray(resid);
    }


    public static Resources getResource(){
        return BaseApplication.getInstance().getResources();
    }

    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}

