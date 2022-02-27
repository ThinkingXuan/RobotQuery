package com.njcit.robotquery.util;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v4.view.ScrollingView;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;


/**
 * Created by youxuan on 2017/3/12 0012.
 */

public class AnimatorUtil {

    static int height1 = 260;
    //隐藏动画
    public static void hideToolBar(View view1) {
        Animator animator = ObjectAnimator.ofFloat(view1, "translationY",
                view1.getTranslationY(), -view1.getHeight());

        animator.setDuration(300);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setTarget(view1);
        animator.start();

    }

    //显示动画
    public static void showToolBar(View view1) {
        Animator animator = ObjectAnimator.ofFloat(view1, "translationY",
                view1.getTranslationY(), 0);
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setTarget(view1);
        animator.start();

    }

    //隐藏动画
    public static void hideBottomBar(final View view2) {


        Animator animator1 = ObjectAnimator.ofFloat(view2, "translationY",
                -0, view2.getHeight());

        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateInterpolator());
        animator1.setTarget(view2);
        animator1.start();
        animator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view2.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    //显示动画
    public static void showBottomBar(View view2) {


        view2.setVisibility(View.VISIBLE);
        final Animator animator1 = ObjectAnimator.ofFloat(view2, "translationY",
                view2.getTranslationY(), 0);
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateInterpolator());
        animator1.setTarget(view2);
        animator1.start();

    }

}
