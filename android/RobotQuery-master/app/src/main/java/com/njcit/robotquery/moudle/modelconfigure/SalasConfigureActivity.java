package com.njcit.robotquery.moudle.modelconfigure;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.njcit.robotquery.R;
import com.njcit.robotquery.base.BaseActivity;
import com.njcit.robotquery.bean.local.TemplateBean;
import com.njcit.robotquery.bean.server.BaseBean;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.data.DataUtil;
import com.njcit.robotquery.databinding.ActivityOrderConfigureBinding;
import com.njcit.robotquery.databinding.ActivitySalasConfigureBinding;
import com.njcit.robotquery.http.ApiManage;
import com.njcit.robotquery.util.DateUtil;
import com.njcit.robotquery.util.JsonUtil;
import com.njcit.robotquery.util.SharedPreUtil;

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

public class SalasConfigureActivity extends BaseActivity<ActivitySalasConfigureBinding> implements View.OnClickListener {

    public static final String TAG = SalasConfigureActivity.class.getSimpleName();

    private Toolbar toolbarActivity;
    private TemplateBean mTemplateBean;
    private Map<String, String> mMap;    //用户选择的条件

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salas_configure);
        initView();
        initData();
    }

    private void initView() {

        showContentView();
        toolbarActivity = (Toolbar) findViewById(R.id.toolbar_activity);
        bindingView.linearSalasProductName.setOnClickListener(this);
        bindingView.linearSalasProductType.setOnClickListener(this);
        bindingView.linearSalasQuarter.setOnClickListener(this);

        setSupportActionBar(toolbarActivity);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    private void initData() {
        mMap = new HashMap<>();

        // 1、获取内存里面的mTemplateBean,先判断是否配置完了模板
        if (SharedPreUtil.getString(getApplicationContext(), Constants.TEMPLATE_SALAS_IS_FINISH, "").equals("1")) {
            mTemplateBean = (TemplateBean) SharedPreUtil.getObject(getApplicationContext(), Constants.TEMPLATE_SALAS);
            mMap = mTemplateBean != null ? mTemplateBean.getMap() : null;
        }

        if (null == mTemplateBean) {
            //没有的话,加载默认配置
            mTemplateBean = new TemplateBean();
            mTemplateBean.setUserId(SharedPreUtil.getUserId(getApplicationContext()));
            mTemplateBean.setRole(SharedPreUtil.getString(getApplicationContext(), Constants.RULE, ""));
            mTemplateBean.setTime(DateUtil.getCurrentTime());
            mTemplateBean.setTemplateId(Constants.TEMPLATE_SALAS);
            mTemplateBean.setType("text");

            Map<String, String> map = new HashMap<>();

            mTemplateBean.setMap(map);

        } else {

            //在页面中显示,先获取，在过滤
            if (mTemplateBean.getMap().get(Constants.SALAS_PRODUCTNAME) != null) {
                bindingView.tvSalasProductName.setText(DataUtil.filiterStringGroup(mTemplateBean.getMap().get(Constants.SALAS_PRODUCTNAME)));
            }
            if (mTemplateBean.getMap().get(Constants.SALAS_PRODUCTTYPE) != null) {
                bindingView.tvSalasProductType.setText(DataUtil.filiteString(mTemplateBean.getMap().get(Constants.SALAS_PRODUCTTYPE)));
            }
            if (mTemplateBean.getMap().get(Constants.SALAS_QUARTER) != null) {
                bindingView.tvSalasQuarter.setText(DataUtil.filiteString(mTemplateBean.getMap().get(Constants.SALAS_QUARTER)));
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_salas_productName:
                showSalasProductName();
                break;
            case R.id.linear_salas_productType:
                showSalasProductType();
                break;
            case R.id.linear_salas_quarter:
                showSalasQuarter();
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
        SharedPreUtil.saveObject(getApplicationContext(), Constants.TEMPLATE_SALAS, mTemplateBean);
        SharedPreUtil.setString(getApplicationContext(), Constants.TEMPLATE_SALAS_IS_FINISH, "1");
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
                        Toast.makeText(SalasConfigureActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean.getCode().equals("1")) {
                            bindingView.progressBar.setVisibility(View.GONE);
                            Toast.makeText(SalasConfigureActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(SalasConfigureActivity.this, "保存失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        addSubscription(subscription);

    }

    private void showSalasProductName() {
        final AlertDialog.Builder mDialog = new AlertDialog.Builder(this);

        mDialog.setTitle("温馨提示:");
        mDialog.setMessage("请输入产品名:");
        final View view = LayoutInflater.from(this).inflate(R.layout.dialog_acount_chose, null);
        mDialog.setView(view);


        mDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText et = (EditText) view.findViewById(R.id.et_acount_chose);
                String input = et.getText().toString().trim();
                //这里要用正则过滤一下
                if (!input.equals("") && !input.equals(Constants.NO)) {
                    bindingView.tvSalasProductName.setText(input);
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

    private void showSalasProductType() {
        final android.app.AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        // AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        mDialog.setCancelable(true);
        mDialog.setTitle("请选择:");
        final View view = LayoutInflater.from(this).inflate(R.layout.dialog_product_type, null);
        mDialog.setView(view);
        final Dialog dialog = mDialog.show();

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_product_type);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                //selectId 和checkedId 是一样的
                int selectId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) view.findViewById(selectId);
                String input = rb.getText().toString().trim();
                if (!input.equals("")) {
                    bindingView.tvSalasProductType.setText(rb.getText().toString().trim());
                    mMap.put(Constants.SALAS_PRODUCTTYPE, DataUtil.getMapValue(Constants.SALAS_PRODUCTTYPE, rb.getText().toString().trim()));
                }
                if (input.equals(Constants.NO)) {
                    mMap.remove(Constants.SALAS_PRODUCTTYPE);
                }
                dialog.dismiss();
            }
        });
    }

    private void showSalasQuarter() {
        final android.app.AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        mDialog.setCancelable(true);
        mDialog.setTitle("请选择:");
        final View view = LayoutInflater.from(this).inflate(R.layout.dialog_quarter_of_year, null);
        mDialog.setView(view);
        final Dialog dialog = mDialog.show();

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_product_type);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                //selectId 和checkedId 是一样的
                int selectId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) view.findViewById(selectId);
                String input = rb.getText().toString().trim();
                if (!input.equals("")) {
                    bindingView.tvSalasQuarter.setText(rb.getText().toString().trim());
                    mMap.put(Constants.SALAS_QUARTER, DataUtil.getMapValue(Constants.SALAS_QUARTER, rb.getText().toString().trim()));
                }
                if (input.equals(Constants.NO)) {
                    mMap.remove(Constants.SALAS_QUARTER);
                }
                dialog.dismiss();
            }
        });
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
        bindingView.tvSalasProductName.setText(Constants.NO);
        bindingView.tvSalasProductType.setText(Constants.NO);
        bindingView.tvSalasQuarter.setText(Constants.NO);
        mMap = new HashMap<>();
    }

}
