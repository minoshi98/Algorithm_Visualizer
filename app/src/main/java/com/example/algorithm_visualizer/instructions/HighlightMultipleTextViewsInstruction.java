package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;

public class HighlightMultipleTextViewsInstruction implements Instructions {

    private TextView[] textViews;
    private TextView textView;
    private String text;
    private int start, end;


    public HighlightMultipleTextViewsInstruction(TextView[] textViews, int start, int end, String text, TextView textView){
        this.textViews = textViews;
        this.start = start;
        this.end = end;
        this.text = text;
        this.textView = textView;
    }

    public TextView[] getTextViews(){return textViews;}
    public int getStart(){return start;}
    public int getEnd(){return end;}
    public String getText(){return text;}
    public TextView getTextView(){return textView;}


}
