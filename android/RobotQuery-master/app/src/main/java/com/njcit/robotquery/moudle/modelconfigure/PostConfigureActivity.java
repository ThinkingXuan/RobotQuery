package com.njcit.robotquery.moudle.modelconfigure;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.njcit.robotquery.R;
import com.njcit.robotquery.base.BaseActivity;
import com.njcit.robotquery.bean.local.TemplateBean;
import com.njcit.robotquery.bean.server.BaseBean;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.databinding.ActivityPostConfigureBinding;
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

public class PostConfigureActivity extends BaseActivity<ActivityPostConfigureBinding> implements View.OnClickListener{


    public static final String TAG = "PostConfigureActivity";
    private Toolbar toolbarActivity;
    private TemplateBean mTemplateBean;
    private Map<String, String> mMap;    //用户选择的条件

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_configure);
        initView();
        initData();
    }

    private void initData() {
        mMap = new HashMap<>();

        // 1、获取内存里面的mTemplateBean,先判断是否配置完了模板
        if (SharedPreUtil.getString(getApplicationContext(), Constants.TEMPLATE_POST_IS_FINISH, "").equals("1")) {
            mTemplateBean = (TemplateBean) SharedPreUtil.getObject(getApplicationContext(), Constants.TEMPLATE_POST);
            mMap = mTemplateBean != null ? mTemplateBean.getMap() : null;
        }

        if (null == mTemplateBean) {
            //没有的话,加载默认配置
            mTemplateBean = new TemplateBean();
            mTemplateBean.setUserId(SharedPreUtil.getUserId(getApplicationContext()));
            mTemplateBean.setRole(SharedPreUtil.getString(getApplicationContext(), Constants.RULE, ""));
            mTemplateBean.setTime(DateUtil.getCurrentTime());
            mTemplateBean.setTemplateId(Constants.TEMPLATE_POST);
            mTemplateBean.setType("text");
            Map<String, String> map = new HashMap<>();
            mTemplateBean.setMap(map);

        } else {
            //在页面中显示,先获取，在过滤
        }

    }

    private void initView() {
        showContentView();

        bindingView.btChosePost.setOnClickListener(this);
        toolbarActivity = (Toolbar) findViewById(R.id.toolbar_activity);
        setSupportActionBar(toolbarActivity);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bt_chose_post:

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
            SharedPreUtil.saveObject(getApplicationContext(), Constants.TEMPLATE_POST,mTemplateBean);
            SharedPreUtil.setString(getApplicationContext(), Constants.TEMPLATE_POST_IS_FINISH, "1");
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
                        Toast.makeText(PostConfigureActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean.getCode().equals("1")) {
                            bindingView.progressBar.setVisibility(View.GONE);
                            Toast.makeText(PostConfigureActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(PostConfigureActivity.this, "保存失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        addSubscription(subscription);

    }
}
