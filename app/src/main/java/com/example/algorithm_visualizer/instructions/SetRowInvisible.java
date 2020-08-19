package com.example.algorithm_visualizer.instructions;

import android.widget.TextView;

public class SetRowInvisible implements Instructions {
    private TextView[] row;

    public SetRowInvisible(TextView[] row){
        this.row = row;
    }

    public TextView[] getRow()
    {
        return row;
    }
}
