package com.njcit.robotquery.base;

import android.app.Application;

import com.iflytek.cloud.SpeechUtility;
import com.njcit.robotquery.R;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by youxuan on 2017/3/17 0017.
 */

public class BaseApplication extends Application {

    private static BaseApplication mApplication;

    public static BaseApplication getInstance(){
       return mApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        initVoice();
        initJush();

    }

    private void initVoice() {
        SpeechUtility.createUtility(this,"appid=" + getString(R.string.app_id));
    }

    private void initJush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

}
