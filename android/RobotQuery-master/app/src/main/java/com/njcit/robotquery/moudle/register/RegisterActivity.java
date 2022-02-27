package com.njcit.robotquery.moudle.register;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;

import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;


import com.njcit.robotquery.R;
import com.njcit.robotquery.base.BaseActivity;
import com.njcit.robotquery.bean.server.BaseBean;
import com.njcit.robotquery.bean.local.User;
import com.njcit.robotquery.databinding.ActivityRegisterBinding;
import com.njcit.robotquery.http.ApiManage;
import com.njcit.robotquery.moudle.login.LoginActivity;
import com.njcit.robotquery.util.AlertUtil;
import com.njcit.robotquery.util.UserStatusUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> implements View.OnClickListener,AdapterView.OnItemSelectedListener{


    public static final String TAG = "RegisterActivity";
    private String email;
    private String username;
    private String password;
    private String again_password;
    private String rule;
    private ProgressDialog mDialog;
    private String[] positions;
    private User mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        showContentView();
        mDialog = new ProgressDialog(this);
        bindingView.btGoRegister.setOnClickListener(this);
        bindingView.fab.setOnClickListener(this);
        bindingView.spinnerPosition.setOnItemSelectedListener(this);

        setSpinner();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation();
        }


    }

    private void setSpinner(){

        positions = getResources().getStringArray(R.array.position_company);

        //构造ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, positions);
        //设置下拉样式以后显示的样式
        adapter.setDropDownViewResource(R.layout.my_drop_down_item);

        bindingView.spinnerPosition.setAdapter(adapter);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_go_register:
                showDialog();
                checkFrom_register();     //注册
                break;
            case R.id.fab:
                animateRevealClose();
                break;

        }
    }


    //注册验证
    private void checkFrom_register() {
        loadUserInfo();

        //表单验证
        if (!checkEmail(email)) {
            cancelDialog();
            AlertUtil.show(this, "邮箱输入错误", null);

            return;
        }

        if (null == username || "".equals(username)) {
            cancelDialog();
            AlertUtil.show(this, "请输入用户名", null);

            return;
        }
        if (null == password || "".equals(password) || null == again_password || "".equals(again_password)) {
            cancelDialog();
            AlertUtil.show(this, "请输入密码", null);
            return;
        }
        if (password.length() < 6 || again_password.length() < 6) {
            cancelDialog();
            AlertUtil.show(this, "密码不得少于6位", null);
            return;
        }
        if (!password.equals(again_password)) {
            cancelDialog();
            AlertUtil.show(this, "两次密码不一致,请重新输入", null);
            return;
        }

        submitForm_register();

    }

    //提交服务器
    private void submitForm_register() {

        Subscription subscrition = ApiManage.getInstence().getChatService().register_user(username, email, password,rule)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onCompleted() {
                        cancelDialog();
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                        cancelDialog();
                        AlertUtil.show(RegisterActivity.this, "提交失败,请检查网络连接", null);

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        Log.d(TAG, "onNext: " + baseBean.toString());
                        cancelDialog();

                        if (baseBean.getCode().equals("0")) { //登录失败
                            AlertUtil.show(RegisterActivity.this, baseBean.getMessage(), null);
                            return;
                        } else if (baseBean.getCode().equals("1")) {
                            AlertUtil.show(RegisterActivity.this, baseBean.getMessage(), null);
                            //保存用户信息
                            UserStatusUtil.saveUserInfo(getApplicationContext(),mUser);

                            //延迟2秒,否则给用户错觉。没有提交成功
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toLoginActivity();
                                }
                            }, 1500);
                        }
                    }
                });

        addSubscription(subscrition);
    }

    private void toLoginActivity() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
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


    private void loadUserInfo() {
        email = bindingView.etEmailRegister.getText().toString().trim();
        username = bindingView.etUsernameRegister.getText().toString().trim();
        password = bindingView.etRepeatpasswordRegister.getText().toString().trim();
        again_password = bindingView.etRepeatpasswordRegister.getText().toString().trim();
        mUser = new User("0",email,username,password,rule);

    }

    private void showDialog() {
        if (mDialog != null) {
            mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mDialog.setCancelable(true);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setMessage("正在注册中 请稍后");
            mDialog.setTitle("提示");
            mDialog.show();
        }
    }

    private void cancelDialog() {

        if (mDialog != null && mDialog.isShowing()) {
            mDialog.cancel();
        }
    }

    //设置进入动画
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                bindingView.cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(bindingView.cvAdd, bindingView.cvAdd.getWidth() / 2, 0, bindingView.fab.getWidth() / 2, bindingView.cvAdd.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                bindingView.cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(bindingView.cvAdd, bindingView.cvAdd.getWidth() / 2, 0, bindingView.cvAdd.getHeight(), bindingView.fab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                bindingView.cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                bindingView.fab.setImageResource(R.drawable.plus);
                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        animateRevealClose();
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        rule = positions[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        rule = positions[0];
    }
}