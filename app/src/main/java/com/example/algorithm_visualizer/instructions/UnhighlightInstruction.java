package com.example.algorithm_visualizer.instructions;

import android.graphics.drawable.shapes.OvalShape;
import android.widget.TextView;

public class UnhighlightInstruction implements Instructions {
    TextView[] row;
    int value;

    public UnhighlightInstruction(TextView[] row, int value){
        this.row = row;
        this.value = value;
    }

    public TextView[] getRow() { return row;}
    public int getValue(){
        return value;
    }





}
