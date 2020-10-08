package com.example.algorithm_visualizer.instructions;

import android.graphics.drawable.shapes.OvalShape;
import android.widget.TextView;

public class UnhighlightInstruction implements Instructions {
    TextView[] row;
    String text;
    TextView textView;
    int value;

    public UnhighlightInstruction(TextView[] row, int value,String text, TextView textView){
        this.row = row;
        this.value = value;
        this.text = text;
        this.textView = textView;
    }

    public TextView[] getRow() { return row;}
    public int getValue(){
        return value;
    }
    public String getText(){return text;}
    public TextView getTextView(){return textView;}





}
