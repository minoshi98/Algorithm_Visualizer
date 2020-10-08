package com.example.algorithm_visualizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Quicksort extends AppCompatActivity {
    Button liveDemo, definition, complexities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quicksort);


        liveDemo = (Button) findViewById(R.id.quicksortDemo);
        definition = (Button) findViewById(R.id.quicksortDefinition);
        complexities = (Button) findViewById(R.id.quicksortComplexities);

        liveDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity();
            }
        });


    }

    public void openNewActivity() {
        Intent intent2 = new Intent(this, LiveDemo_Quicksort.class);
        startActivity(intent2);
    }





}//EOF