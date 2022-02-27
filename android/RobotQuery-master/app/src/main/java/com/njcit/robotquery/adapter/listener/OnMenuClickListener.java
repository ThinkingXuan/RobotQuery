package com.njcit.robotquery.adapter.listener;

import android.view.View;

/**
 * Created by youxuan on 2017/4/14 0014.
 */

public interface OnMenuClickListener<T> {
    public void OnClick(View view,T t,int position);
}
