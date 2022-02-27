package com.njcit.robotquery.moudle.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.njcit.robotquery.R;
import com.njcit.robotquery.moudle.detail.sub.CountFragment;
import com.njcit.robotquery.moudle.detail.sub.ExcelFragment;
import com.njcit.robotquery.moudle.detail.sub.ImageFragment;
import com.njcit.robotquery.moudle.detail.sub.TextFragemnt;

import java.util.Arrays;
import java.util.List;

public class MessageDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView backDetailActivity;
    private ViewPager pager;
    private TabLayout tabLayout;
    private List<Fragment> list = Arrays.asList(new TextFragemnt(), new ExcelFragment(),
            new CountFragment(), new ImageFragment());

    private List<String> title = Arrays.asList("文字","表格","图表","图片");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messag_detail);
        initView();
        initData();
    }

    private void initData() {
//        Bundle bundle = getIntent().getExtras();
//        String str = (String) bundle.get(Constants.ACTIVIIY_BUNDLE_KEY_1);
//        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

    }

    private void initView(){


        backDetailActivity = (ImageView) findViewById(R.id.back_detail_activity);
        pager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        pager.setAdapter(new ExcelPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(pager);
        tabLayout.setTabTextColors(Color.WHITE,Color.WHITE);

        backDetailActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_detail_activity:
                finish();
                break;
        }
    }

    class ExcelPagerAdapter extends FragmentStatePagerAdapter {

        public ExcelPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }
    }
}
