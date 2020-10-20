package com.example.algorithm_visualizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.algorithm_visualizer.instructions.HighlightMultipleTextViewsInstruction;
import com.example.algorithm_visualizer.instructions.HighlightTextViewInstruction;
import com.example.algorithm_visualizer.instructions.Instructions;
import com.example.algorithm_visualizer.instructions.MoveTextViewInstruction;
import com.example.algorithm_visualizer.instructions.SetTextInstruction;
import com.example.algorithm_visualizer.instructions.SwapInstruction;
import com.example.algorithm_visualizer.instructions.UnhighlightInstruction;

import java.util.ArrayList;
import java.util.Arrays;

public class LiveDemo_InsertionSort extends AppCompatActivity {
    Button nextBtn, resetBtn;
    TextView thirtyTextView, tenTextView, fortyTextView, nineTextView, twentyTextView,
            keyPos,twentyfiveTextView, message;

    Animation_Helper animation_helper = new Animation_Helper();

    int i = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_demo__insertion_sort);

        //---Text Views-------
        thirtyTextView = (TextView) findViewById(R.id.thirtyTextView);
        tenTextView = (TextView) findViewById(R.id.tenTextView);
        fortyTextView = (TextView) findViewById(R.id.fortyTextView);
        nineTextView = (TextView) findViewById(R.id.nineTextView);
        twentyTextView = (TextView) findViewById(R.id.twentyTextView);
        twentyfiveTextView = (TextView) findViewById(R.id.twentyfiveTextView);

        message = (TextView) findViewById(R.id.message);
        keyPos = (TextView) findViewById(R.id.keyPos);

        //---Buttons------
        nextBtn = (Button) findViewById(R.id.nextBtn);
        resetBtn = (Button) findViewById(R.id.resetBtn);


        TextView[] textView_array = new TextView[6];
        int[] array = {30,10,40,9,20,25};
        initialize_textView_array(textView_array);
        ArrayList<Instructions> instructions = new ArrayList<>();
        instructions.add(new SetTextInstruction(message,"Starting Insertion Sort"));
        sort(array, textView_array,instructions);

        resetBtn.setOnClickListener(view -> {
            this.recreate();
        });

        nextBtn.setOnClickListener(view -> {
            if(i == instructions.size()-1){
                instructions.add(new SetTextInstruction(message,"Animation finished"));
                incrementI();
                animation_helper.animate(instructions,i);
                return;
            }
            else {
                incrementI();
                animation_helper.animate(instructions, i);
            }
        });
    }

    void incrementI(){
        i = i+1;
    }



    void sort(int[] array, TextView[] textView_array, ArrayList<Instructions> inst ){
        int n = array.length;
        inst.add(new HighlightMultipleTextViewsInstruction(textView_array,1,5,"Highlight unsorted portion",message));

        for(int i = 1; i < n; i++){
            int key = array[i];
            inst.add(new MoveTextViewInstruction(keyPos,textView_array[i],"Move key position",message));
            int j = i-1;
            if(array[j] <= key){
                inst.add(new SetTextInstruction(message, array[j] + " is not greater than " + key + " so no swapping is done"));
            }
            while(j >= 0 && array[j] > key){
                TextView pair[] = new TextView[2];
                pair[0] = textView_array[j];
                pair[1] = textView_array[j+1];
                inst.add(new SwapInstruction(textView_array,j,j+1,pair[0],pair[1],"Swap until key is in the correct position",message));


                int temp = array[j+1];
                array[j+1] = array[j];
                array[j] = temp;



                j = j-1;
            }
            inst.add(new UnhighlightInstruction(textView_array,key,"Unhighlight key",message));
            array[j+1] = key;
        }
    }


    void initialize_textView_array(TextView [] array){
        array[0] = (thirtyTextView);
        array[1] = (tenTextView);
        array[2] = (fortyTextView);
        array[3] = (nineTextView);
        array[4] = (twentyTextView);
        array[5] = (twentyfiveTextView);

    }



}