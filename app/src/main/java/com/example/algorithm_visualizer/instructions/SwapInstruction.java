package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;

public  class SwapInstruction implements Instructions {


    private TextView leftTextView, rightTextView, setTextView;
    private int firstIndex, secondIndex;
    private String text;
    private TextView[] textView_array;


    public SwapInstruction(TextView[] textView_array,int firstIndex, int secondIndex, TextView left, TextView right,String text, TextView setTextView) {

        this.leftTextView = left;
        this.rightTextView =  right;
        this.setTextView = setTextView;
        this.text = text;
        this.textView_array = textView_array;
        this.firstIndex = firstIndex;
        this.secondIndex = secondIndex;

    }

    public TextView getLeftTextView(){
        return leftTextView;
    }
    public TextView getRightTextView(){
        return rightTextView;
    }
    public String getText(){return text;}
    public TextView getSetTextView(){return setTextView;}
    public TextView[] getTextView_array(){return textView_array;}
    public int getFirstIndex(){return firstIndex;}
    public int getSecondIndex(){return secondIndex;}

}
