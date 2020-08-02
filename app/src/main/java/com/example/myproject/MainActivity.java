package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button quicksortBtn, mergesortBtn,insertionSortBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        quicksortBtn = (Button) findViewById(R.id.quicksortBtn);
        mergesortBtn = (Button) findViewById(R.id.mergesortBtn);
        insertionSortBtn = (Button) findViewById(R.id.insertionSortBtn);

        mergesortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMergesortActivity();
            }
        });

        quicksortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity();

            }
        });


    }

    public void openNewActivity(){
        Intent intent = new Intent(this, Quicksort.class);
        startActivity(intent);
    }

    public void openMergesortActivity(){
        Intent intent = new Intent(this, Mergesort.class);
        startActivity(intent);
    }


}