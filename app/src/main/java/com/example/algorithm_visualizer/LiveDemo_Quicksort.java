package com.example.algorithm_visualizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

import com.example.algorithm_visualizer.instructions.HighlightTextViewInstruction;
import com.example.algorithm_visualizer.instructions.Instructions;
import com.example.algorithm_visualizer.instructions.MoveTextViewInstruction;
import com.example.algorithm_visualizer.instructions.SetTextInstruction;
import com.example.algorithm_visualizer.instructions.SwapInstruction;
import com.example.algorithm_visualizer.instructions.UnhighlightInstruction;

public class LiveDemo_Quicksort extends AppCompatActivity {
    Button nextBtn;
    TextView thirtyTextView, tenTextView, fortyTextView, nineTextView, twentyTextView, twentyfiveTextView,iPosition, jPosition, i_origPos,message;

    Animation_Helper animation_helper = new Animation_Helper();

    int i = -1;
    boolean afterPartitionCalled = false;

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

        message = (TextView) findViewById(R.id.message);
        iPosition = (TextView) findViewById(R.id.iPosition);
        jPosition = (TextView) findViewById(R.id.jPosition);
        i_origPos = (TextView) findViewById(R.id.i_origPos);

        //---Buttons------
        nextBtn = (Button) findViewById(R.id.nextBtn);

        TextView[] textView_array = new TextView[6];
        int[] array = new int[6];
        initialize_textView_array(textView_array);
        initialize_array(array);
        ArrayList<Instructions> instructions = new ArrayList<>();

        instructions.add(new SetTextInstruction(message,"Partition starts"));
        quicksort(array,0,array.length-1,instructions,textView_array);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                incrementI();
                animation_helper.animate(instructions,i,true,false);
            }

        });
    }

    void incrementI(){
        i = i+1;
    }

    public void quicksort(int arr[], int low, int high,ArrayList<Instructions> inst,TextView[] textView_array) {

        if(low < high) {

            int pi = partition(arr,low,high,inst,textView_array);

            //pivot is at right place
            if(!afterPartitionCalled)
                inst.add(new SetTextInstruction(message,"Now repeat process for left sub array"));
            quicksort(arr,low, (pi-1),inst,textView_array); //before partition
            afterPartitionCalled = true;
            inst.add(new SetTextInstruction(message,"Now repeat process for right sub array"));
            quicksort(arr, (pi+1), high,inst,textView_array); //after partition

        }


    }

    public int partition(int arr[], int low, int high, ArrayList<Instructions> inst, TextView[] textView_array) {


        int pivot = arr[high];
        inst.add(new HighlightTextViewInstruction(textView_array[high],"Highlight pivot",message));
        int temp = 0;
        int i = (low - 1);
        if(i == -1)
            inst.add(new MoveTextViewInstruction(iPosition,i_origPos,"Initialize i", message));
        else
            inst.add(new MoveTextViewInstruction(iPosition,textView_array[low],"Initialize i", message));

        if(low == 0)
            inst.add(new MoveTextViewInstruction(jPosition,i_origPos,"Initialize j", message));

        else
            inst.add(new MoveTextViewInstruction(jPosition,textView_array[low],"Initialize j", message));

        for(int j = low; j <= (high - 1); j++) {

            inst.add(new MoveTextViewInstruction(jPosition,textView_array[j],"Increment j", message));
            if(arr[j] < pivot) {

                i++;
                inst.add(new MoveTextViewInstruction(iPosition,textView_array[i],arr[j] + " is less than " +
                        pivot + " so increment i", message));

                //add pairs
                TextView pair[] = new TextView[2];
                pair[0] = textView_array[i];
                pair[1] = textView_array[j];
                if(i == j) inst.add(new SetTextInstruction(message,"i == j so nothing is swapped"));
                else inst.add(new SwapInstruction(pair[0], pair[1], "Then swap i and j",message));

                //swap arr[i] and arr[j]
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                //swap textview[i] and textview[j]
                TextView temp2 = textView_array[i];
                textView_array[i] = textView_array[j];
                textView_array[j] = temp2;



            }
            else
            inst.add(new SetTextInstruction(message,arr[j] + " is not < " + pivot + " so i is not incremented"));

        }

        //add pair
        TextView[] pair = new TextView[2];
        pair[0] = textView_array[i+1];
        pair[1] = textView_array[high];
        if((i+1) == high) inst.add(new SetTextInstruction(message,"Pivot is already in correct position"));
        else inst.add(new SwapInstruction(pair[0], pair[1], "Place pivot in correct position so that elements " +
                "less than the pivot are to the left of the pivot and " +
                "elements greater are to the right of the pivot",message));

        //swap arr[i+1] and arr[high]
        temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;


        //swap textview[i+1] and textview[high]
        TextView temp2 = textView_array[i+1];
        textView_array[i+1] = textView_array[high];
        textView_array[high] = temp2;

        inst.add(new SetTextInstruction(message,"Pivot is now in the correct position"));
        inst.add(new UnhighlightInstruction(textView_array,arr[i+1],"Partition is done",message));

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