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

import com.example.algorithm_visualizer.instructions.BlinkInstruction;
import com.example.algorithm_visualizer.instructions.HighlightInstruction;
import com.example.algorithm_visualizer.instructions.Instructions;
import com.example.algorithm_visualizer.instructions.SetMinimumTextInstruction;
import com.example.algorithm_visualizer.instructions.SetRowInvisible;
import com.example.algorithm_visualizer.instructions.SetTextInstruction;
import com.example.algorithm_visualizer.instructions.SetVisibileInstruction;
import com.example.algorithm_visualizer.instructions.SwapInstruction;
import com.example.algorithm_visualizer.instructions.UnhighlightInstruction;


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

    public void setRowInvisible(TextView[] row, OnAnimationEnd onAnimationEnd) {

        for (int i = 0; i < row.length; i++) {

            if (row[i].getVisibility() == View.VISIBLE) {

                row[i].setVisibility(View.INVISIBLE);

            }

            if (i == row.length-1) {

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
            swapElements(swapInstruction.getLeftTextView(), swapInstruction.getRightTextView(), () -> {
                //return;
                 animate(instructions, i + 1);
            });
         //   return;

        } else if (instructions.get(i) instanceof BlinkInstruction) {
            BlinkInstruction blinkInstruction = (BlinkInstruction) instructions.get(i);
            blink(blinkInstruction.getCurrentrow(),blinkInstruction.getLeft(),blinkInstruction.getRight(),() -> {
             //   return;
                animate(instructions,i+1);

            });
        }
        else if (instructions.get(i) instanceof SetVisibileInstruction) {
            SetVisibileInstruction setVisibileInstruction = (SetVisibileInstruction) instructions.get(i);
            setVisibility(setVisibileInstruction.getRow(),setVisibileInstruction.getLeft(),setVisibileInstruction.getRight(),setVisibileInstruction.getM(),() -> {
               // return;
                animate(instructions,i+1);
            });
        }
        else if (instructions.get(i) instanceof HighlightInstruction) {
            HighlightInstruction highlightInstruction = (HighlightInstruction) instructions.get(i);
            highlight(highlightInstruction.getRow(),highlightInstruction.getLeftArray(),highlightInstruction.getRightArray(),()  -> {
              //  return;
                animate(instructions,i+1);

            } );
        }
        else if (instructions.get(i) instanceof UnhighlightInstruction) {
            UnhighlightInstruction unhighlightInstruction = (UnhighlightInstruction) instructions.get(i);
            unhighlight(unhighlightInstruction.getRow(), unhighlightInstruction.getValue(), () -> {
              //  return;
                animate(instructions,i+1);

            } );
        }
        else if (instructions.get(i) instanceof SetTextInstruction) {
            SetTextInstruction setTextInstruction = (SetTextInstruction) instructions.get(i);
            setText(setTextInstruction.getRow(), setTextInstruction.getFindValue(), setTextInstruction.getSetValue(),() -> {
              //  return;
                animate(instructions,i+1);

            } );
        }
        else if (instructions.get(i) instanceof SetRowInvisible) {
            SetRowInvisible setRowInvisible = (SetRowInvisible) instructions.get(i);
            setRowInvisible(setRowInvisible.getRow(), () -> {
            //    return;
                animate(instructions,i+1);

            } );
        }

        else if (instructions.get(i) instanceof SetMinimumTextInstruction) {
            SetMinimumTextInstruction setMinimumTextInstruction = (SetMinimumTextInstruction) instructions.get(i);
            setMinimumText(setMinimumTextInstruction.getTextView(), setMinimumTextInstruction.getValue(),()  -> {
                //return;
                  animate(instructions,i+1);

            } );
        }






    }

    public void setText(TextView[] row, String findValue, String setValue, OnAnimationEnd onAnimationEnd){
        TextView element = findRow(row, findValue);
        element.setText(setValue);
        onAnimationEnd.func();
    }

    public void setMinimumText(TextView textView, String value, OnAnimationEnd onAnimationEnd){
        textView.setText(value);
        onAnimationEnd.func();
    }

    public void swapElements(TextView firstPos, TextView secondPos, OnAnimationEnd onAnimationEnd) {


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


    public void blink(TextView[] row, int left, int right, OnAnimationEnd onAnimationEnd) {
        int colorFrom = Color.parseColor("#ffffff");
        int colorTo = Color.parseColor("#7fbf7f");

        for (int i = left; i <= right; i++) {
            ValueAnimator orginalColor = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
            orginalColor.setDuration(1000); // milliseconds
            int finalI = i;
            orginalColor.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    row[finalI].setBackgroundColor((int) animator.getAnimatedValue());
                }

            });

            // colorAnimation.start();
            ValueAnimator newColor = ValueAnimator.ofObject(new ArgbEvaluator(), colorTo, colorFrom);
            newColor.setDuration(1000); // milliseconds
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
    public TextView findRow(TextView[] row, String value){
        for(int i = 0; i < row.length ; i++){
            Log.d("TEXT", String.valueOf(row[i].getText()));
            if(row[i].getText().equals(String.valueOf(value))) {
                return row[i];
            }
        }
        return null;
    }

    public void highlight(TextView[] row, int[] leftArray, int[] rightArray, OnAnimationEnd onAnimationEnd) {
        int colorFrom = Color.parseColor("#ffffff");
        int colorTo = Color.parseColor("#7fbf7f");

        int leftArraySize = leftArray.length;
        int rightArraySize = rightArray.length;

        int[] arrayCombined = new int[leftArraySize + rightArraySize];
        System.arraycopy(leftArray,0,arrayCombined,0,leftArraySize);
        System.arraycopy(rightArray,0,arrayCombined,leftArraySize,rightArraySize);
        Log.d("COMBINED", Arrays.toString(arrayCombined));



        for (int i = 0; i < arrayCombined.length; i++) {
            ValueAnimator newColor = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
            newColor.setDuration(1000); // milliseconds
            TextView elementToBeHighlighted = findRow(row,String.valueOf(arrayCombined[i]));
            newColor.addUpdateListener(animator -> elementToBeHighlighted.setBackgroundColor((int) animator.getAnimatedValue()));


            newColor.start();
            if (i == arrayCombined.length - 1) {
                newColor.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        onAnimationEnd.func();

                    }
                });
            }
        }


    }

    public void unhighlight(TextView[] row, int value, OnAnimationEnd onAnimationEnd) {
        int colorTo = Color.parseColor("#ffffff");
        int colorFrom = Color.parseColor("#7fbf7f");

        TextView elementToBeUnhighlighted = findRow(row, String.valueOf(value));
            ValueAnimator newColor = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
            newColor.setDuration(2000); // milliseconds
            newColor.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    elementToBeUnhighlighted.setBackgroundColor((int) animator.getAnimatedValue());
                }

            });


            newColor.start();
                newColor.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        onAnimationEnd.func();

                    }
                });


    }

    }





