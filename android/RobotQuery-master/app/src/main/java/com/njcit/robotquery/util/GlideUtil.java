package com.njcit.robotquery.util;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.njcit.robotquery.R;

/**
 * Created by youxuan on 2017/4/14 0014.
 */

public class GlideUtil {

    public static GlideUtil sUtil;

    private GlideUtil(){

    }

    public static  GlideUtil getIntance(){
        if (sUtil==null){
            synchronized (GlideUtil.class){
                if (sUtil==null){
                    sUtil = new GlideUtil();
                }
            }
        }
        return sUtil;
    }


    public void LoadImage( String imageUrl, ImageView imageView){
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.img_one_bi_one)
                .error(R.drawable.img_one_bi_one)
                .into(imageView);
    }
}
