package com.example.algorithm_visualizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.algorithm_visualizer.instructions.BlinkInstruction;
import com.example.algorithm_visualizer.instructions.HighlightInstruction;
import com.example.algorithm_visualizer.instructions.Instructions;
import com.example.algorithm_visualizer.instructions.SetMinimumTextInstruction;
import com.example.algorithm_visualizer.instructions.SetRowInvisible;
import com.example.algorithm_visualizer.instructions.SetTextInstruction;
import com.example.algorithm_visualizer.instructions.SetVisibileInstruction;
import com.example.algorithm_visualizer.instructions.UnhighlightInstruction;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class LiveDemo_Mergesort extends AppCompatActivity {

    TextView row0col0, row0col1, row0col2, row0col3, row0col4, row0col5,
            row0col6, row1col0, row1col1, row1col2, row1col3, row1col4, row1col5, row1col6, row2col0, row2col1, row2col2, row2col3,
            row2col4, row2col5, row2col6,row3col0, row3col1, row3col2, row3col3, row3col4, row3col5, row3col6, mergeValues;
    GridLayout firstrow,secondrow,thirdrow;

    Button nextBtn, prevBtn, startBtn;

    //Animation helpers
    Animation_Helper animation_helper = new Animation_Helper();

    //--end

    int increment = -1;
    int enteredmerge = 0;
    int i = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_demo__mergesort);

        //----TextViews
        row0col0 = (TextView) findViewById(R.id.row0col0);
        row0col1 = (TextView) findViewById(R.id.row0col1);
        row0col2 = (TextView) findViewById(R.id.row0col2);
        row0col3 = (TextView) findViewById(R.id.row0col3);
        row0col4 = (TextView) findViewById(R.id.row0col4);
        row0col5 = (TextView) findViewById(R.id.row0col5);
        row0col6 = (TextView) findViewById(R.id.row0col6);

        row1col0 = (TextView) findViewById(R.id.row1col0);
        row1col1 = (TextView) findViewById(R.id.row1col1);
        row1col2 = (TextView) findViewById(R.id.row1col2);
        row1col3 = (TextView) findViewById(R.id.row1col3);
        row1col4 = (TextView) findViewById(R.id.row1col4);
        row1col5 = (TextView) findViewById(R.id.row1col5);
        row1col6 = (TextView) findViewById(R.id.row1col6);

        row2col0 = (TextView) findViewById(R.id.row2col0);
        row2col1 = (TextView) findViewById(R.id.row2col1);
        row2col2 = (TextView) findViewById(R.id.row2col2);
        row2col3 = (TextView) findViewById(R.id.row2col3);
        row2col4 = (TextView) findViewById(R.id.row2col4);
        row2col5 = (TextView) findViewById(R.id.row2col5);
        row2col6 = (TextView) findViewById(R.id.row2col6);

        row3col0 = (TextView) findViewById(R.id.row3col0);
        row3col1 = (TextView) findViewById(R.id.row3col1);
        row3col2 = (TextView) findViewById(R.id.row3col2);
        row3col3 = (TextView) findViewById(R.id.row3col3);
        row3col4 = (TextView) findViewById(R.id.row3col4);
        row3col5 = (TextView) findViewById(R.id.row3col5);
        row3col6 = (TextView) findViewById(R.id.row3col6);



        //--Buttons
        //nextBtn = (Button) findViewById(R.id.nextBtn);
     //   prevBtn = (Button) findViewById(R.id.prevBtn);
        startBtn = (Button) findViewById(R.id.startBtn);

        int[] array = new int[7];

        //Grid Layouts (rows)
        firstrow = (GridLayout) findViewById(R.id.firstrow);
        secondrow = (GridLayout) findViewById(R.id.secondrow);
        thirdrow = (GridLayout) findViewById(R.id.thirdrow);

        //TextViews (rows)
        TextView[] firstrow_array = new TextView[7];
        TextView[] secondrow_array = new TextView[7];
        TextView[] thirdrow_array = new TextView[7];
        TextView[] fourthrow_array = new TextView[7];

        initialize_array(array);
        initialize_firstrow_array(firstrow_array);
        initialize_secondrow_array(secondrow_array);
        initialize_thirdrow_array(thirdrow_array);
        initialize_fourthrow_array(fourthrow_array);

        ArrayList<GridLayout> grid_rows = new ArrayList<GridLayout>();
        grid_rows.add(firstrow);
        grid_rows.add(secondrow);
        grid_rows.add(thirdrow);

        ArrayList<TextView[]> row_arrays = new ArrayList<TextView[]>();
        row_arrays.add(firstrow_array);
        row_arrays.add(secondrow_array);
        row_arrays.add(thirdrow_array);
        row_arrays.add(fourthrow_array);

        ArrayList<Instructions> inst = new ArrayList<Instructions>();
        merge_sort(array,0,array.length-1, inst, row_arrays);
        startBtn.setOnClickListener(view -> {
          //merge_sort(array,0,array.length-1, inst, row_arrays);
            animation_helper.animate(inst,0);

        });
        /*
        nextBtn.setOnClickListener(view -> {
            incrementI();
         //   merge_sort(array, 0, array.length - 1, inst,row_arrays);

            animation_helper.animate(inst,i);
            Log.d("INST", String.valueOf(inst.get(i)));
       //     animation_helper.displayArrayList(textViewsToBeHighlighted);
        });

        prevBtn.setOnClickListener(view -> {
            try {
                decrementI();
                merge_sort(array, 0, array.length - 1, inst, row_arrays);
                Log.d("done", "yes");
                animation_helper.animate(inst, i);
            }
            catch(ArrayIndexOutOfBoundsException e){
                Log.e("ERROR", "out of range");
            }
        });

         */
    }

    void incrementI(){
        i = i+1;
    }

    void decrementI(){
        i = i - 1;
    }

    void merge_sort(int[] array, int left, int right, ArrayList<Instructions> inst, ArrayList<TextView[]> row_arrays ) {
      //  while(i < 2) {
            if (left < right) {

                int m = (left + right) / 2;
                increment++;
                if(increment > 2) increment = increment - 1;

                Log.d("i", String.valueOf(increment));


                if(increment <= row_arrays.size()-2) {
                    inst.add(new BlinkInstruction(row_arrays.get(increment), left, right));
                    inst.add(new SetVisibileInstruction(row_arrays.get(increment + 1), left, right, m));
                }

                merge_sort(array, left, m, inst, row_arrays); //sort first half
                merge_sort(array, m + 1, right, inst, row_arrays); //sort second half
                merge(array, left, m, right, inst, row_arrays);

            }


       // }
    }


        void merge ( int arr[], int l, int m, int r, ArrayList<Instructions> inst, ArrayList<TextView[]> row_arrays)
        {
            Log.d("merge i",String.valueOf(increment));
            enteredmerge++;
            // Creating temporary subarrays
            int[] leftArray = new int[m - l + 1];
            int[] rightArray = new int[r - m];

            // Copying our subarrays into temporaries
            for (int i = 0; i < leftArray.length; i++) {
                leftArray[i] = arr[l + i];

                    Log.e("leftArray[i]", String.valueOf(leftArray[i]));



            }
            for (int i = 0; i < rightArray.length; i++) {
                rightArray[i] = arr[m + i + 1];

                    Log.e("rightArray[i]", String.valueOf(rightArray[i]));


            }

            inst.add(new HighlightInstruction(row_arrays.get(increment+1), leftArray,rightArray));

            Log.d("left array", Arrays.toString(leftArray));
            Log.d("right array", Arrays.toString(rightArray));

            // Iterators containing current index of temp subarrays
            int leftIndex = 0;
            int rightIndex = 0;

            // Copying from leftArray and rightArray back into array
            for (int j = l; j < r + 1; j++) {

                // If there are still uncopied elements in R and L, copy minimum of the two
                if (leftIndex < leftArray.length && rightIndex < rightArray.length) {

                    if (leftArray[leftIndex] < rightArray[rightIndex]) {

                        inst.add(new UnhighlightInstruction(row_arrays.get(increment+1), leftArray[leftIndex]));
                        inst.add(new SetTextInstruction(row_arrays.get(increment+1), String.valueOf(leftArray[leftIndex]), ""));

                        Log.d("leftIndex", String.valueOf(leftArray[leftIndex]));

                        arr[j] = leftArray[leftIndex];
                        inst.add(new SetMinimumTextInstruction(row_arrays.get(increment)[j], String.valueOf(leftArray[leftIndex])));


                        leftIndex++;

                    } else {
                        inst.add(new UnhighlightInstruction(row_arrays.get(increment+1), rightArray[rightIndex]));
                        inst.add(new SetTextInstruction(row_arrays.get(increment+1), String.valueOf(rightArray[rightIndex]), ""));

                        Log.d("rightIndex", String.valueOf(rightArray[rightIndex]));


                        arr[j] = rightArray[rightIndex];
                        inst.add(new SetMinimumTextInstruction(row_arrays.get(increment)[j], String.valueOf(rightArray[rightIndex])));

                        rightIndex++;
                    }
                }


                else if (leftIndex < leftArray.length) {

                    // If all elements have been copied from rightArray, copy rest of leftArray
                    Log.d("left remaining", String.valueOf(leftArray[leftIndex]));
                    inst.add(new UnhighlightInstruction(row_arrays.get(increment+1), leftArray[leftIndex]));
                    inst.add(new SetTextInstruction(row_arrays.get(increment+1), String.valueOf(leftArray[leftIndex]), ""));


                    arr[j] = leftArray[leftIndex];
                    inst.add(new SetMinimumTextInstruction(row_arrays.get(increment)[j], String.valueOf(leftArray[leftIndex])));


                    leftIndex++;
                } else if (rightIndex < rightArray.length) {

                    // If all elements have been copied from leftArray, copy rest of rightArray
                    Log.d("right remaining", String.valueOf(rightArray[rightIndex]));
                    inst.add(new UnhighlightInstruction(row_arrays.get(increment+1), rightArray[rightIndex]));
                    inst.add(new SetTextInstruction(row_arrays.get(increment+1), String.valueOf(rightArray[rightIndex]), ""));



                    arr[j] = rightArray[rightIndex];
                    inst.add(new SetMinimumTextInstruction(row_arrays.get(increment)[j], String.valueOf(rightArray[rightIndex])));

                    rightIndex++;
                }
            }

            inst.add(new SetRowInvisible(row_arrays.get(increment+1)));
            increment = increment - 1;
            Log.d("Array", Arrays.toString(arr));
        }


        void initialize_array ( int[] array){

            array[0] = Integer.parseInt(row0col0.getText().toString());
            array[1] = Integer.parseInt(row0col1.getText().toString());
            array[2] = Integer.parseInt(row0col2.getText().toString());
            array[3] = Integer.parseInt(row0col3.getText().toString());
            array[4] = Integer.parseInt(row0col4.getText().toString());
            array[5] = Integer.parseInt(row0col5.getText().toString());
            array[6] = Integer.parseInt(row0col6.getText().toString());

        }

        void initialize_firstrow_array (TextView[]firstrow_array){

            firstrow_array[0] = row0col0;
            firstrow_array[1] = row0col1;
            firstrow_array[2] = row0col2;
            firstrow_array[3] = row0col3;
            firstrow_array[4] = row0col4;
            firstrow_array[5] = row0col5;
            firstrow_array[6] = row0col6;

        }

        void initialize_secondrow_array (TextView[]secondrow_array){

            secondrow_array[0] = row1col0;
            secondrow_array[1] = row1col1;
            secondrow_array[2] = row1col2;
            secondrow_array[3] = row1col3;
            secondrow_array[4] = row1col4;
            secondrow_array[5] = row1col5;
            secondrow_array[6] = row1col6;

        }


        void initialize_thirdrow_array (TextView[]thirdrow_array){

            thirdrow_array[0] = row2col0;
            thirdrow_array[1] = row2col1;
            thirdrow_array[2] = row2col2;
            thirdrow_array[3] = row2col3;
            thirdrow_array[4] = row2col4;
            thirdrow_array[5] = row2col5;
            thirdrow_array[6] = row2col6;

        }

        void initialize_fourthrow_array(TextView[] fourthrow_array){

            fourthrow_array[0] = row3col0;
            fourthrow_array[1] = row3col1;
            fourthrow_array[2] = row3col2;
            fourthrow_array[3] = row3col3;
            fourthrow_array[4] = row3col4;
            fourthrow_array[5] = row3col5;
            fourthrow_array[6] = row3col6;

        }


}