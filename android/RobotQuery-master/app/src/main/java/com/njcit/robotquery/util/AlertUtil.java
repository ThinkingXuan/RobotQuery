package com.njcit.robotquery.util;

import android.app.Activity;

import com.tapadoo.alerter.Alerter;

/**
 * Created by youxuan on 2017/3/26 0026.
 * 封装Alerter
 */

public class AlertUtil {


    public static void  show(Activity activity,int backgroundColor,String title,String text){


        Alerter.create(activity)
                .setTitle(title)
                .setText(text)
                .setBackgroundColor(backgroundColor)
                .setDuration(500)
                .show();
    }

    public static void  show(Activity activity,String title,String text){
        Alerter.create(activity)
                .setTitle(title)
                .setText(text)
                .setBackgroundColor(android.R.color.holo_red_light)
                .setDuration(500)
                .show();
    }
}
