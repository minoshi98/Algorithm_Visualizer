package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;

public class MoveTextViewInstruction implements Instructions {

    private TextView firstPos, secondPos, setTextView;
    private String text;

    public MoveTextViewInstruction(TextView firstPos, TextView secondPos, String text, TextView setTextView){
        this.firstPos = firstPos;
        this.secondPos = secondPos;
        this.setTextView = setTextView;
        this.text = text;
    }

    public TextView getFirstPos(){return firstPos;}
    public TextView getSecondPos(){return secondPos;}
    public TextView getSetTextView(){return setTextView;}
    public String getText(){return text;}
}
