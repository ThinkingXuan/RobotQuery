package com.njcit.robotquery.moudle.modelconfigure;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.njcit.robotquery.R;
import com.njcit.robotquery.base.BaseActivity;
import com.njcit.robotquery.bean.local.TemplateBean;
import com.njcit.robotquery.bean.server.BaseBean;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.data.DataUtil;
import com.njcit.robotquery.databinding.ActivityOrderConfigureBinding;
import com.njcit.robotquery.http.ApiManage;
import com.njcit.robotquery.util.DateUtil;
import com.njcit.robotquery.util.JsonUtil;
import com.njcit.robotquery.util.SharedPreUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by youxuan on 2017/5/16 0016.
 */

public class OrderConfigureActivity extends BaseActivity<ActivityOrderConfigureBinding> implements View.OnClickListener, OnDateSetListener {

    public static final String TAG = OrderConfigureActivity.class.getSimpleName();

    private Toolbar toolbarActivity;
    private TemplateBean mTemplateBean;
    private Map<String, String> mMap;    //用户选择的条件
    private TimePickerDialog mDialogYearMonthDay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_configure);
        initView();
        initData();
    }

    private void initView() {
        mDialogYearMonthDay = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setTitleStringId("选择时间")
                .setMinMillseconds(2014)
                .setMaxMillseconds(2522880000000L)
                .setCallBack(this)
                .setThemeColor(getResources().getColor(R.color.primary))
                .build();
        mDialogYearMonthDay.setCancelable(false);

        showContentView();
        toolbarActivity = (Toolbar) findViewById(R.id.toolbar_activity);
        bindingView.linearOrderNumber.setOnClickListener(this);
        bindingView.linearOrderUser.setOnClickListener(this);
        bindingView.linearOrderStartDate.setOnClickListener(this);
        bindingView.linearOrderEndDate.setOnClickListener(this);

        bindingView.btChose.setOnClickListener(this);
        setSupportActionBar(toolbarActivity);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    private void initData() {
        mMap = new HashMap<>();

        // 1、获取内存里面的mTemplateBean,先判断是否配置完了模板
        if (SharedPreUtil.getString(getApplicationContext(), Constants.TEMPLATE_ORDER_IS_FINISH, "").equals("1")) {
            mTemplateBean = (TemplateBean) SharedPreUtil.getObject(getApplicationContext(), Constants.TEMPLATE_ORDER);
            mMap = mTemplateBean != null ? mTemplateBean.getMap() : null;
        }


        if (null == mTemplateBean) {
            //没有的话,加载默认配置
            mTemplateBean = new TemplateBean();
            mTemplateBean.setUserId(SharedPreUtil.getUserId(getApplicationContext()));
            mTemplateBean.setRole(SharedPreUtil.getString(getApplicationContext(), Constants.RULE, ""));
            mTemplateBean.setTime(DateUtil.getCurrentTime());
            mTemplateBean.setTemplateId(Constants.TEMPLATE_ORDER);
            mTemplateBean.setType("text");

            Map<String, String> map = new HashMap<>();

            mTemplateBean.setMap(map);

        } else {

            //在页面中显示,先获取，在过滤
            if (mTemplateBean.getMap().get(Constants.ORDER_NUMBER) != null) {
                bindingView.tvOrderNumber.setText(DataUtil.filiterStringGroup(mTemplateBean.getMap().get(Constants.ORDER_NUMBER)));
            }
            if (mTemplateBean.getMap().get(Constants.ORDER_USER) != null) {
                bindingView.tvOrderUser.setText(DataUtil.filiteString(mTemplateBean.getMap().get(Constants.ORDER_USER)));
            }
            if (mTemplateBean.getMap().get(Constants.ORDER_START_DATE) != null) {
                bindingView.tvOrderStartDate.setText(DataUtil.filiteString(mTemplateBean.getMap().get(Constants.ORDER_START_DATE)));
            }
            if (mTemplateBean.getMap().get(Constants.ORDER_END_DATE) != null) {
                bindingView.tvOrderEndDate.setText(DataUtil.filiteString(mTemplateBean.getMap().get(Constants.ORDER_END_DATE)));
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_order_number:
                showOrderNumber();
                break;
            case R.id.linear_order_user:
                showOrderUser();
                break;
            case R.id.linear_order_startDate:
                showOrderStartDate();
                break;
            case R.id.linear_order_endDate:
                showOrderEndDate();
                break;
            case R.id.bt_chose:
                //1、SharedPreference保存到本地
                SaveTemplate();
                //2、提交到服务器
                submit();
                break;
        }
    }

    private void SaveTemplate() {
        mTemplateBean.setTime(DateUtil.getCurrentTime());
        if (mMap.size() >= 0 && mMap != null) {
            mTemplateBean.setMap(mMap);
        } else {
            mTemplateBean.setMap(mMap);
        }
        SharedPreUtil.saveObject(getApplicationContext(), Constants.TEMPLATE_ORDER, mTemplateBean);
        SharedPreUtil.setString(getApplicationContext(), Constants.TEMPLATE_ORDER_IS_FINISH, "1");

        Log.d(TAG, "SaveTemplate: " + mTemplateBean.toString());
    }


    //提交服务器已Json形式:
    private void submit() {

        bindingView.progressBar.setVisibility(View.VISIBLE);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JsonUtil.getObjectJson(mTemplateBean));

        Subscription subscription = ApiManage.getInstence().getUpdatetModelService().updateModel(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        bindingView.progressBar.setVisibility(View.GONE);
                        Toast.makeText(OrderConfigureActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean.getCode().equals("1")) {
                            bindingView.progressBar.setVisibility(View.GONE);
                            Toast.makeText(OrderConfigureActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(OrderConfigureActivity.this, "保存失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        addSubscription(subscription);

    }

    private void showOrderNumber() {
        final android.app.AlertDialog.Builder mDialog = new AlertDialog.Builder(this);

        mDialog.setTitle("温馨提示:");
        mDialog.setMessage("请输入订单编号:");
        final View view = LayoutInflater.from(this).inflate(R.layout.dialog_acount_chose, null);
        mDialog.setView(view);


        mDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText et = (EditText) view.findViewById(R.id.et_acount_chose);
                String input = et.getText().toString().trim();
                //这里要用正则过滤一下
                if (!input.equals("") && !input.equals(Constants.NO)) {
                    bindingView.tvOrderNumber.setText(input);
                }
                mMap.put(Constants.ORDER_NUMBER, DataUtil.getMapValue(Constants.ORDER_NUMBER, input));
            }
        });
        mDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        mDialog.show();
    }

    private void showOrderUser() {
        final android.app.AlertDialog.Builder mDialog = new AlertDialog.Builder(this);

        mDialog.setTitle("温馨提示:");
        mDialog.setMessage("请输入订单人:");
        final View view = LayoutInflater.from(this).inflate(R.layout.dialog_acount_chose, null);
        mDialog.setView(view);


        mDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText et = (EditText) view.findViewById(R.id.et_acount_chose);
                String input = et.getText().toString().trim();
                //这里要用正则过滤一下
                if (!input.equals("") && !input.equals(Constants.NO)) {
                    bindingView.tvOrderUser.setText(input);
                }
                mMap.put(Constants.ORDER_USER, DataUtil.getMapValue(Constants.ORDER_USER, input));
            }
        });
        mDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        mDialog.show();
    }

    private void showOrderStartDate() {
        mDialogYearMonthDay.show(getSupportFragmentManager(), "startTime");


    }


    private void showOrderEndDate() {
        mDialogYearMonthDay.show(getSupportFragmentManager(), "endTime");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_actions_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_delete:
                clearTemplate();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void clearTemplate() {
        bindingView.tvOrderNumber.setText(Constants.NO);
        bindingView.tvOrderNumber.setText(Constants.NO);
        bindingView.tvOrderStartDate.setText(Constants.NO);
        bindingView.tvOrderEndDate.setText(Constants.NO);
        mMap = new HashMap<>();
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

        String dateStr_Day = DateUtil.getLongToStr_Day(millseconds);
        String dateStr_Second = DateUtil.getLongToStr_Sencond(millseconds);

        switch (timePickerView.getTag()) {
            case "startTime":
                if (!dateStr_Day.equals("") && !dateStr_Day.equals(Constants.NO)) {
                    bindingView.tvOrderStartDate.setText(dateStr_Day);
                }
                mMap.put(Constants.ORDER_START_DATE, DataUtil.getMapValue(Constants.ORDER_START_DATE, dateStr_Second));
                break;
            case "endTime":
                if (!dateStr_Day.equals("") && !dateStr_Day.equals(Constants.NO)) {
                    bindingView.tvOrderEndDate.setText(dateStr_Day);
                }
                mMap.put(Constants.ORDER_END_DATE, DataUtil.getMapValue(Constants.ORDER_END_DATE, dateStr_Second));
                break;
        }
    }
}
