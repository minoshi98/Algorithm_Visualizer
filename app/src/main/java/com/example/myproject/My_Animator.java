package com.example.myproject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.widget.TextView;

public class My_Animator {


    public interface OnAnimationEnd {
        void func();
    }

    public void swapElements(TextView[] array, TextView firstPos, TextView secondPos, OnAnimationEnd onAnimationEnd) {


        int[] i_location = new int[2];
        int[] j_location = new int[2];

        firstPos.getLocationOnScreen(i_location);
        secondPos.getLocationOnScreen(j_location);


        ValueAnimator right = ValueAnimator.ofFloat(i_location[0],  j_location[0]);
        right.setDuration(3000);
        right.addUpdateListener(valueAnimator -> {
            float progress = (float) valueAnimator.getAnimatedValue();
            firstPos.setX(progress);
        });


        ValueAnimator left = ValueAnimator.ofFloat(j_location[0],  i_location[0]);
        left.setDuration(3000);
        left.addUpdateListener(valueAnimator -> {
            float progress = (float) valueAnimator.getAnimatedValue();
            secondPos.setX(progress);
        });

        AnimatorSet set = new AnimatorSet();

        set.playTogether(right,left);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                onAnimationEnd.func();

            }
            });



    }

    public void drawLine(TextView startTextView, TextView endTextView){

    }


}
