package com.njcit.robotquery.moudle.fragment.subfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.njcit.robotquery.R;
import com.njcit.robotquery.adapter.MyFragmentPagerAdapter;
import com.njcit.robotquery.base.BaseFragment;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.databinding.FragmentFunctionBinding;
import com.njcit.robotquery.moudle.fragment.subfragment.PageFragment.DayRecommedFragment;
import com.njcit.robotquery.moudle.fragment.subfragment.PageFragment.SubscribeFragment;

import java.util.ArrayList;

/**
 * Created by youxuan on 2017/3/12 0012.
 */

public class FuncationFragment extends BaseFragment<FragmentFunctionBinding> {


    public static final String TAG = "FuncationFragment";

    public static FuncationFragment newInstance(String s) {

        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        FuncationFragment fragment = new FuncationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private ArrayList<String> mTitleList = new ArrayList<>(4);
    private ArrayList<Fragment> mFragments = new ArrayList<>(4);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showLoading();
        initFragmentList();
        /**
         * 注意使用的是：getChildFragmentManager，
         * 这样setOffscreenPageLimit()就可以添加上，保留相邻3个实例，切换时不会卡
         * 但会内存溢出，在显示时加载数据
         */
        MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mFragments, mTitleList);
        mBindingView.vpGank.setAdapter(myAdapter);
        // 左右预加载页面的个数
        mBindingView.vpGank.setOffscreenPageLimit(3);
        myAdapter.notifyDataSetChanged();
        mBindingView.tabGank.setTabMode(TabLayout.MODE_FIXED);
        mBindingView.tabGank.setupWithViewPager(mBindingView.vpGank);

        showContentView();

    }

    @Override
    public int setContent() {
        return R.layout.fragment_function;

    }

    private void initFragmentList() {
        mTitleList.add("每日推荐");
        mTitleList.add("我的订阅");
        mTitleList.add("模板中心");
        mTitleList.add("我的社区");
        if (mFragments.size()<=0) {
            mFragments.add(new DayRecommedFragment());
            mFragments.add(new SubscribeFragment());
            mFragments.add(new DayRecommedFragment());
            mFragments.add(new DayRecommedFragment());
//        mFragments.add(AndroidFragment.newInstance("iOS"));
        }
    }

}
