package com.njcit.robotquery.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.njcit.robotquery.R;
import com.njcit.robotquery.databinding.ActivityBaseBinding;
import com.njcit.robotquery.databinding.ActivityMainBinding;
import com.njcit.robotquery.util.CommonUtil;
import com.njcit.robotquery.util.PerfectClickListener;
import com.njcit.robotquery.view.statusbar.StatusBarUtil;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by youxuan on 2017/3/18 0018.
 */

public class BaseActivity<SV extends ViewDataBinding> extends AppCompatActivity{

    //布局View
    protected  SV bindingView;
    private LinearLayout llProgresBar;
    private View refresh;
    private ActivityBaseBinding mBaseBinding;
    private AnimationDrawable mAnimationDrawable;
    private CompositeSubscription mCompositeSubscription;

    protected <T extends View> T getView(int id){
        return (T)findViewById(id);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mBaseBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_base,null,false);
        bindingView = DataBindingUtil.inflate(getLayoutInflater(),layoutResID,null,false);

        //Content
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
        RelativeLayout mContainter = (RelativeLayout) mBaseBinding.getRoot().findViewById(R.id.container);
        mContainter.addView(bindingView.getRoot());
        getWindow().setContentView(mBaseBinding.getRoot());

        //设置透明状态栏
        StatusBarUtil.setColor(this, CommonUtil.getColor(R.color.primary_dark),0);
        llProgresBar = getView(R.id.ll_progress_bar);
        refresh = getView(R.id.ll_error_refresh);
        ImageView img = getView(R.id.img_progress);

        //加载动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
        //默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()){
            mAnimationDrawable.start();
        }

        //setToolBar();
        //点击加载失败布局
        refresh.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showLoading();
                onRefresh();
            }
        });

        bindingView.getRoot().setVisibility(View.GONE);
    }
    /**
     * 设置titleBar
     */

    protected void setToolBar(){
        //setSupportActionBar(mBaseBinding.toolBar);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
//        mBaseBinding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
    }

//
//    public void setTitle(CharSequence text){
//        mBaseBinding.toolBar.setTitle(text);
//    }


    protected void showLoading(){
        if (llProgresBar.getVisibility()!=View.VISIBLE){
            llProgresBar.setVisibility(View.VISIBLE);
        }
        //开始动画
        if (mAnimationDrawable.isRunning()){
            mAnimationDrawable.start();
        }

        if (bindingView.getRoot().getVisibility()!=View.GONE){
            bindingView.getRoot().setVisibility(View.GONE);
        }

        if (refresh.getVisibility()!=View.GONE){
            refresh.setVisibility(View.GONE);
        }
    }

    protected void showContentView(){
        if (llProgresBar.getVisibility()!=View.GONE){
            llProgresBar.setVisibility(View.GONE);
        }

        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (refresh.getVisibility() != View.GONE) {
            refresh.setVisibility(View.GONE);
        }
        if (bindingView.getRoot().getVisibility() != View.VISIBLE) {
            bindingView.getRoot().setVisibility(View.VISIBLE);
        }
    }

    protected void showError(){
        if (llProgresBar.getVisibility()!=View.GONE){
            llProgresBar.setVisibility(View.GONE);
        }
        //停止动画
        if (mAnimationDrawable.isRunning()){
            mAnimationDrawable.stop();
        }

        if (refresh.getVisibility()!=View.VISIBLE){
            refresh.setVisibility(View.VISIBLE);
        }

        if (bindingView.getRoot().getVisibility()!=View.GONE){
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }

    /**
     * 失败后点击刷新
     */

    protected void onRefresh(){

    }

    public void addSubscription(Subscription s){
        if (this.mCompositeSubscription == null){
            this.mCompositeSubscription = new CompositeSubscription();

        }
        this.mCompositeSubscription.add(s);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mCompositeSubscription!=null &&mCompositeSubscription.hasSubscriptions()){
            this.mCompositeSubscription.unsubscribe();
        }
    }


    public void removeSubscription(){
        if (this.mCompositeSubscription!=null && mCompositeSubscription.hasSubscriptions()){
            this.mCompositeSubscription.unsubscribe();
        }
    }
}
