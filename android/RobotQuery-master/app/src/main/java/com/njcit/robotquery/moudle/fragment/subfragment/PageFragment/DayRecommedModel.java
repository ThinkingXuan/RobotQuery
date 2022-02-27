package com.njcit.robotquery.moudle.fragment.subfragment.PageFragment;

import android.util.Log;

import com.njcit.robotquery.bean.server.DayRecommedBean;
import com.njcit.robotquery.http.ApiManage;
import com.njcit.robotquery.adapter.listener.RequestImpl;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by youxuan on 2017/3/25 0025.
 * 分页数据模型
 */

public class DayRecommedModel {
    public static final String TAG = "DayRecommedModel";
    private int page;
    private int per_page;
    private String userId;

    public void setData(String userId, int page, int per_page) {
        this.page = page;
        this.per_page = per_page;
        this.userId = userId;
    }

    public void getRecommedData(final RequestImpl listener) {
        Log.d(TAG, "getChatHistory: ");
        Subscription subscription =
                ApiManage.getInstence().getQueryService()
                        .getDayRecommdBean(userId, page, per_page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<DayRecommedBean>() {
                            @Override
                            public void onCompleted() {

                                Log.d(TAG, "onCompleted: ");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "onError: ");
                                listener.loadFailed();
                            }

                            @Override
                            public void onNext(DayRecommedBean dayRecommedBean) {
                                Log.d(TAG, "onNext: ");
                                listener.loadSuccess(dayRecommedBean);
                            }
                        });

        listener.addSubscription(subscription);

    }

}
