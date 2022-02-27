package com.njcit.robotquery.moudle.datadetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.njcit.robotquery.R;
import com.njcit.robotquery.base.BaseActivity;
import com.njcit.robotquery.bean.server.Employee;
import com.njcit.robotquery.bean.server.EmployeeBean;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.databinding.ActivityDataDetailBinding;
import com.njcit.robotquery.moudle.datadetail.SubFragment.AttendanceFragment;
import com.njcit.robotquery.moudle.datadetail.SubFragment.CheckFragment;
import com.njcit.robotquery.moudle.datadetail.SubFragment.EmployeeFragment;
import com.njcit.robotquery.moudle.datadetail.SubFragment.OrderFragment;
import com.njcit.robotquery.moudle.datadetail.SubFragment.PostFragment;
import com.njcit.robotquery.moudle.datadetail.SubFragment.ProductFragment;
import com.njcit.robotquery.moudle.datadetail.SubFragment.SalesAcountFragment;
import com.njcit.robotquery.moudle.datadetail.SubFragment.TrainFragment;


/**
 * Created by youxuan on 2017/4/14 0014.
 * 1、聊天数据查询细节图表。表格
 * 2、数据查询的细节
 *
 *
 * 3、查询历史
 */

public class DataDetailActivity<T> extends BaseActivity<ActivityDataDetailBinding> {

    public static final String TAG = "DataDetailActivity";
    private Toolbar toolbarActivity;

    private Fragment currentFragment;
    private Fragment replaceFragment;
    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_detail);
        initView();

        initData();
    }

    private void initView() {

        toolbarActivity = (Toolbar) findViewById(R.id.toolbar_activity);
        setSupportActionBar(toolbarActivity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        showContentView();
    }

    private void initData() {
        type = getIntent().getStringExtra(Constants.ACTIVIIY_BUNDLE_KEY_TYPE);
        Log.d(TAG, "initData: " + type);

        Object obj = getIntent().getSerializableExtra(Constants.ACTIVIIY_BUNDLE_KEY_OBJ);
        Log.d(TAG, "initData: " + obj.toString());
        replaceFragment = getFragment(type, obj);
        switchFragment(replaceFragment, "员工查询");
    }


    private Fragment getFragment(String type, Object o) {
        Fragment fragment = null;

        switch (type) {
            case Constants.TEMPLATE_EMPLOYYEE:
                fragment = EmployeeFragment.newInstance(o);
                toolbarActivity.setTitle("员工查询");
                break;
            case Constants.TEMPLATE_ATTENDANCE:
                fragment = new AttendanceFragment().newInstance(o);
                break;
            case Constants.TEMPLATE_POST:
                fragment = new PostFragment().newInstance(o);
                break;
            case Constants.TEMPLATE_CHECK:
                fragment = new CheckFragment().newInstance(o);
                break;
            case Constants.TEMPLATE_TRAIN:
                fragment = new TrainFragment().newInstance(o);
                break;
            case Constants.TEMPLATE_SALESACOUNT:
                fragment = new SalesAcountFragment().newInstance();
                break;
            case Constants.TEMPLATE_PRODUCT:
                fragment = new ProductFragment().newInstance(o);
                break;
            case Constants.TEMPLATE_ORDER:
                fragment = new OrderFragment().newInstance(o);
                break;

        }

        return fragment;
    }

    private void switchFragment(Fragment fragment, String title) {
        if (currentFragment == null || !currentFragment.getClass().getName().equals(fragment.getClass())) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.data_detail_container, fragment)
                    .commit();
            currentFragment = fragment;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
