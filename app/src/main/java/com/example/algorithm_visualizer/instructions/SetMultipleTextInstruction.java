package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;

public class SetMultipleTextInstruction implements Instructions {

    TextView[] row;
    String text;
    TextView textView;
    int[] leftArray, rightArray;

    public SetMultipleTextInstruction(TextView[] row,int[] leftArray, int[] rightArray, String text, TextView textView){
        this.row = row;
        this.leftArray = leftArray;
        this.rightArray = rightArray;
        this.text = text;
        this.textView = textView;
    }

    public TextView[] getRow(){
        return row;
    }

    public  int[] getLeftArray(){
        return leftArray;
    }

    public int[] getRightArray(){
        return rightArray;
    }

    public String getText(){ return text;}

    public TextView getTextView(){ return textView;}


}
