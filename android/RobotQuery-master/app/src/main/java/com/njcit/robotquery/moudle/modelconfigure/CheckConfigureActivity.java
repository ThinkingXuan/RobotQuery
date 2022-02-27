package com.njcit.robotquery.moudle.modelconfigure;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.njcit.robotquery.R;
import com.njcit.robotquery.base.BaseActivity;
import com.njcit.robotquery.bean.local.TemplateBean;
import com.njcit.robotquery.bean.server.BaseBean;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.data.DataUtil;
import com.njcit.robotquery.databinding.ActivityCheckConfigureBinding;
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
 * Created by youxuan on 2017/4/24 0024.
 */

public class CheckConfigureActivity extends BaseActivity<ActivityCheckConfigureBinding>implements View.OnClickListener {

    public static final String TAG = "CheckConfigureActivity";
    private Toolbar toolbarActivity;
    private TextView tvCheckConfigUsername;
    private LinearLayout linearCheckConfigureName;
    private TemplateBean mTemplateBean;
    private Map<String, String> mMap;    //用户选择的条件
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_configure);
        initView();
        initData();
    }

    private void initData() {
        mMap = new HashMap<>();

        // 1、获取内存里面的mTemplateBean,先判断是否配置完了模板
        if (SharedPreUtil.getString(getApplicationContext(), Constants.TEMPLATE_CHECK_IS_FINISH, "").equals("1")) {
            mTemplateBean = (TemplateBean) SharedPreUtil.getObject(getApplicationContext(), Constants.TEMPLATE_CHECK);
            mMap = mTemplateBean != null ? mTemplateBean.getMap() : null;
        }

        if (null == mTemplateBean) {
            //没有的话,加载默认配置
            mTemplateBean = new TemplateBean();
            mTemplateBean.setUserId(SharedPreUtil.getUserId(getApplicationContext()));
            mTemplateBean.setRole(SharedPreUtil.getString(getApplicationContext(), Constants.RULE, ""));
            mTemplateBean.setTime(DateUtil.getCurrentTime());
            mTemplateBean.setTemplateId(Constants.TEMPLATE_CHECK);
            mTemplateBean.setType("text");
            Map<String, String> map = new HashMap<>();
            mTemplateBean.setMap(map);

        } else {
            //在页面中显示,先获取，在过滤
            if (mTemplateBean.getMap().get(Constants.CHECK_USERID) != null) {
                bindingView.tvCheckConfigUsername.setText(DataUtil.filiteString(mTemplateBean.getMap().get(Constants.CHECK_USERID)));
            }
        }

    }

    private void initView() {
        showContentView();

        bindingView.btChoseCheck.setOnClickListener(this);
        toolbarActivity = (Toolbar) findViewById(R.id.toolbar_activity);
        linearCheckConfigureName = (LinearLayout) findViewById(R.id.linear_check_configure_name);
        linearCheckConfigureName.setOnClickListener(this);

        setSupportActionBar(toolbarActivity);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvCheckConfigUsername = (TextView) findViewById(R.id.tv_check_config_username);

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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.linear_check_configure_name:
                showUserNameDialog();
                break;
            case R.id.bt_chose_check:

                //1、SharedPreference保存到本地
                SaveTemplate();
                //2、提交到服务器
                submit();

        }
    }

    private void SaveTemplate() {

        if (mMap.size() >= 0 && mMap != null) {
            mTemplateBean.setTime(DateUtil.getCurrentTime());
            mTemplateBean.setMap(mMap);
            SharedPreUtil.saveObject(getApplicationContext(), Constants.TEMPLATE_CHECK,mTemplateBean);
            SharedPreUtil.setString(getApplicationContext(), Constants.TEMPLATE_CHECK_IS_FINISH, "1");
        }
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
                        Toast.makeText(CheckConfigureActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean.getCode().equals("1")) {
                            bindingView.progressBar.setVisibility(View.GONE);
                            Toast.makeText(CheckConfigureActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(CheckConfigureActivity.this, "保存失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        addSubscription(subscription);

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
                    bindingView.tvCheckConfigUsername.setText(input);
                }
                mMap.put(Constants.CHECK_USERID,input);
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


    private void clearTemplate(){
        bindingView.tvCheckConfigUsername.setText(Constants.NO);
        mMap = new HashMap<>();
    }

}
