package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;

public class BlinkInstruction implements Instructions {
    private TextView[] currentrow;
    private int left, right;

    public BlinkInstruction(TextView[] currentrow, int left, int right){
        this.currentrow = currentrow;
        this.left = left;
        this.right = right;
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

}
