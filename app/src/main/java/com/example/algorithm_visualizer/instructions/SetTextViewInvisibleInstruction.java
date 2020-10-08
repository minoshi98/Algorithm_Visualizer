package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;

import org.w3c.dom.Text;

public class SetTextViewInvisibleInstruction implements Instructions {
    private TextView[] row;
    private String value;

    public SetTextViewInvisibleInstruction (TextView[] row, String value){
        this.row = row;
        this.value = value;
    }

    public TextView[] getRow(){return row;}
    public  String getValue (){return value;}
}
