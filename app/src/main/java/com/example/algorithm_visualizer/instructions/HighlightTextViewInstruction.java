package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;


public class HighlightTextViewInstruction implements Instructions {

    private TextView textView, setTextView;
    private String text;
    public HighlightTextViewInstruction(TextView textView,String text, TextView setTextView){
        this.textView = textView;
        this.text = text;
        this.setTextView = setTextView;

    }

    public TextView getTextView(){return textView;}
    public String getText(){return text;}
    public TextView getSetTextView(){return setTextView;}

}
