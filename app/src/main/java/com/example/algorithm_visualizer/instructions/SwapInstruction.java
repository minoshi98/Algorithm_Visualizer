package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;

public  class SwapInstruction implements Instructions {


    private TextView leftTextView, rightTextView;

    public SwapInstruction(TextView[] arr, TextView left, TextView right) {

        leftTextView = left;
        rightTextView =  right;

    }

    public TextView getLeftTextView(){
        return leftTextView;
    }
    public TextView getRightTextView(){
        return rightTextView;
    }
}
