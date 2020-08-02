package com.example.algorithm_visualizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mergesort extends AppCompatActivity {

    Button live_demoBtn, descriptionBtn, complexitiesBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mergesort);
        live_demoBtn = (Button) findViewById(R.id.live_demoBtn);
        descriptionBtn = (Button) findViewById(R.id.descriptionBtn);
        complexitiesBtn = (Button) findViewById(R.id.complexitiesBtn);

        live_demoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity();
            }
        });

    }
    public void openNewActivity(){
        Intent intent = new Intent(this, LiveDemo_Mergesort.class);
        startActivity(intent);
    }
}