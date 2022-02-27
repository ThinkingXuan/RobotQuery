package com.njcit.robotquery.moudle.fragment.subfragment.PageFragment;

import com.njcit.robotquery.adapter.listener.RequestImpl;
import com.njcit.robotquery.bean.server.SubscribeBean;
import com.njcit.robotquery.http.ApiManage;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by youxuan on 2017/4/15 0015.
 * Subscribe的分页模型
 */

public class SubscribeModel {

    private String userId;
    private int page;
    private int size;

    public SubscribeModel(String userId,int page,int size){
        this.userId = userId;
        this.page = page;
        this.size = size;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public void getData(final RequestImpl impl){
        Subscription subscription = ApiManage.getInstence().getQueryService().getSubscribeMessage(userId,page,size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SubscribeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        impl.loadFailed();
                    }

                    @Override
                    public void onNext(SubscribeBean subscripeBean) {
                        impl.loadSuccess(subscripeBean);
                    }
                });

        impl.addSubscription(subscription);
    }
}
