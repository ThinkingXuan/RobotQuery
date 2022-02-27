package com.njcit.robotquery.moudle.modelconfigure;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.njcit.robotquery.R;
import com.njcit.robotquery.adapter.ModelListAdapter;
import com.njcit.robotquery.base.BaseActivity;
import com.njcit.robotquery.base.BaseApplication;
import com.njcit.robotquery.base.BaseFragment;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.databinding.ActivityModelListBinding;
import com.njcit.robotquery.util.SharedPreUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youxuan on 2017/4/8 0008.
 */


public class ModelListFragment extends BaseFragment<ActivityModelListBinding> {
    private List<String> mModelList; //模板集合
    private ModelListAdapter mAdapter;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initAdapter();
    }

    @Override
    public int setContent() {
        return R.layout.activity_model_list;
    }

    private void initAdapter() {
        LinearLayoutManager mLayoutManger = new LinearLayoutManager(getActivity());
        mBindingView.recyclerModelList.setLayoutManager(mLayoutManger);
        mAdapter = new ModelListAdapter(getActivity(),mModelList);
        mBindingView.recyclerModelList.setAdapter(mAdapter);

    }

    private void initView() {
        showContentView();


    }

    private void initData(){
        mModelList = new ArrayList<>();
        String rule = SharedPreUtil.getString(BaseApplication.getInstance(), Constants.RULE,"");
        switch (rule) {
            case "老板":
                mModelList.add("员工查询");
                mModelList.add("员工培训");
                mModelList.add("绩效考核");
                mModelList.add("考勤查询");
                mModelList.add("招聘查询");

                mModelList.add("产品查询");
                mModelList.add("订单查询");
                mModelList.add("销量查询");

                break;
            case "人事部经理":
                mModelList.add("员工查询");
                mModelList.add("员工培训");
                mModelList.add("绩效考核");
                mModelList.add("考勤查询");
                mModelList.add("招聘查询");

                break;
            case "普通员工":
                mModelList.add("我的信息");
                mModelList.add("职位查询");
                mModelList.add("工作历史");
                mModelList.add("客户代表");
                mModelList.add("技术主管");
                break;
            case "销售部经理":
                mModelList.add("员工查询");
                mModelList.add("产品查询");
                mModelList.add("订单查询");
                mModelList.add("销量查询");
                break;
        }
    }

}
