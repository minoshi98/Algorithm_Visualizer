package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;

public class SetTextInstruction implements Instructions{
    private TextView textView;
    private String text;


    public SetTextInstruction(TextView textView, String text){
        this.textView = textView;
        this.text = text;
    }

    public TextView getTextView(){
        return textView;
    }
    public String getText(){ return text;}


}
