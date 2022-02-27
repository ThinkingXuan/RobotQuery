package com.njcit.robotquery.moudle.modelconfigure;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.njcit.robotquery.R;
import com.njcit.robotquery.base.BaseActivity;
import com.njcit.robotquery.bean.local.TemplateBean;
import com.njcit.robotquery.bean.server.BaseBean;
import com.njcit.robotquery.bean.server.Products;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.data.DataUtil;
import com.njcit.robotquery.databinding.ActivityProductConfigureBinding;
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
 * Created by youxuan on 2017/4/20 0020.
 */

public class ProductModelConfigureActivity extends BaseActivity<ActivityProductConfigureBinding> implements View.OnClickListener {

    public static final String TAG = ProductModelConfigureActivity.class.getSimpleName();

    private Toolbar toolbarActivity;
    private LinearLayout linearProductName;
    private TextView tvProductProductName;
    private LinearLayout linearProductType;
    private TextView tvProductProductType;
    private LinearLayout linearProductPrice;
    private TextView tvProductProductPrice;
    private ImageView imageView3;
    private LinearLayout linearProductStorageCount;
    private TextView tvProductStorageCount;
    private Button btChose;
    private ProgressBar progressBar;

    private TemplateBean mTemplateBean;
    private Map<String, String> mMap;    //用户选择的条件

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_configure);
        initView();
        initData();
    }

    private void initView() {
        showContentView();
        toolbarActivity = (Toolbar) findViewById(R.id.toolbar_activity);
        setSupportActionBar(toolbarActivity);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        linearProductName = (LinearLayout) findViewById(R.id.linear_productName);
        tvProductProductName = (TextView) findViewById(R.id.tv_product_productName);
        linearProductType = (LinearLayout) findViewById(R.id.linear_productType);
        tvProductProductType = (TextView) findViewById(R.id.tv_product_productType);
        linearProductPrice = (LinearLayout) findViewById(R.id.linear_productPrice);
        tvProductProductPrice = (TextView) findViewById(R.id.tv_product_productPrice);
        linearProductStorageCount = (LinearLayout) findViewById(R.id.linear_product_storageCount);
        tvProductStorageCount = (TextView) findViewById(R.id.tv_product_storageCount);
        btChose = (Button) findViewById(R.id.bt_chose);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        linearProductName.setOnClickListener(this);
        linearProductPrice.setOnClickListener(this);
        linearProductType.setOnClickListener(this);
        linearProductStorageCount.setOnClickListener(this);
        btChose.setOnClickListener(this);

    }

    private void initData() {
        mMap = new HashMap<>();

        // 1、获取内存里面的mTemplateBean,先判断是否配置完了模板
        if (SharedPreUtil.getString(getApplicationContext(), Constants.TEMPLATE_PROUDUT_IS_FINISH, "").equals("1")) {
            mTemplateBean = (TemplateBean) SharedPreUtil.getObject(getApplicationContext(), Constants.TEMPLATE_PRODUCT);
            mMap = mTemplateBean != null ? mTemplateBean.getMap() : null;
        }

        if (null == mTemplateBean) {
            //没有的话,加载默认配置
            mTemplateBean = new TemplateBean();
            mTemplateBean.setUserId(SharedPreUtil.getUserId(getApplicationContext()));
            mTemplateBean.setRole(SharedPreUtil.getString(getApplicationContext(), Constants.RULE, ""));
            mTemplateBean.setTime(DateUtil.getCurrentTime());
            mTemplateBean.setTemplateId(Constants.TEMPLATE_PRODUCT);
            mTemplateBean.setType("text");
            Map<String, String> map = new HashMap<>();
            mTemplateBean.setMap(map);

        } else {
            //在页面中显示,先获取，在过滤
            if (mTemplateBean.getMap().get(Constants.PRODUCT_NAME) != null) {
                bindingView.tvProductProductName.setText(DataUtil.filiteString(mTemplateBean.getMap().get(Constants.PRODUCT_NAME)));
            }
            if (mTemplateBean.getMap().get(Constants.PRODUCT_TYPE) != null) {
                bindingView.tvProductProductType.setText(DataUtil.filiteString(DataUtil.getProductTypeName(mTemplateBean.getMap().get(Constants.PRODUCT_TYPE))));
            }
            if (mTemplateBean.getMap().get(Constants.PRODUCT_PRICE) != null) {
                bindingView.tvProductProductPrice.setText(DataUtil.filiterStringGroup(mTemplateBean.getMap().get(Constants.PRODUCT_PRICE)));
            }
            if (mTemplateBean.getMap().get(Constants.PRODUCT_STORAGECOUNT) != null) {
                bindingView.tvProductProductName.setText(DataUtil.filiterStringGroup(mTemplateBean.getMap().get(Constants.PRODUCT_STORAGECOUNT)));
            }
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.linear_productName:
                showProcutNameDialog();
                break;
            case R.id.linear_productType:
                showProcutTypeDialog();
                break;
            case R.id.linear_productPrice:
                showProductPriceDialog();
                break;
            case R.id.linear_product_storageCount:
                showProductStorageCountDialog();
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

        if (mMap.size() >= 0 && mMap != null) {
            mTemplateBean.setTime(DateUtil.getCurrentTime());
            mTemplateBean.setMap(mMap);
            SharedPreUtil.saveObject(getApplicationContext(), Constants.TEMPLATE_PRODUCT, mTemplateBean);
            SharedPreUtil.setString(getApplicationContext(), Constants.TEMPLATE_PROUDUT_IS_FINISH, "1");
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
                        Toast.makeText(ProductModelConfigureActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean.getCode().equals("1")) {
                            bindingView.progressBar.setVisibility(View.GONE);
                            Toast.makeText(ProductModelConfigureActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ProductModelConfigureActivity.this, "保存失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        addSubscription(subscription);

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

    private void showProcutNameDialog() {
        final android.app.AlertDialog.Builder mDialog = new AlertDialog.Builder(this);

        mDialog.setTitle("温馨提示:");
        mDialog.setMessage("请输入产品名称:");
        final View view = LayoutInflater.from(this).inflate(R.layout.dialog_acount_chose, null);
        mDialog.setView(view);


        mDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText et = (EditText) view.findViewById(R.id.et_acount_chose);
                String input = et.getText().toString().trim();
                //这里要用正则过滤一下
                if (!input.equals("") && !input.equals(Constants.NO)) {
                    bindingView.tvProductProductName.setText(input);
                }
                mMap.put(Constants.PRODUCT_NAME,  DataUtil.getMapValue(Constants.PRODUCT_NAME, input));
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

    private void showProcutTypeDialog() {
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
                    bindingView.tvProductProductType.setText(rb.getText().toString().trim());
                    String type_id = DataUtil.getProductTypeId(rb.getText().toString().trim());
                    mMap.put(Constants.PRODUCT_TYPE, DataUtil.getMapValue(Constants.PRODUCT_TYPE, type_id));
                }
                if (input.equals(Constants.NO)) {
                    mMap.remove(Constants.PRODUCT_TYPE);
                }
                dialog.dismiss();
            }
        });

    }

    private void showProductPriceDialog() {
        final android.app.AlertDialog.Builder mDialog = new AlertDialog.Builder(this);

        mDialog.setTitle("温馨提示:");
        mDialog.setMessage("请输入产品价格:");
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
                    bindingView.tvProductProductPrice.setText(input1 + " - " + input2 + "元");
                }
                mMap.put(Constants.PRODUCT_PRICE, DataUtil.getMapValue(Constants.PRODUCT_PRICE, input1, input2));
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

    private void showProductStorageCountDialog() {
        final android.app.AlertDialog.Builder mDialog = new AlertDialog.Builder(this);

        mDialog.setTitle("温馨提示:");
        mDialog.setMessage("请输入库存数量:");
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
                    bindingView.tvProductStorageCount.setText(input1 + " - " + input2 + "个");
                }
                mMap.put(Constants.PRODUCT_STORAGECOUNT, DataUtil.getMapValue(Constants.PRODUCT_STORAGECOUNT, input1, input2));
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

    private void clearTemplate() {
        bindingView.tvProductProductType.setText(Constants.NO);
        bindingView.tvProductProductName.setText(Constants.NO);
        bindingView.tvProductProductPrice.setText(Constants.NO);
        bindingView.tvProductStorageCount.setText(Constants.NO);
        mMap = new HashMap<>();
    }
}
