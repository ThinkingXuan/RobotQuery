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
import com.njcit.robotquery.adapter.subAdapter.AttendanceDetailAdapter;
import com.njcit.robotquery.adapter.subAdapter.EmployeeDetailAdapter;
import com.njcit.robotquery.base.BaseFragment;
import com.njcit.robotquery.bean.server.AttendanceBean;
import com.njcit.robotquery.bean.server.EmployeeBean;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.databinding.FragmentBaseDetailBinding;
import com.njcit.robotquery.databinding.HeaderItemCountCustomBinding;

import java.util.List;

/**
 * Created by youxuan on 2017/4/30 0030.
 */

public class AttendanceFragment extends BaseFragment<FragmentBaseDetailBinding> {

    private AttendanceBean employee;
    public static final String TAG = "EmployeeFragment";

    private List<AttendanceBean.AttendanceData> mdatas;
    private AttendanceDetailAdapter mAdapter;
    private HeaderItemCountCustomBinding mHeaderBinding;
    private View mHeaderView;
    public static AttendanceFragment newInstance(Object o) {
        AttendanceBean employee = (AttendanceBean) o;
        Bundle args = new Bundle();
        args.putSerializable(Constants.TEMPLATE_ATTENDANCE,employee);
        AttendanceFragment fragment = new AttendanceFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        employee = (AttendanceBean) getArguments().get(Constants.TEMPLATE_ATTENDANCE);
        if (employee!=null){
            Log.d(TAG, "onActivityCreated: "+employee.toString());
            mdatas = employee.getObject();
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
        mAdapter = new AttendanceDetailAdapter(getContext(),mdatas);
        mBindingView.recyclerEmployeeDetail.setAdapter(mAdapter);
    }
    @Override
    public int setContent() {
        return R.layout.fragment_base_detail;
    }


}
