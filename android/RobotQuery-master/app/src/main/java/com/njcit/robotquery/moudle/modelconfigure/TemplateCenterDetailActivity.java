package com.njcit.robotquery.moudle.modelconfigure;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.njcit.robotquery.R;
import com.njcit.robotquery.adapter.TemplateCenterDetailAdapter;
import com.njcit.robotquery.base.BaseActivity;
import com.njcit.robotquery.databinding.ActivityTemplateDetailBinding;

/**
 * Created by youxuan on 2017/4/29 0029.
 * 模板中心详细页面
 */

public class TemplateCenterDetailActivity extends BaseActivity<ActivityTemplateDetailBinding> {
    private Toolbar toolbarActivity;
    private TemplateCenterDetailAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_detail);
        initView();

        mAdapter = new TemplateCenterDetailAdapter(getApplicationContext());
        LinearLayoutManager linear = new LinearLayoutManager(getApplicationContext());
        bindingView.recycelerTemplateCenterDetail.setLayoutManager(linear);
        bindingView.recycelerTemplateCenterDetail.setAdapter(mAdapter);

    }

    private void initView() {

        toolbarActivity = (Toolbar) findViewById(R.id.toolbar_activity);
        setSupportActionBar(toolbarActivity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        showContentView();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
