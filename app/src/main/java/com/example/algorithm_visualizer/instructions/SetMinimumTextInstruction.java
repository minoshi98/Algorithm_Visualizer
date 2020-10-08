package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;

public class SetMinimumTextInstruction implements  Instructions {
    private TextView textView,  setTextView;
    private String value, text;

    public SetMinimumTextInstruction(TextView textView, String value, String text, TextView setTextView){
        this.textView = textView;
        this.value = value;
        this.text = text;
        this.setTextView = setTextView;
    }

    public TextView getTextView() {
        return textView;
    }

    public String getValue(){
        return value;
    }

    public String getText(){return text;}

    public TextView getSetTextView(){return setTextView;}
}
