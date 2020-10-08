package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;

public class BlinkInstruction implements Instructions {
    private TextView[] currentrow;
    private int left, right;
    private String text;
    private TextView textView;

    public BlinkInstruction(TextView[] currentrow, int left, int right, String text, TextView textView){
        this.currentrow = currentrow;
        this.left = left;
        this.right = right;
        this.text = text;
        this.textView = textView;
    }

    public TextView[] getCurrentrow(){
        return currentrow;
    }

    public int getLeft(){
        return left;
    }
    public int getRight(){
        return right;
    }

    public String getText(){
        return text;
    }

    public TextView getTextView(){
        return textView;
    }

}
