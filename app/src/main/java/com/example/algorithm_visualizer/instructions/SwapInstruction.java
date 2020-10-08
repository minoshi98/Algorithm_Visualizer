package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;

public  class SwapInstruction implements Instructions {


    private TextView leftTextView, rightTextView, setTextView;
    private String text;


    public SwapInstruction(TextView left, TextView right,String text, TextView setTextView) {

        leftTextView = left;
        rightTextView =  right;
        this.setTextView = setTextView;
        this.text = text;

    }

    public TextView getLeftTextView(){
        return leftTextView;
    }
    public TextView getRightTextView(){
        return rightTextView;
    }
    public String getText(){return text;}
    public TextView getSetTextView(){return setTextView;}

}
