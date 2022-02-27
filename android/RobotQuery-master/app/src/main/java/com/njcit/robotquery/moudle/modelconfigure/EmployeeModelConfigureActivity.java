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
import com.njcit.robotquery.databinding.ActivityModelConfigureBinding;
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
 * Created by youxuan on 2017/4/9 0009.
 */

public class EmployeeModelConfigureActivity extends BaseActivity<ActivityModelConfigureBinding> implements View.OnClickListener {

    public static final String TAG = "EmployeeModelConfigure";
    private Toolbar toolbarActivity;
    private TemplateBean mTemplateBean;
    private Map<String, String> mMap;    //用户选择的条件


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_configure);
        initView();
        initData();
    }

    private void initData() {
        mMap = new HashMap<>();

        // 1、获取内存里面的mTemplateBean,先判断是否配置完了模板
        if (SharedPreUtil.getString(getApplicationContext(), Constants.TEMPLATE_EMPLOYEE_IS_FINISH, "").equals("1")) {
            mTemplateBean = (TemplateBean) SharedPreUtil.getObject(getApplicationContext(), Constants.TEMPLATE_EMPLOYYEE);
            mMap = mTemplateBean != null ? mTemplateBean.getMap() : null;
        }


        if (null == mTemplateBean) {
            //没有的话,加载默认配置
            mTemplateBean = new TemplateBean();
            mTemplateBean.setUserId(SharedPreUtil.getUserId(getApplicationContext()));
            mTemplateBean.setRole(SharedPreUtil.getString(getApplicationContext(), Constants.RULE, ""));
            mTemplateBean.setTime(DateUtil.getCurrentTime());
            mTemplateBean.setTemplateId(Constants.TEMPLATE_EMPLOYYEE);
            mTemplateBean.setType("text");

            Map<String, String> map = new HashMap<>();

            mTemplateBean.setMap(map);

        } else {

            //在页面中显示,先获取，在过滤
            if (mTemplateBean.getMap().get(Constants.EMPLOYEE_AGE) != null) {
                bindingView.tvUserAge.setText(DataUtil.filiterStringGroup(mTemplateBean.getMap().get(Constants.EMPLOYEE_AGE)));
            }
            if (mTemplateBean.getMap().get(Constants.EMPLOYEE_USERNAME) != null) {
                bindingView.tvUserName.setText(DataUtil.filiteString(mTemplateBean.getMap().get(Constants.EMPLOYEE_USERNAME)));
            }
            if (mTemplateBean.getMap().get(Constants.EMPLOYEE_GENDER) != null) {
                bindingView.tvUserGender.setText(DataUtil.filiteString(mTemplateBean.getMap().get(Constants.EMPLOYEE_GENDER)));
            }
            if (mTemplateBean.getMap().get(Constants.EMPLOYEE_POSTION) != null) {
                bindingView.tvTime01.setText(DataUtil.filiteString(mTemplateBean.getMap().get(Constants.EMPLOYEE_POSTION)));
            }
            if (mTemplateBean.getMap().get(Constants.EMPLOYEE_SALARY) != null) {
                bindingView.tvChooseSalary.setText(DataUtil.filiterStringGroup(mTemplateBean.getMap().get(Constants.EMPLOYEE_SALARY)));
            }

        }

    }

    private void initView() {
        showContentView();
        bindingView.linearUserName.setOnClickListener(this);
        bindingView.linearUserGender.setOnClickListener(this);
        bindingView.linearUserAge.setOnClickListener(this);
        bindingView.linearWorkTime.setOnClickListener(this);
        bindingView.linearChooseSalary.setOnClickListener(this);
        bindingView.btChose.setOnClickListener(this);


        toolbarActivity = (Toolbar) findViewById(R.id.toolbar_activity);
        setSupportActionBar(toolbarActivity);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.linear_user_name:
                showUserNameDialog();
                break;
            case R.id.linear_user_gender:
                showGenderDialog();
                break;
            case R.id.linear_user_age:
                showAgeDialog();
                break;
            case R.id.linear_work_time:
                showTimeDialog();
                break;

            case R.id.linear_choose_salary:
                showSalaryDialog();
                break;
            case R.id.bt_chose:

                //1、SharedPreference保存到本地
                SaveTemplate();
                //2、提交到服务器
                submit();

        }
    }

    private void SaveTemplate() {
        mTemplateBean.setTime(DateUtil.getCurrentTime());
        if (mMap.size()>=0&& mMap!=null) {
            mTemplateBean.setMap(mMap);
        }else {
            mTemplateBean.setMap(mMap);
        }
        SharedPreUtil.saveObject(getApplicationContext(), Constants.TEMPLATE_EMPLOYYEE, mTemplateBean);
        SharedPreUtil.setString(getApplicationContext(), Constants.TEMPLATE_EMPLOYEE_IS_FINISH, "1");

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
                        Toast.makeText(EmployeeModelConfigureActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean.getCode().equals("1")) {
                            bindingView.progressBar.setVisibility(View.GONE);
                            Toast.makeText(EmployeeModelConfigureActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(EmployeeModelConfigureActivity.this, "保存失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        addSubscription(subscription);

    }

    private void showAgeDialog() {

        final android.app.AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        mDialog.setCancelable(true);
        mDialog.setTitle("请输入用户年龄:");
        final View view = LayoutInflater.from(this).inflate(R.layout.dialog_range_choose, null);
        mDialog.setView(view);


        mDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText et1 = (EditText) view.findViewById(R.id.et_range_1);
                EditText et2 = (EditText) view.findViewById(R.id.et_range_2);

                String input1 = et1.getText().toString().trim();
                String input2 = et2.getText().toString().trim();
                //这里要用正则过滤一下
                if (!input1.equals("") && !input2.equals("")) {
                    bindingView.tvUserAge.setText(input1 + " - " + input2);
                }
                mMap.put(Constants.EMPLOYEE_AGE, DataUtil.getMapValue(Constants.EMPLOYEE_AGE, input1, input2));
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

    private void showGenderDialog() {
        final android.app.AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        mDialog.setCancelable(true);
        mDialog.setTitle("请选择:");
        final View view = LayoutInflater.from(this).inflate(R.layout.dialog_gender_chose, null);
        mDialog.setView(view);
        final Dialog dialog = mDialog.show();

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_group_gender);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                //selectId 和checkedId 是一样的
                int selectId = group.getCheckedRadioButtonId();
                Log.d("onCheckedChanged", "onCheckedChanged: " + selectId + ":" + checkedId);
                RadioButton rb = (RadioButton) view.findViewById(selectId);
                String input = rb.getText().toString().trim();

                if (!input.equals("")) {
                    bindingView.tvUserGender.setText(rb.getText().toString().trim());
                    mMap.put(Constants.EMPLOYEE_GENDER, DataUtil.getMapValue(Constants.EMPLOYEE_GENDER, rb.getText().toString().trim()));
                }
                if (input.equals(Constants.NO)) {
                    mMap.remove(Constants.EMPLOYEE_GENDER);
                }

                dialog.dismiss();
            }
        });

    }


    private void showTimeDialog() {
        final android.app.AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        // AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        mDialog.setCancelable(true);
        mDialog.setTitle("请选择:");
        final View view = LayoutInflater.from(this).inflate(R.layout.dialog_time_chose, null);
        mDialog.setView(view);
        final Dialog dialog = mDialog.show();

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_group_time);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                //selectId 和checkedId 是一样的
                int selectId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) view.findViewById(selectId);
                String input = rb.getText().toString().trim();
                if (!input.equals("")) {
                    bindingView.tvWorkTime.setText(rb.getText().toString().trim());
                    mMap.put(Constants.EMPLOYEE_POSTION, DataUtil.getMapValue(Constants.EMPLOYEE_POSTION, rb.getText().toString().trim()));
                }
                if (input.equals(Constants.NO)) {
                    mMap.remove(Constants.EMPLOYEE_POSTION);
                }
                dialog.dismiss();
            }
        });

    }

    private void showUserNameDialog() {
        final android.app.AlertDialog.Builder mDialog = new AlertDialog.Builder(this);

        mDialog.setTitle("温馨提示:");
        mDialog.setMessage("请输入你要用户姓名:");
        final View view = LayoutInflater.from(this).inflate(R.layout.dialog_acount_chose, null);
        mDialog.setView(view);


        mDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText et = (EditText) view.findViewById(R.id.et_acount_chose);
                String input = et.getText().toString().trim();
                //这里要用正则过滤一下
                if (!input.equals("") && !input.equals(Constants.NO)) {
                    bindingView.tvUserName.setText(input);
                }
                mMap.put(Constants.EMPLOYEE_USERNAME, DataUtil.getMapValue(Constants.EMPLOYEE_USERNAME, input));
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

    private void showSalaryDialog() {
        final android.app.AlertDialog.Builder mDialog = new AlertDialog.Builder(this);

        mDialog.setTitle("温馨提示:");
        mDialog.setMessage("请输入员工工资:");
        final View view = LayoutInflater.from(this).inflate(R.layout.dialog_range_choose, null);
        mDialog.setView(view);


        mDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText et1 = (EditText) view.findViewById(R.id.et_range_1);
                EditText et2 = (EditText) view.findViewById(R.id.et_range_2);

                String input1 = et1.getText().toString().trim();
                String input2 = et2.getText().toString().trim();
                //这里要用正则过滤一下
                if (!input1.equals("") && !input2.equals("")) {
                    bindingView.tvChooseSalary.setText(input1 + " - " + input2 + "元");
                }
                mMap.put(Constants.EMPLOYEE_SALARY, DataUtil.getMapValue(Constants.EMPLOYEE_SALARY, input1, input2));
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
        bindingView.tvUserAge.setText(Constants.NO);
        bindingView.tvUserName.setText(Constants.NO);
        bindingView.tvUserGender.setText(Constants.NO);
        bindingView.tvTime01.setText(Constants.NO);
        bindingView.tvChooseSalary.setText(Constants.NO);
        mMap = new HashMap<>();
    }
}



