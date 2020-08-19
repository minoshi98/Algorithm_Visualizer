package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;

public class SetTextInstruction implements Instructions{
    private TextView[] row;
    private String findValue, setValue;


    public SetTextInstruction(TextView[] row, String findValue, String setValue){
        this.row = row;
        this.findValue = findValue;
        this.setValue = setValue;
    }

    public TextView[] getRow(){
        return row;
    }
    public String getFindValue() { return findValue;}
    public String getSetValue () { return  setValue;}

}
