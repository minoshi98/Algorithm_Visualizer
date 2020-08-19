package com.example.algorithm_visualizer;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.algorithm_visualizer.instructions.Instructions;
import com.example.algorithm_visualizer.instructions.SwapInstruction;

public class LiveDemo_Quicksort extends AppCompatActivity {
    Button startBtn;
    TextView thirtyTextView, tenTextView, fortyTextView, nineTextView, twentyTextView, twentyfiveTextView,
    lowLabelTextView, highLabelTextView, lowValueTextView, highValueTextView, pivotLabelTextView,
            ivalueTextView, jvalueTextView, pivotValueTextView, conditionTextView, jpositionTextView, ipositionTextView;

    Animation_Helper animation_helper = new Animation_Helper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_demo__quicksort);

        //---Text Views-------
        thirtyTextView = (TextView) findViewById(R.id.thirtyTextView);
        tenTextView = (TextView) findViewById(R.id.tenTextView);
        fortyTextView = (TextView) findViewById(R.id.fortyTextView);
        nineTextView = (TextView) findViewById(R.id.nineTextView);
        twentyTextView = (TextView) findViewById(R.id.twentyTextView);
        twentyfiveTextView = (TextView) findViewById(R.id.twentyfiveTextView);
        lowLabelTextView = (TextView) findViewById(R.id.lowLabelTextView);
        highLabelTextView = (TextView) findViewById(R.id.highLabelTextView);
        lowValueTextView = (TextView) findViewById(R.id.lowValueTextView);
        highValueTextView = (TextView) findViewById(R.id.highValueTextView);
        pivotLabelTextView = (TextView) findViewById(R.id.pivotLabelTextView);
        pivotValueTextView = (TextView) findViewById(R.id.pivotValueTextView);
        ivalueTextView = (TextView) findViewById(R.id.ivalueTextView);
        jvalueTextView = (TextView) findViewById(R.id.jvalueTextView);


        //---Buttons------
        startBtn = (Button) findViewById(R.id.startBtn);




        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView[] textView_array = new TextView[6];
                int[] array = new int[6];
                initialize_textView_array(textView_array);
                initialize_array(array);
                ArrayList<Instructions> instructions = new ArrayList<>();


                int low = 0;
                int high = array.length-1;
                int i = 0;


                //initialize variables
                lowValueTextView.setText(String.valueOf(low));
                highValueTextView.setText(String.valueOf(high));
                pivotValueTextView.setText((textView_array[high].getText().toString()));
                ivalueTextView.setText("i = " + String.valueOf(low - 1));
                jvalueTextView.setText("j = 0");


                quicksort(array,low,high,instructions,textView_array);
                animation_helper.animate(instructions,0);


                }

        });
    }


    public void quicksort(int arr[], int low, int high,ArrayList<Instructions> inst,TextView[] textView_array) {

        if(low < high) {

            int pi = partition(arr,low,high,inst,textView_array);

            quicksort(arr,low, (pi-1),inst,textView_array); //before partition
            quicksort(arr, (pi+1), high,inst,textView_array); //after partition

        }


    }

    public int partition(int arr[], int low, int high, ArrayList<Instructions> inst, TextView[] textView_array) {


        int pivot = arr[high];
        int temp = 0;
        int i = (low - 1);

        for(int j = low; j <= (high - 1); j++) {

            if(arr[j] < pivot) {

                i++;

                //add pairs
                TextView pair[] = new TextView[2];
                pair[0] = textView_array[i];
                pair[1] = textView_array[j];
                inst.add(new SwapInstruction(textView_array, pair[0], pair[1]));

                //swap arr[i] and arr[j]
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                //swap textview[i] and textview[j]
                TextView temp2 = textView_array[i];
                textView_array[i] = textView_array[j];
                textView_array[j] = temp2;



            }
        }

        //add pair
        TextView[] pair = new TextView[2];
        pair[0] = textView_array[i+1];
        pair[1] = textView_array[high];
        inst.add(new SwapInstruction(textView_array, pair[0], pair[1]));

        //swap arr[i+1] and arr[high]
        temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;


        //swap textview[i+1] and textview[high]
        TextView temp2 = textView_array[i+1];
        textView_array[i+1] = textView_array[high];
        textView_array[high] = temp2;

        return (i+1);

    }


    public void initialize_textView_array(TextView [] array){
        array[0] = (thirtyTextView);
        array[1] = (tenTextView);
        array[2] = (fortyTextView);
        array[3] = (nineTextView);
        array[4] = (twentyTextView);
        array[5] = (twentyfiveTextView);

    }

    public void initialize_array(int[] array){
        array[0] = (Integer.parseInt(thirtyTextView.getText().toString()));
        array[1] = (Integer.parseInt(tenTextView.getText().toString()));
        array[2] = (Integer.parseInt(fortyTextView.getText().toString()));
        array[3] = (Integer.parseInt(nineTextView.getText().toString()));
        array[4] = (Integer.parseInt(twentyTextView.getText().toString()));
        array[5] = (Integer.parseInt(twentyfiveTextView.getText().toString()));

    }





}