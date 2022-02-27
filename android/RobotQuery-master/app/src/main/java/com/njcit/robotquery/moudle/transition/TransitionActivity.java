package com.njcit.robotquery.moudle.transition;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.njcit.robotquery.MainActivity;
import com.njcit.robotquery.R;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.constant.ConstantsImageUrl;
import com.njcit.robotquery.databinding.ActivityTransitionBinding;
import com.njcit.robotquery.moudle.login.LoginActivity;
import com.njcit.robotquery.util.CommonUtil;
import com.njcit.robotquery.util.SharedPreUtil;

import java.util.Random;

public class TransitionActivity extends AppCompatActivity {


    private ActivityTransitionBinding mBinding;
    private boolean isIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_transition);
        int i = new Random().nextInt(ConstantsImageUrl.TRANSITION_URLS.length);

        //加载默认图
        mBinding.ivDefaultPic.setImageDrawable(CommonUtil.getDrawable(R.drawable.image_app_luncher));

        Glide.with(this)
                .load(ConstantsImageUrl.TRANSITION_URLS[i])
                .placeholder(R.drawable.image_app_luncher)
                .error(R.drawable.image_app_luncher)
                .into(mBinding.ivPic);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                mBinding.ivDefaultPic.setVisibility(View.GONE);
            }
        },1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toMainActivity();
            }

        },3500);
    }

    private void toMainActivity(){
        if (isIn){
            return;
        }

        //判断用户是否登录
        Class cls = null;
        if (getLoginStatus().equals("1")){    //已登录
             cls = MainActivity.class;
        }else {
             cls = LoginActivity.class;      //未登录
        }

        Intent intent = new Intent(this,cls);
        startActivity(intent);

        overridePendingTransition(R.anim.screen_zoom_in,R.anim.screen_zoom_out);
        finish();
        isIn = true;
    }

    private String getLoginStatus(){
        return  SharedPreUtil.getString(getApplicationContext(),Constants.LOGINSTATE,"");

    }
}
