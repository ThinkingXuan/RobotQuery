package com.njcit.robotquery;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.SimpleArrayMap;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.moudle.chat.messages.MessagesListFragment;
import com.njcit.robotquery.moudle.fragment.subfragment.FuncationFragment;
import com.njcit.robotquery.moudle.modelconfigure.ModelListFragment;
import com.njcit.robotquery.util.SharedPreUtil;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = "MainActivity";
    private static MainActivity activity;

    private MenuItem currentMenuItem;
    private Fragment currentFragment;
    int navigationId;
    SimpleArrayMap<Integer, String> mTitleArrayMap = new SimpleArrayMap<>();
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ImageView mImageView;
    private TextView mTextView;


    public static MainActivity newInstance() {
        if (activity != null) {
            return activity;
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        activity = this;
        addFragmentsAndTitle();

        if (savedInstanceState == null) {
            navigationId = SharedPreUtil.getNevigationItem(this);
            if (navigationId == -1) {
                currentMenuItem = navigationView.getMenu().findItem(R.id.nav_chat_robot);
                currentMenuItem.setChecked(true);
            }

            if (currentMenuItem == null) {
                currentMenuItem = navigationView.getMenu().findItem(R.id.nav_chat_robot);
                currentMenuItem.setChecked(true);
            }

            if (currentMenuItem != null) {
                currentMenuItem.setCheckable(true);

                Fragment fragment = getFragmentById(currentMenuItem.getItemId());
                String title = mTitleArrayMap.get(currentMenuItem.getItemId());

                if (fragment != null) {
                    switchFragment(fragment, title);
                }
            }
        } else {

            if (currentFragment != null) {
                Fragment fragment = getFragmentById(currentMenuItem.getItemId());
                String title = mTitleArrayMap.get(currentMenuItem.getItemId());
                if (fragment != null) {
                    switchFragment(fragment, title);
                }
            } else {
                switchFragment(new MessagesListFragment(), " ");
                currentMenuItem = navigationView.getMenu().findItem(R.id.nav_chat_robot);
            }
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                if (drawer.isDrawerOpen(Gravity.START)) {
                    drawer.closeDrawers();
                }
                if (currentMenuItem != item && currentMenuItem != null) {
                    currentMenuItem.setChecked(true);
                    int id = item.getItemId();
                    SharedPreUtil.putNevigationItem(MainActivity.this, id);
                    currentMenuItem = item;
                    currentMenuItem.setChecked(true);
                }

                return true;
            }
        });

        //解决NavigationView切换Fragment卡顿问题:在DrawerLayout中监听Drawer关闭后在SwitchFragment()
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                switchFragment(getFragmentById(currentMenuItem.getItemId()), mTitleArrayMap.get(currentMenuItem.getItemId()));
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        mImageView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.iv_logout);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        mTextView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_header_name);
        mTextView.setText(SharedPreUtil.getString(getApplicationContext(), Constants.USERNAME, ""));

        //navigationView.setItemTextColor(null);
        navigationView.setItemIconTintList(null);



    }


    private void addFragmentsAndTitle() {

        mTitleArrayMap.put(R.id.nav_chat_robot, getResources().getString(R.string.chat_name_robot));
        mTitleArrayMap.put(R.id.nav_function, getResources().getString(R.string.item_function));
        mTitleArrayMap.put(R.id.nav_template, getResources().getString(R.string.my_model_str));
        mTitleArrayMap.put(R.id.nav_order, getResources().getString(R.string.my_order_history));

    }

    private Fragment getFragmentById(int id) {
        Fragment fragment = null;

        switch (id) {
            case R.id.nav_chat_robot:
                fragment = new MessagesListFragment();
                break;
            case R.id.nav_function:
                fragment = new FuncationFragment();
                break;
            case R.id.nav_template:
                fragment = new ModelListFragment();
                break;
        }
        return fragment;
    }

    public void switchFragment(Fragment fragment, String title) {

        if (currentFragment == null || !currentFragment.getClass().getName().equals(fragment.getClass().getName()))
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

        toolbar.setTitle(title);
        currentFragment = fragment;

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setIconsVisible(menu, true);
        return true;
    }

    private void showDialog() {


        // final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getApplicationContext());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示:");
        builder.setMessage("是否确认退出");
        builder.setCancelable(false);

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //清除用户登录状态
                SharedPreUtil.removeString(getApplicationContext(), Constants.LOGINSTATE);
                SharedPreUtil.removeString(getApplicationContext(),Constants.TEMPLATE_IS_FINISH);
                finish();
            }
        });
        builder.show();
    }


    private void setIconsVisible(Menu menu, boolean flag) {
        //判断menu是否为空
        if (menu != null) {
            try {
                //如果不为空,就反射拿到menu的setOptionalIconsVisible方法
                Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                //暴力访问该方法
                method.setAccessible(true);
                //调用该方法显示icon
                method.invoke(menu, flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
