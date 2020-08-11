package com.example.algorithm_visualizer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import com.example.algorithm_visualizer.instructions.Instructions;
import com.example.algorithm_visualizer.instructions.SetColorInstruction;
import com.example.algorithm_visualizer.instructions.SwapInstruction;


public class Animation_Helper {


    public interface OnAnimationEnd {
        void func();
    }

    public void setVisibility(TextView[] row, int left, int right, int m, OnAnimationEnd onAnimationEnd) {
        for (int i = left; i <= right; i++) {
            if (row[i].getVisibility() == View.INVISIBLE) {

                row[i].setVisibility(View.VISIBLE);

            }

            if (i <= m) {
                row[i].setBackgroundColor(Color.parseColor("#ffffff"));
            } else {
                row[i].setBackgroundColor(Color.parseColor("#FFFF00"));

            }
            if (i == right) {

                onAnimationEnd.func();
            }
        }
    }


    public void animate(List<Instructions> instructions, int i) {

        if (i >= instructions.size()) {
            return;
        }
        if (instructions.get(i) instanceof SwapInstruction) {
            SwapInstruction swapInstruction = (SwapInstruction) instructions.get(i);
            swapElements(swapInstruction.getArray(), swapInstruction.getLeftTextView(), swapInstruction.getRightTextView(), () -> {
                animate(instructions, i + 1);
            });
            return;

        } else if (instructions.get(i) instanceof SetColorInstruction) {

        }

        /*
        for (Instructions instruction : instructions) {
            if(instruction instanceof SwapInstruction) {
                SwapInstruction swapInstruction = (SwapInstruction) instruction;
                TextView[] array = swapInstruction.getArray();
                Log.d("inst", Arrays.toString(array));
            }
        }

         */

    }



    public void swapElements(TextView[] array, TextView firstPos, TextView secondPos, OnAnimationEnd onAnimationEnd) {


        int[] i_location = new int[2];
        int[] j_location = new int[2];

        firstPos.getLocationOnScreen(i_location);
        secondPos.getLocationOnScreen(j_location);



        ValueAnimator right = ValueAnimator.ofFloat(i_location[0], j_location[0]);
        right.setDuration(3000);
        right.addUpdateListener(valueAnimator -> {
            float progress = (float) valueAnimator.getAnimatedValue();
            firstPos.setX(progress);
        });


        ValueAnimator left = ValueAnimator.ofFloat(j_location[0], i_location[0]);
        left.setDuration(3000);
        left.addUpdateListener(valueAnimator -> {
            float progress = (float) valueAnimator.getAnimatedValue();
            secondPos.setX(progress);
        });

        AnimatorSet set = new AnimatorSet();

        set.playTogether(right, left);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                onAnimationEnd.func();

            }
        });


    }

    public void rowcomplete(TextView[] row,int colorFrom, int colorTo, int left, int right, OnAnimationEnd onAnimationEnd) {

        for (int i = left; i <= right; i++) {
            ValueAnimator orginalColor = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
            orginalColor.setDuration(2000); // milliseconds
            int finalI = i;
            orginalColor.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    row[finalI].setBackgroundColor((int) animator.getAnimatedValue());
                }

            });

            // colorAnimation.start();
            ValueAnimator newColor = ValueAnimator.ofObject(new ArgbEvaluator(), colorTo, colorFrom);
            newColor.setDuration(2000); // milliseconds
            newColor.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    row[finalI].setBackgroundColor((int) animator.getAnimatedValue());
                }

            });
            AnimatorSet set = new AnimatorSet();
            set.play(orginalColor).before(newColor);
            set.start();
            if (i == right) {
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        onAnimationEnd.func();

                    }
                });
            }
        }

    }

    public void divide(TextView[] current_row, TextView[] next_row,int left, int right, int m, OnAnimationEnd onAnimationEnd) {
            int colorFrom = Color.parseColor("#ffffff");
            int colorTo = Color.parseColor("#7fbf7f");

            //highlight from left index to right index and then unhighlight

                rowcomplete(current_row, colorFrom, colorTo, left, right, () -> {
                    setVisibility(next_row, left, right, m, () -> {

                        onAnimationEnd.func();
                    });
                });


            //highlight from left index to right index and then unhighlight
            //set third row visible with only index 0-3 visible -> set index 0-1 as white and index 2-3 as yellow

        }

        public void merge(OnAnimationEnd onAnimationEnd){

        }

    }





