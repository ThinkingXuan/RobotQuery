package com.njcit.robotquery.moudle.detail.sub;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.njcit.robotquery.R;
import com.njcit.robotquery.base.BaseFragment;
import com.njcit.robotquery.databinding.FragmentDetailImageBinding;

/**
 * Created by youxuan on 2017/4/7 0007.
 */

public class ImageFragment extends BaseFragment<FragmentDetailImageBinding> {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_detail_image;
    }
}
