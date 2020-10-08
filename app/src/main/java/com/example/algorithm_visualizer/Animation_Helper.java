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
import com.example.algorithm_visualizer.instructions.HighlightTextViewInstruction;
import com.example.algorithm_visualizer.instructions.Instructions;
import com.example.algorithm_visualizer.instructions.MoveTextViewInstruction;
import com.example.algorithm_visualizer.instructions.SetMinimumTextInstruction;
import com.example.algorithm_visualizer.instructions.SetMultipleTextInstruction;
import com.example.algorithm_visualizer.instructions.SetRowInvisible;
import com.example.algorithm_visualizer.instructions.SetTextInstruction;
import com.example.algorithm_visualizer.instructions.SetTextViewInvisibleInstruction;
import com.example.algorithm_visualizer.instructions.SetVisibileInstruction;
import com.example.algorithm_visualizer.instructions.SwapInstruction;
import com.example.algorithm_visualizer.instructions.UnhighlightInstruction;


public class Animation_Helper {
//LiveDemo_Mergesort liveDemo_mergesort = new LiveDemo_Mergesort();
    public interface OnAnimationEnd {
        void func();
    }

    public void setVisibility(TextView[] row, int left, int right, int m, String text, TextView textView, OnAnimationEnd onAnimationEnd) {
        textView.setText(text);
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




    public void animate(List<Instructions> instructions, int i, boolean nextBtnCalled, boolean startBtnCalled ) {

        if (i >= instructions.size()) {
            return;
        }
        if (instructions.get(i) instanceof SwapInstruction) {
            SwapInstruction swapInstruction = (SwapInstruction) instructions.get(i);
            swapElements(swapInstruction.getLeftTextView(), swapInstruction.getRightTextView(),swapInstruction.getText(),swapInstruction.getSetTextView(),()  -> {
                Log.d("MINO","SWAP");
                if(nextBtnCalled)
                    return;
                else
                 animate(instructions, i + 1,true,false);
            });
         //   return;

        } else if (instructions.get(i) instanceof BlinkInstruction) {
            BlinkInstruction blinkInstruction = (BlinkInstruction) instructions.get(i);
            blink(blinkInstruction.getCurrentrow(),blinkInstruction.getLeft(),blinkInstruction.getRight(),blinkInstruction.getText(),blinkInstruction.getTextView(),() -> {
                Log.d("MINO","BLINK");

                if(nextBtnCalled)
                    return;
                else
                    animate(instructions, i + 1,false,true);

            });
        }
        else if (instructions.get(i) instanceof SetVisibileInstruction) {
            SetVisibileInstruction setVisibileInstruction = (SetVisibileInstruction) instructions.get(i);
            setVisibility(setVisibileInstruction.getRow(),setVisibileInstruction.getLeft(),setVisibileInstruction.getRight(),setVisibileInstruction.getM(),setVisibileInstruction.getText(),setVisibileInstruction.getTextView(),() -> {
               // return;
                Log.d("MINO","SETVISIBLE");

                if(nextBtnCalled)
                    return;
                else
                    animate(instructions, i + 1,false,true);
            });
        }
        else if (instructions.get(i) instanceof HighlightInstruction) {
            HighlightInstruction highlightInstruction = (HighlightInstruction) instructions.get(i);
            highlight(highlightInstruction.getRow(),highlightInstruction.getLeftArray(),highlightInstruction.getRightArray(),()  -> {
              //  return;
                Log.d("MINO","HIGHLIGHT");

                if(nextBtnCalled)
                    return;
                else
                    animate(instructions, i + 1,false,true);

            } );
        }
        else if (instructions.get(i) instanceof UnhighlightInstruction) {
            UnhighlightInstruction unhighlightInstruction = (UnhighlightInstruction) instructions.get(i);
            unhighlight(unhighlightInstruction.getRow(), unhighlightInstruction.getValue(),unhighlightInstruction.getText(),unhighlightInstruction.getTextView(),()  -> {
              //  return;
                Log.d("MINO","UNHIGHLIGHT");

                if(nextBtnCalled)
                    return;
                else
                    animate(instructions, i + 1,false,true);

            } );
        }
        else if (instructions.get(i) instanceof SetTextInstruction) {
            SetTextInstruction setTextInstruction = (SetTextInstruction) instructions.get(i);
            setText(setTextInstruction.getTextView(),setTextInstruction.getText(),() -> {
              //  return;
                Log.d("MINO","SETTEXT");

                if(nextBtnCalled)
                    return;
                else
                    animate(instructions, i + 1,false,true);

            } );
        }
        else if (instructions.get(i) instanceof SetRowInvisible) {
            SetRowInvisible setRowInvisible = (SetRowInvisible) instructions.get(i);
            setRowInvisible(setRowInvisible.getRow(), () -> {
            //    return;
                Log.d("MINO","SETROW");

                if(nextBtnCalled)
                    return;
                else
                    animate(instructions, i + 1,false,true);

            } );
        }

        else if (instructions.get(i) instanceof SetMinimumTextInstruction) {
            SetMinimumTextInstruction setMinimumTextInstruction = (SetMinimumTextInstruction) instructions.get(i);
            setMinimumText(setMinimumTextInstruction.getTextView(), setMinimumTextInstruction.getValue(),setMinimumTextInstruction.getText(),setMinimumTextInstruction.getSetTextView(),()  -> {
                //return;
                Log.d("MINO","SETMIN");

                if(nextBtnCalled)
                    return;
                else
                    animate(instructions, i + 1,false,true);

            } );
        }
        else if (instructions.get(i) instanceof SetMultipleTextInstruction) {
            SetMultipleTextInstruction setMultipleTextInstruction = (SetMultipleTextInstruction) instructions.get(i);
            setMultipleTexts(setMultipleTextInstruction.getRow(), setMultipleTextInstruction.getLeftArray(),setMultipleTextInstruction.getRightArray(),setMultipleTextInstruction.getText(),setMultipleTextInstruction.getTextView(),()  -> {
                //return;
                Log.d("MINO","SETMULTIPLE");

                if(nextBtnCalled)
                    return;
                else
                    animate(instructions, i + 1,false,true);

            } );
        }
        else if (instructions.get(i) instanceof MoveTextViewInstruction) {
            MoveTextViewInstruction moveTextViewInstruction = (MoveTextViewInstruction) instructions.get(i);
            moveTextView(moveTextViewInstruction.getFirstPos(), moveTextViewInstruction.getSecondPos(),moveTextViewInstruction.getText(),moveTextViewInstruction.getSetTextView(),()  -> {
                //return;
                Log.d("MINO","MOVETEXTVIEW");

                if(nextBtnCalled)
                    return;
                else
                    animate(instructions, i + 1,false,true);

            } );
        }
        else if (instructions.get(i) instanceof HighlightTextViewInstruction) {
            HighlightTextViewInstruction highlightTextViewInstruction = (HighlightTextViewInstruction) instructions.get(i);
            highlightTextView(highlightTextViewInstruction.getTextView(), highlightTextViewInstruction.getText(),highlightTextViewInstruction.getSetTextView(),()  -> {
                //return;
                Log.d("MINO","HIGHLIGHTTEXTVIEW");

                if(nextBtnCalled)
                    return;
                else
                    animate(instructions, i + 1,false,true);

            } );
        }
        else if (instructions.get(i) instanceof SetTextViewInvisibleInstruction) {
            SetTextViewInvisibleInstruction setTextViewInvisibleInstruction = (SetTextViewInvisibleInstruction) instructions.get(i);
            setTextViewInvisible(setTextViewInvisibleInstruction.getRow(), setTextViewInvisibleInstruction.getValue(),()  -> {
                //return;
                Log.d("MINO","SETTEXTVIEWINVIS");

                if(nextBtnCalled)
                    return;
                else
                    animate(instructions, i + 1,false,true);

            } );
        }

    }

    public void setTextViewInvisible(TextView[] row, String value, OnAnimationEnd onAnimationEnd){

        TextView textView = findRow(row,value);
        textView.setVisibility(View.INVISIBLE);
        onAnimationEnd.func();
    }

    public void setText(TextView[] row, String findValue, String setValue, OnAnimationEnd onAnimationEnd){
        TextView element = findRow(row, findValue);
        element.setText(setValue);
        onAnimationEnd.func();
    }

    public void setMinimumText(TextView textView, String value, String text, TextView setTextView,OnAnimationEnd onAnimationEnd){
        setTextView.setText(text);
        textView.setText(value);
        onAnimationEnd.func();
    }
    public void moveTextView(TextView firstPos, TextView secondPos,String text, TextView setTextView, OnAnimationEnd onAnimationEnd) {

        if(setTextView.getVisibility() == View.INVISIBLE) setTextView.setVisibility(View.VISIBLE);
        setTextView.setText(text);

        int[] firstPos_loc = new int[2];
        int[] secondPos_loc = new int[2];

        firstPos.getLocationOnScreen(firstPos_loc);
        secondPos.getLocationOnScreen(secondPos_loc);


        ValueAnimator move = ValueAnimator.ofFloat(firstPos_loc[0],secondPos_loc[0] );
        move.setDuration(1000);
        move.addUpdateListener(valueAnimator -> {
            float progress = (float) valueAnimator.getAnimatedValue();
            firstPos.setX(progress);
        });


        move.start();
        move.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                onAnimationEnd.func();

            }
        });


    }
    public void swapElements(TextView firstPos, TextView secondPos,String text, TextView setTextView, OnAnimationEnd onAnimationEnd) {

        if(setTextView.getVisibility() == View.INVISIBLE) setTextView.setVisibility(View.VISIBLE);
        setTextView.setText(text);

        int[] i_location = new int[2];
        int[] j_location = new int[2];

        firstPos.getLocationOnScreen(i_location);
        secondPos.getLocationOnScreen(j_location);



        ValueAnimator right = ValueAnimator.ofFloat(i_location[0], j_location[0]);
        right.setDuration(1000);
        right.addUpdateListener(valueAnimator -> {
            float progress = (float) valueAnimator.getAnimatedValue();
            firstPos.setX(progress);
        });


        ValueAnimator left = ValueAnimator.ofFloat(j_location[0], i_location[0]);
        left.setDuration(1000);
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


    public void blink(TextView[] row, int left, int right, String text, TextView textView,OnAnimationEnd onAnimationEnd) {
        int colorFrom = Color.parseColor("#ffffff");
        int colorTo = Color.parseColor("#7fbf7f");
        if(textView.getVisibility() == View.INVISIBLE) textView.setVisibility(View.VISIBLE);
        textView.setText(text);
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
            if(row[i].getText().equals(String.valueOf(value))) {
                return row[i];
            }
        }
        return null;
    }

    public void setText(TextView textView, String text,OnAnimationEnd onAnimationEnd){
        if(textView.getVisibility() == View.INVISIBLE) textView.setVisibility(View.VISIBLE);
        textView.setText(text);
        onAnimationEnd.func();
    }

    public void setMultipleTexts(TextView[] row, int[] leftArray, int[] rightArray, String text, TextView textview, OnAnimationEnd onAnimationEnd) {

        textview.setText((text));

        int leftArraySize = leftArray.length;
        int rightArraySize = rightArray.length;

        int[] arrayCombined = new int[leftArraySize + rightArraySize];
        System.arraycopy(leftArray,0,arrayCombined,0,leftArraySize);
        System.arraycopy(rightArray,0,arrayCombined,leftArraySize,rightArraySize);



        for (int i = 0; i < arrayCombined.length; i++) {

            TextView textView = findRow(row,String.valueOf(arrayCombined[i]));
            textView.setText("");


            if(i == arrayCombined.length-1)
                onAnimationEnd.func();

        }


    }
    public void highlight(TextView[] row, int[] leftArray, int[] rightArray, OnAnimationEnd onAnimationEnd) {
        int colorFrom = Color.parseColor("#ffffff");
        int colorTo = Color.parseColor("#7fbf7f");

        int leftArraySize = leftArray.length;
        int rightArraySize = rightArray.length;

        int[] arrayCombined = new int[leftArraySize + rightArraySize];
        System.arraycopy(leftArray,0,arrayCombined,0,leftArraySize);
        System.arraycopy(rightArray,0,arrayCombined,leftArraySize,rightArraySize);



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
    public void highlightTextView(TextView textView,String text, TextView setTextView,OnAnimationEnd onAnimationEnd) {
        int colorFrom = Color.parseColor("#ffffff");
        int colorTo = Color.parseColor("#7fbf7f");

        setTextView.setText(text);
        ValueAnimator newColor = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        newColor.setDuration(1000); // milliseconds
        newColor.addUpdateListener(animator -> textView.setBackgroundColor((int) animator.getAnimatedValue()));


        newColor.start();
        newColor.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                onAnimationEnd.func();
            }
        });

    }
    public void unhighlight(TextView[] row, int value, String text, TextView textView, OnAnimationEnd onAnimationEnd) {
        int colorTo = Color.parseColor("#ffffff");
        int colorFrom = Color.parseColor("#7fbf7f");

        textView.setText(text);
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





