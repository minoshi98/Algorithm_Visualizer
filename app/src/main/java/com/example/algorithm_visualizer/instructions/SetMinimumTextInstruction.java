package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;

public class SetMinimumTextInstruction implements  Instructions {
    TextView textView;
    String value;
    public SetMinimumTextInstruction(TextView textView, String value){
        this.textView = textView;
        this.value = value;
    }

    public TextView getTextView() {
        return textView;
    }

    public String getValue(){
        return value;
    }
}
