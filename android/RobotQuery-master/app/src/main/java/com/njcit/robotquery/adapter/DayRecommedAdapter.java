package com.njcit.robotquery.adapter;


import android.view.View;
import android.view.ViewGroup;

import com.njcit.robotquery.R;
import com.njcit.robotquery.base.BaseRecyclerViewAdapter;
import com.njcit.robotquery.base.BaseRecyclerViewHolder;
import com.njcit.robotquery.bean.server.DayRecommedBean;
import com.njcit.robotquery.databinding.CardIitemDayBinding;
import com.njcit.robotquery.util.GlideUtil;

/**
 * Created by youxuan on 2017/4/14 0014.
 */

public class DayRecommedAdapter extends BaseRecyclerViewAdapter<DayRecommedBean.DataBean> {


    private boolean isAll = false;

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new DayRecommedViewHolder(parent, R.layout.card_iitem_day);
    }

    private class DayRecommedViewHolder extends BaseRecyclerViewHolder<DayRecommedBean.DataBean, CardIitemDayBinding> {
        public DayRecommedViewHolder(ViewGroup viewGroup, int layoutId) {
            super(viewGroup, layoutId);
        }

        @Override
        public void onBindViewHolder(final DayRecommedBean.DataBean object, final int position) {

            GlideUtil.getIntance().LoadImage(object.getDailyRecommendPicture(), binding.ivContentDay);

            binding.setResultBean(object);    //向视图中填充数据
            binding.executePendingBindings();


            //绑定Item的菜单控件
            binding.ivMeunDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnMenuClickListener.OnClick(v,object,position);
                }
            });

            binding.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(object,position);
                }
            });
        }
    }
}
