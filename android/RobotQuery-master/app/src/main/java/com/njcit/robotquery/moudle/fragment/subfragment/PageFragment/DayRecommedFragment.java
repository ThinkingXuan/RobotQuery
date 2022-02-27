package com.njcit.robotquery.moudle.fragment.subfragment.PageFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.njcit.robotquery.R;
import com.njcit.robotquery.adapter.DayRecommedAdapter;
import com.njcit.robotquery.adapter.listener.OnItemClickListener;
import com.njcit.robotquery.adapter.listener.OnMenuClickListener;
import com.njcit.robotquery.base.BaseApplication;
import com.njcit.robotquery.base.BaseFragment;
import com.njcit.robotquery.bean.server.BaseBean;
import com.njcit.robotquery.bean.server.DayRecommedBean;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.databinding.FragmentDayrecommedBinding;
import com.njcit.robotquery.http.ApiManage;
import com.njcit.robotquery.adapter.listener.RequestImpl;
import com.njcit.robotquery.http.cache.ACache;
import com.njcit.robotquery.moudle.datadetail.DataDetailActivity;
import com.njcit.robotquery.util.CommonUtil;
import com.njcit.robotquery.util.SharedPreUtil;


import java.io.Serializable;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by youxuan on 2017/4/14 0014.
 */

public class DayRecommedFragment extends BaseFragment<FragmentDayrecommedBinding> {

    public static final String TAG = "DayRecommedFragment";
    private String userId;
    private ACache mAache;
    private DayRecommedBean datas;
    private DayRecommedModel mModel;
    private int page = 1;
    private int size = 10;
    private boolean mIsPrepared = true;
    private boolean mIsFirst = true;
    private DayRecommedAdapter mAdapter;

    public static DayRecommedFragment newInstance() {
        return new DayRecommedFragment();
    }


