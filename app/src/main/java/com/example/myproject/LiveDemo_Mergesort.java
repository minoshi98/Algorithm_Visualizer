package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LiveDemo_Mergesort extends AppCompatActivity {

    TextView fiftyTextView, twentyTextView, sixtyfiveTextView, fifteenTextView, thirtyfiveTextView, fiveTextView,
            firstDivideTextView, nineteenTextView;

    Button startBtn;
    My_Animator animator = new My_Animator();
    int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_demo__mergesort);

        //----TextViews
        fiftyTextView = (TextView) findViewById(R.id.fiftyTextView);
        twentyTextView = (TextView) findViewById(R.id.twentyTextView);
        sixtyfiveTextView = (TextView) findViewById(R.id.sixtyfiveTextView);
        fifteenTextView = (TextView) findViewById(R.id.fifteenTextView);
        thirtyfiveTextView = (TextView) findViewById(R.id.thirtyfiveTextView);
        fiveTextView = (TextView) findViewById(R.id.fiveTextView);
        nineteenTextView = (TextView) findViewById(R.id.nineteenTextView);
        firstDivideTextView = (TextView) findViewById(R.id.firstDivide);

        //--Buttons
        startBtn = (Button) findViewById(R.id.startBtn);
        int[] array = new int[7];
        TextView[] left_subarray = new TextView[4];
        TextView[] right_subarray = new TextView[3];

        TextView[] textView_array = new TextView[7];
        initialize_array(array);
        initialize_textView_array(textView_array);
        initialize_left_subarray(left_subarray);
        initialize_right_subarray(right_subarray);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sort(array,0,array.length-1,textView_array);


            }
        });

    }



    void sort(int[] array, int left, int right,TextView[] textView_array){
        if(left < right){
          //  if(i == 1) {
              //  i++;
                int m = (left + right) / 2;
                firstDivideTextView.setVisibility(View.VISIBLE);

                sort(array, left, m,textView_array); //sort first half
                sort(array,m+1,right,textView_array); //sort second half
                merge(array,left,m,right);
           // }
        }
    }



    void merge(int arr[], int l, int m, int r)
    {
        Log.d("l ", String.valueOf(l));
        Log.d("m ", String.valueOf(m));
        Log.d("r ", String.valueOf(r));

        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    void initialize_array(int[] array){

        array[0] = Integer.parseInt(fiftyTextView.getText().toString());
        array[1] = Integer.parseInt(twentyTextView.getText().toString());
        array[2] = Integer.parseInt(sixtyfiveTextView.getText().toString());
        array[3] = Integer.parseInt(fifteenTextView.getText().toString());
        array[4] = Integer.parseInt(thirtyfiveTextView.getText().toString());
        array[5] = Integer.parseInt(fiveTextView.getText().toString());
        array[6] = Integer.parseInt(nineteenTextView.getText().toString());

    }

    void initialize_textView_array(TextView[] textView_array){

        textView_array[0] = fiftyTextView;
        textView_array[1] = twentyTextView;
        textView_array[2] = sixtyfiveTextView;
        textView_array[3] = fifteenTextView;
        textView_array[4] = thirtyfiveTextView;
        textView_array[5] = fiveTextView;
        textView_array[6] = nineteenTextView;

    }

    void initialize_left_subarray(TextView[] left_subarray){
        left_subarray[0] = fiftyTextView;
        left_subarray[1] = twentyTextView;
        left_subarray[2] = sixtyfiveTextView;
        left_subarray[3] = fifteenTextView;
    }

    void initialize_right_subarray(TextView[] right_subarray){
        right_subarray[0] = thirtyfiveTextView;
        right_subarray[1] = fiveTextView;
        right_subarray[2] = nineteenTextView;
    }



}