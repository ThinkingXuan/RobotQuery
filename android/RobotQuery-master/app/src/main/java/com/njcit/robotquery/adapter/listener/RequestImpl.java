package com.njcit.robotquery.adapter.listener;

import rx.Subscription;

/**
 * Created by youxuan on 2017/3/25.
 * 用于数据请求的回调
 */

public interface RequestImpl {
    void loadSuccess(Object object);

    void loadFailed();

    void addSubscription(Subscription subscription);
}
