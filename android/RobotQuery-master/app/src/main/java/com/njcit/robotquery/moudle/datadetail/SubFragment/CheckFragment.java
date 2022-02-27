package com.njcit.robotquery.moudle.datadetail.SubFragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.njcit.robotquery.R;
import com.njcit.robotquery.adapter.subAdapter.CheckDetailAdapter;
import com.njcit.robotquery.adapter.subAdapter.EmployeeDetailAdapter;
import com.njcit.robotquery.base.BaseFragment;
import com.njcit.robotquery.bean.server.AttendanceBean;
import com.njcit.robotquery.bean.server.CheckBean;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.databinding.FragmentBaseDetailBinding;
import com.njcit.robotquery.databinding.HeaderItemCountCustomBinding;

import java.util.List;

/**
 * Created by youxuan on 2017/4/30 0030.
 */

public class CheckFragment extends BaseFragment<FragmentBaseDetailBinding> {
    private CheckBean mCheckBean;
    public static final String TAG = "EmployeeFragment";

    private List<CheckBean.CheckData> mdatas;
    private CheckDetailAdapter mAdapter;
    private HeaderItemCountCustomBinding mHeaderBinding;
    private View mHeaderView;

    public static CheckFragment newInstance(Object o) {
        CheckBean employee = (CheckBean) o;
        Bundle args = new Bundle();
        args.putSerializable(Constants.TEMPLATE_CHECK,employee);
        CheckFragment fragment = new CheckFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCheckBean = (CheckBean) getArguments().get(Constants.TEMPLATE_CHECK);
        if (mCheckBean!=null){
            Log.d(TAG, "onActivityCreated: "+mCheckBean.toString());
            mdatas = mCheckBean.getObject();
            setAdatper();
        }

    }

    private void setAdatper() {
        showContentView();
        mHeaderBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.header_item_count_custom, null, false);
        mBindingView.recyclerEmployeeDetail.setLoadingMoreEnabled(false);
        mBindingView.recyclerEmployeeDetail.setPullRefreshEnabled(false);
        if (mHeaderView==null){
            mHeaderView = mHeaderBinding.getRoot();
            mBindingView.recyclerEmployeeDetail.addHeaderView(mHeaderView);
            mHeaderBinding.tvDataCount.setText(mdatas.size()+"");
        }
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mBindingView.recyclerEmployeeDetail.setLayoutManager(llm);
        mBindingView.recyclerEmployeeDetail.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new CheckDetailAdapter(getContext(),mdatas);
        mBindingView.recyclerEmployeeDetail.setAdapter(mAdapter);
    }
    @Override
    public int setContent() {
        return R.layout.fragment_base_detail;
    }
}
