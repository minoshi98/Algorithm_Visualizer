package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;

public class SetVisibileInstruction implements Instructions {
    private TextView[] row;
    private int left, right, m;

    public SetVisibileInstruction(TextView[] row, int left, int right, int m){
        this.row = row;
        this.left = left;
        this.right = right;
        this.m = m;
    }

    public TextView[] getRow()
    {
        return row;
    }

    public int getLeft(){
        return left;
    }
    public int getRight(){
        return right;
    }
    public int getM(){
        return m;
    }

}
