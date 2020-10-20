package com.example.algorithm_visualizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.algorithm_visualizer.instructions.BlinkInstruction;
import com.example.algorithm_visualizer.instructions.HighlightInstruction;
import com.example.algorithm_visualizer.instructions.Instructions;
import com.example.algorithm_visualizer.instructions.SetMinimumTextInstruction;
import com.example.algorithm_visualizer.instructions.SetMultipleTextInstruction;
import com.example.algorithm_visualizer.instructions.SetRowInvisible;
import com.example.algorithm_visualizer.instructions.SetTextInstruction;
import com.example.algorithm_visualizer.instructions.SetTextViewInvisibleInstruction;
import com.example.algorithm_visualizer.instructions.SetVisibileInstruction;
import com.example.algorithm_visualizer.instructions.UnhighlightInstruction;

import java.util.ArrayList;



public class LiveDemo_Mergesort extends AppCompatActivity {

    TextView row0col0, row0col1, row0col2, row0col3, row0col4, row0col5,
            row0col6, row1col0, row1col1, row1col2, row1col3, row1col4, row1col5, row1col6, row2col0, row2col1, row2col2, row2col3,
            row2col4, row2col5, row2col6,row3col0, row3col1, row3col2, row3col3, row3col4, row3col5, row3col6, message;
    GridLayout firstrow,secondrow,thirdrow;

    Button nextBtn, resetBtn;

    //Animation helpers
    Animation_Helper animation_helper = new Animation_Helper();


    int increment = -1;
    int i = -1;
    boolean rightCalled = false;



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


        message = (TextView) findViewById(R.id.message);

        //--Buttons
        nextBtn = (Button) findViewById(R.id.nextBtn);
        resetBtn = (Button) findViewById(R.id.resetBtn);

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

        resetBtn.setOnClickListener(view -> {
            this.recreate();
        });
        merge_sort(array,0,array.length-1, inst, row_arrays);

        nextBtn.setOnClickListener(view -> {
            if(i == inst.size()-1){
                inst.add(new SetTextInstruction(message,"Animation finished"));
                incrementI();
                animation_helper.animate(inst,i);
                return;
            }
            else {
                incrementI();
                animation_helper.animate(inst, i);
            }
        });




    }


    void incrementI(){
        i = i+1;
    }


    void merge_sort(int[] array, int left, int right, ArrayList<Instructions> inst, ArrayList<TextView[]> row_arrays ) {
            if (left < right) {

                int m = (left + right) / 2;
                increment++;
                if(increment > 2) increment = increment - 1;

                if(increment <= row_arrays.size()-2) {
                    if(right == array.length-1 && left == 0)
                        inst.add(new BlinkInstruction(row_arrays.get(increment), left, right,"Select entire array",message));
                    else if(rightCalled)
                        inst.add(new BlinkInstruction(row_arrays.get(increment), left, right,"Select right sub array",message));
                    else
                        inst.add(new BlinkInstruction(row_arrays.get(increment), left, right,"Select left sub array",message));

                    inst.add(new SetVisibileInstruction(row_arrays.get(increment + 1), left, right, m,"Split array as evenly as possible",message));
                }

                rightCalled = false;
                merge_sort(array, left, m, inst, row_arrays); //sort first half
                rightCalled = true;
                merge_sort(array, m + 1, right, inst, row_arrays); //sort second half
                merge(array, left, m, right, inst, row_arrays);

            }


    }


        void merge ( int arr[], int l, int m, int r, ArrayList<Instructions> inst, ArrayList<TextView[]> row_arrays)
        {
            // Creating temporary subarrays
            int[] leftArray = new int[m - l + 1];
            int[] rightArray = new int[r - m];

            // Copying our subarrays into temporaries
            for (int i = 0; i < leftArray.length; i++)
                leftArray[i] = arr[l + i];


            for (int i = 0; i < rightArray.length; i++)
                rightArray[i] = arr[m + i + 1];


            if(r - l == 1)
                inst.add(new SetMultipleTextInstruction(row_arrays.get(increment), leftArray,rightArray,"An array of length 1 cannot be split, ready for merge",message));
            else
                inst.add(new SetMultipleTextInstruction(row_arrays.get(increment), leftArray,rightArray,"Merge selected arrays back together, in sorted order",message));


            inst.add(new HighlightInstruction(row_arrays.get(increment+1), leftArray,rightArray));

            // Iterators containing current index of temp subarrays
            int leftIndex = 0;
            int rightIndex = 0;

            // Copying from leftArray and rightArray back into array
            for (int j = l; j < r + 1; j++) {

                // If there are still uncopied elements in R and L, copy minimum of the two
                if (leftIndex < leftArray.length && rightIndex < rightArray.length) {

                    if (leftArray[leftIndex] < rightArray[rightIndex]) {

                        inst.add(new UnhighlightInstruction(row_arrays.get(increment+1), leftArray[leftIndex],"Select minimum value",message));
                        inst.add(new SetTextViewInvisibleInstruction(row_arrays.get(increment+1), String.valueOf(leftArray[leftIndex])));

                        arr[j] = leftArray[leftIndex];
                        inst.add(new SetMinimumTextInstruction(row_arrays.get(increment)[j], String.valueOf(leftArray[leftIndex]),"Add value to the sorted array",message));


                        leftIndex++;

                    } else {
                        inst.add(new UnhighlightInstruction(row_arrays.get(increment+1), rightArray[rightIndex],"Select minimum value",message));
                        inst.add(new SetTextViewInvisibleInstruction(row_arrays.get(increment+1), String.valueOf(rightArray[rightIndex])));

                        arr[j] = rightArray[rightIndex];
                        inst.add(new SetMinimumTextInstruction(row_arrays.get(increment)[j], String.valueOf(rightArray[rightIndex]),"Add value to the sorted array",message));

                        rightIndex++;
                    }
                }


                else if (leftIndex < leftArray.length) {

                    // If all elements have been copied from rightArray, copy rest of leftArray
                    inst.add(new UnhighlightInstruction(row_arrays.get(increment+1), leftArray[leftIndex],"Select remaining value",message));
                    inst.add(new SetTextViewInvisibleInstruction(row_arrays.get(increment+1), String.valueOf(leftArray[leftIndex])));


                    arr[j] = leftArray[leftIndex];
                    inst.add(new SetMinimumTextInstruction(row_arrays.get(increment)[j], String.valueOf(leftArray[leftIndex]),"Add value to the sorted array",message));


                    leftIndex++;
                } else if (rightIndex < rightArray.length) {

                    // If all elements have been copied from leftArray, copy rest of rightArray
                    inst.add(new UnhighlightInstruction(row_arrays.get(increment+1), rightArray[rightIndex],"Select remaining value",message));
                    inst.add(new SetTextViewInvisibleInstruction(row_arrays.get(increment+1), String.valueOf(rightArray[rightIndex])));



                    arr[j] = rightArray[rightIndex];
                    inst.add(new SetMinimumTextInstruction(row_arrays.get(increment)[j], String.valueOf(rightArray[rightIndex]),"Add value to the sorted array",message));

                    rightIndex++;
                }
            }

            inst.add(new SetRowInvisible(row_arrays.get(increment+1)));
            increment = increment - 1;
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