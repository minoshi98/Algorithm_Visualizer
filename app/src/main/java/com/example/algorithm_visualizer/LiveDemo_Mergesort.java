package com.example.algorithm_visualizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class LiveDemo_Mergesort extends AppCompatActivity {

    TextView row0col0, row0col1, row0col2, row0col3, row0col4, row0col5,
            row0col6, row1col0, row1col1, row1col2, row1col3, row1col4, row1col5, row1col6, row2col0, row2col1, row2col2, row2col3,
            row2col4, row2col5, row2col6,row3col0, row3col1, row3col2, row3col3, row3col4, row3col5, row3col6;
    GridLayout firstrow,secondrow,thirdrow;

    Button startBtn;
    Animation_Helper animator = new Animation_Helper();
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

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sort(array, 0, array.length - 1, grid_rows,row_arrays);

            }
        });

    }


    void sort(int[] array, int left, int right, ArrayList<GridLayout> grid_rows,ArrayList<TextView[]> row_arrays ) {
        if (left < right) {

            while(i < 2) {
                int m = (left + right) / 2;
                i++;

                animator.divide(row_arrays.get(i), row_arrays.get(i+1), left,right,m,() -> {
                    sort(array, left, m, grid_rows,row_arrays); //sort first half
                  //  sort(array, m + 1, right, grid_rows,row_arrays); //sort second half
                 //   merge(array, left, m, right);
                 });
                return;
            }

        }
    }


        void merge ( int arr[], int l, int m, int r)
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
                } else {
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