package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;

public  class SwapInstruction implements Instructions {

    TextView[] array;
    TextView leftTextView, rightTextView;

    public SwapInstruction(TextView[] arr, TextView left, TextView right) {
        array = arr;
        leftTextView = left;
        rightTextView =  right;

    }

    public TextView[] getArray(){
        return array;
    }
    public TextView getLeftTextView(){
        return leftTextView;
    }
    public TextView getRightTextView(){
        return rightTextView;
    }
}
