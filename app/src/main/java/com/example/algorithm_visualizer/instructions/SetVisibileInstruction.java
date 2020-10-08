package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;

public class SetVisibileInstruction implements Instructions {
    private TextView[] row;
    private String text;
    private TextView textView;
    private int left, right, m;

    public SetVisibileInstruction(TextView[] row, int left, int right, int m, String text, TextView textview){
        this.row = row;
        this.left = left;
        this.right = right;
        this.m = m;
        this.text = text;
        this.textView = textview;
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
    public String getText(){return text;}
    public TextView getTextView(){return textView;}

}
