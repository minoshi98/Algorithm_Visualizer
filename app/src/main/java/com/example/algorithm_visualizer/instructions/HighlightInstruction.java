package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;

public class HighlightInstruction implements Instructions {

    private TextView[] row;
    private int[] leftArray, rightArray;

    public HighlightInstruction(TextView[] row,  int[] leftArray, int[] rightArray){
        this.row = row;
        this.leftArray = leftArray;
        this.rightArray = rightArray;
    }

    public TextView[] getRow(){
        return row;
    }

    public int[] getLeftArray(){
        return leftArray;
    }
    public  int[] getRightArray(){
        return rightArray;
    }

}
