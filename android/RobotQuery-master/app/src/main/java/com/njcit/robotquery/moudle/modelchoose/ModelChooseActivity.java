package com.njcit.robotquery.moudle.modelchoose;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.njcit.robotquery.MainActivity;
import com.njcit.robotquery.R;
import com.njcit.robotquery.adapter.StringTagAdapter;
import com.njcit.robotquery.base.BaseActivity;
import com.njcit.robotquery.bean.local.TemplateBean;
import com.njcit.robotquery.bean.server.TemplateTagBean;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.databinding.ActivityModelChooseBinding;
import com.njcit.robotquery.http.ApiManage;
import com.njcit.robotquery.moudle.modelconfigure.AttendanceActivity;
import com.njcit.robotquery.moudle.modelconfigure.CheckConfigureActivity;
import com.njcit.robotquery.moudle.modelconfigure.EmployeeModelConfigureActivity;
import com.njcit.robotquery.moudle.modelconfigure.OrderConfigureActivity;
import com.njcit.robotquery.moudle.modelconfigure.PostConfigureActivity;
import com.njcit.robotquery.moudle.modelconfigure.ProductModelConfigureActivity;
import com.njcit.robotquery.moudle.modelconfigure.SalaryQueryConfigureActivity;
import com.njcit.robotquery.moudle.modelconfigure.SalasConfigureActivity;
import com.njcit.robotquery.moudle.modelconfigure.TrainActivity;
import com.njcit.robotquery.util.DateUtil;
import com.njcit.robotquery.util.SharedPreUtil;
import com.njcit.robotquery.util.UserStatusUtil;
import com.njcit.robotquery.view.flexbox.interfaces.OnFlexboxSubscribeListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by youxuan on 2017/4/5 0005.
 */

public class ModelChooseActivity extends BaseActivity<ActivityModelChooseBinding> implements View.OnClickListener {

    public static final String TAG = "ModelChooseActivity";

    private Toolbar toolbarActivity;
    public StringTagAdapter adapter;
    public List<TemplateTagBean.TagData> mTagData;
    private List<String> sourceData;
    private List<String> sourceDataUS;
    private String rule;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_choose);

        initData();
        initView();
    }

    private void initData() {

        userId = SharedPreUtil.getUserId(getApplicationContext());
        //获取TemplateTag
        Subscription subscription = ApiManage.getInstence().getUpdatetModelService().getTemplateTag(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TemplateTagBean>() {
                    @Override
                    public void onCompleted() {
                        bindingView.tvDataCount.setText(mTagData.size() + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ModelChooseActivity.this, "获取失败,请检查网络", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(TemplateTagBean templateTagBean) {
                        Log.d(TAG, "onNext: " + templateTagBean.toString());
                        if (templateTagBean.getCode().equals("1")) {
                            mTagData = templateTagBean.getObject();

                            sourceData = new ArrayList<>();
                            sourceDataUS = new ArrayList<>();

                            if (mTagData != null) {
                                for (int i = 0; i < mTagData.size(); i++) {
                                    sourceData.add(mTagData.get(i).getTagName());
                                    sourceDataUS.add(mTagData.get(i).getTemplateId());

                                }
                            }


                        }
                        setAdapter();
                    }

                });
        addSubscription(subscription);
    }


    private void initView() {

        toolbarActivity = (Toolbar) findViewById(R.id.toolbar_activity);
        setSupportActionBar(toolbarActivity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        showContentView();
        bindingView.btDone.setOnClickListener(this);

    }

    private void setAdapter() {
        adapter = new StringTagAdapter(ModelChooseActivity.this, sourceData, null);
        adapter.setOnSubscribeListener(new OnFlexboxSubscribeListener<String>() {
            @Override
            public void onSubscribe(List<String> selectedItem) {
                //String selectStr = selectedItem.get(0);

                //Log.d(TAG, "onSubscribe: " + selectedItem.toString());
                //Toast.makeText(ModelChooseActivity.this, "点击了:"+selectStr, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClickListener(String item) {
                Intent intent = null;
                switch (item) {
                    case "员工查询":
                        intent = new Intent(ModelChooseActivity.this, EmployeeModelConfigureActivity.class);
                        break;
                    case "产品查询":
                        intent = new Intent(ModelChooseActivity.this, ProductModelConfigureActivity.class);
                        break;
                    case "考勤":
                        intent = new Intent(ModelChooseActivity.this, AttendanceActivity.class);
                        break;
                    case "招聘":
                        intent = new Intent(ModelChooseActivity.this, PostConfigureActivity.class);
                        break;
                    case "员工培训":
                        intent = new Intent(ModelChooseActivity.this, TrainActivity.class);
                        break;
                    case "绩效考核":
                        intent = new Intent(ModelChooseActivity.this, CheckConfigureActivity.class);
                        break;
                    case "薪资查询":
                        intent = new Intent(ModelChooseActivity.this, SalaryQueryConfigureActivity.class);
                        break;
                    case "订单查询":
                        intent = new Intent(ModelChooseActivity.this, OrderConfigureActivity.class);
                        break;
                    case "销量查询":
                        intent = new Intent(ModelChooseActivity.this, SalasConfigureActivity.class);
                        break;

                }

                if (intent != null) {
                    startActivity(intent);
                }
            }
        });

        bindingView.flowLayout.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_done:

                //生成默认的查询模板
                saveQueryTemplate();
                Intent i = new Intent(ModelChooseActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }

    private void saveQueryTemplate() {


        //初始化模板查询语句
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < sourceDataUS.size(); i++) {
            TemplateBean templateBean = new TemplateBean();
            templateBean.setTime(DateUtil.getCurrentTime_day());
            templateBean.setUserId(SharedPreUtil.getUserId(getApplication()));
            templateBean.setType("text");
            templateBean.setRole(rule);
            templateBean.setMap(map);
            SharedPreUtil.saveObject(getApplication(), sourceDataUS.get(i), templateBean);
        }

        //保存完成
        UserStatusUtil.saveUserInfo(getApplicationContext(), Constants.TEMPLATE_IS_FINISH, "1");

    }

}
