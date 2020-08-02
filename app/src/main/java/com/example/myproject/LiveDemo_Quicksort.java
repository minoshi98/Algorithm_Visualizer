package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class LiveDemo_Quicksort extends AppCompatActivity {
    Button startBtn;
    TextView thirtyTextView, tenTextView, fortyTextView, nineTextView, twentyTextView, twentyfiveTextView,
    lowLabelTextView, highLabelTextView, lowValueTextView, highValueTextView, pivotLabelTextView,
            ivalueTextView, jvalueTextView, pivotValueTextView, conditionTextView, jpositionTextView, ipositionTextView;


    AnimatorSet set;

    My_Animator myAnimator = new My_Animator();

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
                ArrayList<TextView[]> instructions = new ArrayList<TextView[]>();


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

                swap(instructions,textView_array,i);

                }

        });
    }


    public void swap(ArrayList<TextView[]> instructions, TextView[] array, int i) {
        if (i >= instructions.size()) {
          return;
        }
        TextView[] instruction = instructions.get(i);
        int leftIndex = Arrays.asList(array).indexOf(instruction[0]);
        int rightIndex = Arrays.asList(array).indexOf(instruction[1]);

        if (leftIndex == rightIndex) {
            swap(instructions, array, i + 1);
            return;
        }

        myAnimator.swapElements(array, array[leftIndex], array[rightIndex], () -> {
            // on Animation end code

            //need to swap text views here
            TextView temp = array[leftIndex];
            array[leftIndex] = array[rightIndex];
            array[rightIndex] = temp;

            swap(instructions, array, i + 1);


        });
    }






    public void quicksort(int arr[], int low, int high,ArrayList<TextView[]> inst,TextView[] textView_array) {

        if(low < high) {

            int pi = partition(arr,low,high,inst,textView_array);

            quicksort(arr,low, (pi-1),inst,textView_array); //before partition
            quicksort(arr, (pi+1), high,inst,textView_array); //after partition

        }


    }

    public int partition(int arr[], int low, int high,ArrayList<TextView[]> inst,TextView[] textView_array) {

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
                inst.add(pair);

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
        inst.add(pair);

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
/*
    public void swapElements(TextView iposition, TextView jposition, AnimatorSet set) {


        int[] i_location = new int[2];
        int[] j_location = new int[2];

        iposition.getLocationOnScreen(i_location);
        jposition.getLocationOnScreen(j_location);

        final float verticalDistance = (i_location[1]);

        ValueAnimator right = ValueAnimator.ofFloat(i_location[0],  j_location[0]);
        right.setDuration(3000);
        right.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float progress = (float) valueAnimator.getAnimatedValue();
                iposition.setX(progress);
            }
        });


        ValueAnimator left = ValueAnimator.ofFloat(j_location[0],  i_location[0]);
        left.setDuration(3000);
        left.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float progress = (float) valueAnimator.getAnimatedValue();
                jposition.setX(progress);
            }
        });


        set.playTogether(right,left);

        /*
        ValueAnimator up = ValueAnimator.ofFloat(0f, -90f);
        up.setDuration(3000);
        up.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float progress = (float) valueAnimator.getAnimatedValue();
                iposition.setTranslationY(progress);
            }
        });


        ValueAnimator down = ValueAnimator.ofFloat(0f, 90f);
        down.setDuration(3000);
        down.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float progress = (float) valueAnimator.getAnimatedValue();
                jposition.setTranslationY(progress);
            }
        });


        ValueAnimator right = ValueAnimator.ofFloat(0f, (float) j_location[0] - i_location[0]);
        right.setDuration(3000);
        right.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float progress = (float) valueAnimator.getAnimatedValue();
                iposition.setTranslationX(progress);
            }
        });

        ValueAnimator left = ValueAnimator.ofFloat(0f, (float) i_location[0] - j_location[0]);
        left.setDuration(3000);
        left.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float progress = (float) valueAnimator.getAnimatedValue();
                jposition.setTranslationX(progress);
            }
        });

        ValueAnimator down2 = ValueAnimator.ofFloat(0f, (float) (verticalDistance - i_location[1]));
        down2.setDuration(3000);
        down2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float progress = (float) valueAnimator.getAnimatedValue();
                iposition.setTranslationY(progress);
            }
        });

        ValueAnimator up2 = ValueAnimator.ofFloat(0f, (float) -(j_location[1] - verticalDistance));
        up2.setDuration(3000);
        up2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float progress = (float) valueAnimator.getAnimatedValue();
                jposition.setTranslationY(progress);
            }
        });


      //  AnimatorSet set = new AnimatorSet();
        set.playTogether(up, down);
        set.play(up).before(right);
        set.play(down).before(left);
        set.play(right).before(down2);
        set.play(left).before(up2);

         */
     //   set.start();
/*
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                checkAnimationEnded();
            }
        });
*/




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