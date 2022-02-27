package com.njcit.robotquery.moudle.login;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Explode;
import android.transition.Visibility;
import android.view.View;

import com.njcit.robotquery.MainActivity;
import com.njcit.robotquery.R;
import com.njcit.robotquery.base.BaseActivity;
import com.njcit.robotquery.bean.server.LoginBean;
import com.njcit.robotquery.bean.local.User;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.databinding.ActivityLoginBinding;
import com.njcit.robotquery.http.ApiManage;
import com.njcit.robotquery.moudle.modelchoose.ModelChooseActivity;
import com.njcit.robotquery.moudle.register.RegisterActivity;
import com.njcit.robotquery.util.AlertUtil;
import com.njcit.robotquery.util.UserStatusUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by youxuan on 2017/3/17 0017.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements View.OnClickListener {

    private String email;
    private String password;
    private ProgressDialog mDialog;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        showContentView();
        initDate();
        mDialog = new ProgressDialog(this);
        bindingView.fab.setOnClickListener(this);
        bindingView.btGoLogin.setOnClickListener(this);
    }

    private void initDate() {
        mUser = UserStatusUtil.getUserInfo(getApplicationContext());
        if (null != mUser) {
            bindingView.etUsernameLogin.setText(mUser.getEmail().trim());
            bindingView.etPasswordLogin.setText(mUser.getPassword().trim());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, bindingView.fab, bindingView.fab.getTransitionName());
                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(this, RegisterActivity.class));
                }
                break;

            case R.id.bt_go_login:
                check_form_user();
                break;
        }
    }

    private void check_form_user() {
        showDialog();
        load_userData(); //加载数据
        if (!checkEmail(email)) {
            cancelDialog();
            AlertUtil.show(this, "邮箱输入错误", null);

            return;
        }

        if (null == password || "".equals(password)) {
            cancelDialog();
            AlertUtil.show(this, "请输入密码", null);
            return;
        }

        submit_login();
    }

    private void submit_login() {
        Subscription subscription = ApiManage.getInstence().getChatService().login_user(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        cancelDialog();
                        AlertUtil.show(LoginActivity.this, "提交失败,请检查网络连接", null);
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getCode().equals("0")) { //登录失败
                            AlertUtil.show(LoginActivity.this, loginBean.getMessage(), null);
                            return;
                        } else if (loginBean.getCode().equals("1")) {
                            mUser = getUser();

                            LoginBean.ObjectBean objectBean = loginBean.getObject().get(0);
                            AlertUtil.show(LoginActivity.this, loginBean.getMessage(), null);
                            //保存用户登录状态信息
                            UserStatusUtil.setLoginStatus_Yes(getApplicationContext());
                            //保存用户信息,userId username rule
                            mUser.setUserId(objectBean.getUserId());
                            mUser.setRule(objectBean.getRoleId());
                            mUser.setUsername(objectBean.getUserUsername());
                            UserStatusUtil.saveUserInfo(getApplicationContext(), mUser);

                            //延迟1秒,否则给用户错觉。没有提交成功
                            new Handler().postDelayed(new Runnable() {
                                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                @Override
                                public void run() {
                                    toMainActivity();
                                }
                            }, 1000);
                        }
                    }
                });

        addSubscription(subscription);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void toMainActivity() {
        cancelDialog();
        Explode explode = new Explode();
        explode.setMode(Visibility.MODE_IN);
        explode.setDuration(500);

        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);

        ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        Intent i2 = null;
        if ("1".equals(UserStatusUtil.getUserInfo(getApplicationContext(), Constants.TEMPLATE_IS_FINISH))) {
            i2 = new Intent(this, MainActivity.class);
        } else {
            i2 = new Intent(this, ModelChooseActivity.class);
        }
        startActivity(i2, oc2.toBundle());

        finish();
        //overridePendingTransition(R.anim.screen_zoom_in,R.anim.screen_zoom_out);
    }


    private void load_userData() {
        email = bindingView.etUsernameLogin.getText().toString().trim();
        password = bindingView.etPasswordLogin.getText().toString().trim();
    }

    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    private void showDialog() {
        if (mDialog != null) {
            mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mDialog.setCancelable(true);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setTitle("提示:");
            mDialog.setMessage("正在登录中 请稍后");

            mDialog.show();
        }
    }

    private void cancelDialog() {

        if (mDialog != null && mDialog.isShowing()) {
            mDialog.cancel();
        }
    }

    private User getUser() {
        User user = new User();
        user.setEmail(bindingView.etUsernameLogin.getText().toString().trim());
        user.setPassword(bindingView.etPasswordLogin.getText().toString().trim());
        if (user != null) {
            return user;
        }
        return null;
    }
}