    @Override
    public int setContent() {
        Log.d(TAG, "setContent: ");
        return R.layout.fragment_dayrecommed;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAache = ACache.get(getContext());

        mAdapter = new DayRecommedAdapter();
        mAdapter.setOnItemClickListener(new OnItemClickListener<DayRecommedBean.DataBean>() {
            @Override
            public void onClick(DayRecommedBean.DataBean dataBean, int position) {
                Intent intent = new Intent(getActivity(), DataDetailActivity.class);
                intent.putExtra(Constants.ACTIVIIY_BUNDLE_KEY_TYPE,Constants.TEMPLATE_SALESACOUNT);
                intent.putExtra(Constants.ACTIVIIY_BUNDLE_KEY_OBJ, "");
                startActivity(intent);
            }
        });

        mBindingView.xrvDayRecommed.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {         //下拉刷新,加载第一页数据
                page = 1;
                loadDayRecommedData();
            }

            @Override
            public void onLoadMore() {        //上滑刷新,加载下一页数据
                page++;
                loadDayRecommedData();
            }
        });

        // 准备就绪
        mIsPrepared = true;
        Log.d(TAG, "onActivityCreated: ");
    }

    @Override
    protected void loadData() {
        if (!mIsPrepared || !mIsVisible || !mIsFirst) {
            return;
        }
        if (datas != null && datas.getResultList() != null &&
                datas.getResultList().size() > 0) {
            showContentView();
            datas = (DayRecommedBean) mAache.getAsObject(Constants.DAY_RECOMMED_DATA);
            setAdapter(datas);
        } else {
            loadDayRecommedData();
        }
    }

    private void loadDayRecommedData() {
        userId = SharedPreUtil.getUserId(BaseApplication.getInstance());

        Log.d(TAG, "loadDayRecommedData: " + System.currentTimeMillis());
        mModel = new DayRecommedModel();
        mModel.setData(userId, page, size);
        mModel.getRecommedData(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                showContentView();
                DayRecommedBean dayRecommedBean = (DayRecommedBean) object;
                if (page == 1) {
                    if (dayRecommedBean != null && dayRecommedBean.getResultList() != null && dayRecommedBean.getResultList().size() > 0) {
                        setAdapter(dayRecommedBean);
                        mAache.remove(Constants.DAY_RECOMMED_DATA);
                        // 缓存50分钟
                        mAache.put(Constants.DAY_RECOMMED_DATA, dayRecommedBean, 30000);
                    }
                } else {
                    if (dayRecommedBean != null && dayRecommedBean.getResultList() != null && dayRecommedBean.getResultList().size() > 0) {

                        mBindingView.xrvDayRecommed.refreshComplete();
                        mAdapter.addAll(dayRecommedBean.getResultList());
                        mAdapter.notifyDataSetChanged();
                    } else {
                        mBindingView.xrvDayRecommed.setNoMore(true);
                    }
                }
            }

            @Override
            public void loadFailed() {
                mBindingView.xrvDayRecommed.refreshComplete();
                if (mAdapter.getItemCount() == 0) {
                    showError();
                }
                if (page > 1) {
                    page--;
                }
            }

            @Override
            public void addSubscription(Subscription subscription) {
                DayRecommedFragment.this.addSubscription(subscription);
            }
        });

    }


    private void setAdapter(DayRecommedBean mbean) {
        mAdapter.clear();
        mAdapter.addAll(mbean.getResultList());

        mBindingView.xrvDayRecommed.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBindingView.xrvDayRecommed.setItemAnimator(new DefaultItemAnimator());
        mBindingView.xrvDayRecommed.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mBindingView.xrvDayRecommed.refreshComplete();

        mIsFirst = false;


        mAdapter.setOnMenuClickListener(new OnMenuClickListener<DayRecommedBean.DataBean>() {
            @Override
            public void OnClick(View view, DayRecommedBean.DataBean dataBean, int position) {
                popUpMyOverflow(view, dataBean, position);
            }
        });

    }


    @Override
    protected void onRefresh() {
        loadDayRecommedData();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }

    //显示PopupWindow
    public void popUpMyOverflow(View v, final DayRecommedBean.DataBean dataBean, final int position) {

        //加载布局
        final View popView = getActivity().getLayoutInflater().inflate(R.layout.action_meun_item, null);
        //构建PopupWindow
        final PopupWindow popWind = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);//popView即popupWindow的布局，ture设置focusAble.

        //必须设置BackgroundDrawable后setOutsideTouchable(true)才会有效。这里在XML中定义背景，所以这里设置为null;
        popWind.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        popWind.setOutsideTouchable(true); //点击外部关闭。
        popWind.setAnimationStyle(android.R.style.Animation_Dialog);    //设置一个动画。

        //计算位置布局出现的位置
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        popWind.showAtLocation(v, Gravity.NO_GRAVITY, location[0] - popWind.getWidth() - CommonUtil.Dp2Px(getContext(), 80), location[1] - popWind.getHeight() + CommonUtil.Dp2Px(getContext(), 20));

        LinearLayout linearSubscribeItem;
        LinearLayout linearCancelItem;

        linearSubscribeItem = (LinearLayout) popView.findViewById(R.id.linear_subscribe_item);
        linearCancelItem = (LinearLayout) popView.findViewById(R.id.linear_cancel_item);

        //点击订阅
        linearSubscribeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subscription subscription = ApiManage.getInstence().getQueryService().subscribe(userId, dataBean.getTemplateId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<BaseBean>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(getContext(), "订阅失败,请检查网络连接", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(BaseBean baseBean) {
                                if (popWind.isShowing()) {
                                    popWind.dismiss();
                                }
                                if (baseBean.getCode().equals("1")) {

                                    Toast.makeText(getContext(), "订阅成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), "订阅失败,请检查网络连接", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                addSubscription(subscription);
            }
        });

        //点击取消订阅
        linearCancelItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subscription subscription = ApiManage.getInstence().getQueryService().cancelSubscribe(userId, dataBean.getTemplateId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<BaseBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(getContext(), "取消订阅失败,请检查网络连接", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(BaseBean baseBean) {
                                if (popWind.isShowing()) {
                                    popWind.dismiss();
                                }
                                if (baseBean.getCode().equals("1")) {

                                    Toast.makeText(getContext(), "取消订阅成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), "取消订阅失败,请检查网络连接", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                addSubscription(subscription);
            }
        });

//        linearCancelItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAdapter.remove(position);
//                mAdapter.notifyDataSetChanged();
//                if (popWind.isShowing()) {
//                    popWind.dismiss();
//                }
//                Toast.makeText(getContext(), "取消关注成功:"+position, Toast.LENGTH_SHORT).show();
//
//            }
//        });


    }

}
