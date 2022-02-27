package com.njcit.robotquery.moudle.fragment.subfragment.PageFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.njcit.robotquery.R;
import com.njcit.robotquery.adapter.SubscribeAdapter;
import com.njcit.robotquery.adapter.listener.OnMenuClickListener;
import com.njcit.robotquery.adapter.listener.RequestImpl;
import com.njcit.robotquery.base.BaseApplication;
import com.njcit.robotquery.base.BaseFragment;
import com.njcit.robotquery.bean.server.DayRecommedBean;
import com.njcit.robotquery.bean.server.SubscribeBean;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.databinding.FragmentSubscribeBinding;
import com.njcit.robotquery.http.cache.ACache;
import com.njcit.robotquery.util.SharedPreUtil;

import rx.Subscription;

/**
 * Created by youxuan on 2017/4/15 0015.
 * 我的订阅
 * 根据userId进行加载指定的内容,还可以进行推送。让用户点击通知进入。
 * 这就要求数据库动态更新
 */

public class SubscribeFragment extends BaseFragment<FragmentSubscribeBinding> {

    public static final String TAG = "SubscribeFragment";
    private SubscribeBean mSubscribeBean;
    private SubscribeModel mModel;
    private SubscribeAdapter mAdapter;
    private boolean isPrepared = true;
    private boolean mIsFirst = true;
    private ACache mACache;
    private String userId;
    private int page = 1;
    private int size = 10;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mACache = ACache.get(getContext());
        mAdapter = new SubscribeAdapter();

        mBindingView.recyclerSubscribe.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadSubscribeData();
            }

            @Override
            public void onLoadMore() {
                page++;
                loadSubscribeData();
            }
        });

        // 准备就绪
        mIsFirst = true;

    }


    @Override
    protected void loadData() {

        if (!mIsVisible || !mIsFirst || !isPrepared) {
            return;
        }

        if (mSubscribeBean != null && mSubscribeBean.getResultList() != null && mSubscribeBean.getResultList().size() > 0) {
            showContentView();
            mSubscribeBean = (SubscribeBean) mACache.getAsObject(Constants.SUBSCRIBE_DATA);
            setAdapter(mSubscribeBean);
        } else {
            loadSubscribeData();
        }
    }


    @Override
    public int setContent() {
        return R.layout.fragment_subscribe;
    }

    private void loadSubscribeData() {
        userId = SharedPreUtil.getUserId(BaseApplication.getInstance());
        mModel = new SubscribeModel(userId, page, size);
        mModel.getData(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                showContentView();
                SubscribeBean datas = (SubscribeBean) object;

                if (page == 1) {
                    if (datas != null && datas.getResultList() != null && datas.getResultList().size() > 0) {
                        setAdapter(datas);
                        mACache.remove(Constants.SUBSCRIBE_DATA);
                        // 缓存50分钟
                        mACache.put(Constants.SUBSCRIBE_DATA, datas, 30000);

                    }else {
                        mBindingView.recyclerSubscribe.refreshComplete();
                        setAdapter(datas);
                    }
                } else {
                    if (datas != null && datas.getResultList() != null && datas.getResultList().size() > 0) {

                        mBindingView.recyclerSubscribe.refreshComplete();
                        mAdapter.addAll(datas.getResultList());
                        mAdapter.notifyDataSetChanged();
                    } else {
                        mBindingView.recyclerSubscribe.setNoMore(true);
                    }
                }

            }

            @Override
            public void loadFailed() {

                mBindingView.recyclerSubscribe.refreshComplete();

                if (mAdapter.getItemCount() == 0) {
                    //showError();
                }
                if (page > 1) {
                    page--;
                }
            }

            @Override
            public void addSubscription(Subscription subscription) {
                SubscribeFragment.this.addSubscription(subscription);
            }
        });

    }

    private void setAdapter(SubscribeBean subscribeBean) {
        mAdapter.clear();
        mAdapter.addAll(subscribeBean.getResultList());

        mBindingView.recyclerSubscribe.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBindingView.recyclerSubscribe.setItemAnimator(new DefaultItemAnimator());
        mBindingView.recyclerSubscribe.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mBindingView.recyclerSubscribe.refreshComplete();

        mIsFirst = false;

        mAdapter.setOnMenuClickListener(new OnMenuClickListener<DayRecommedBean.DataBean>() {
            @Override
            public void OnClick(View view, DayRecommedBean.DataBean dataBean, int position) {
                //popUpMyOverflow(view, dataBean);
            }
        });
    }

    @Override
    protected void onRefresh() {
        loadSubscribeData();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
