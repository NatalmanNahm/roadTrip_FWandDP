package com.example.roadtrip_fwanddp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addLocFab = findViewById(R.id.add_fab);
        addLocFab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddLocationActivity.class);
            startActivity(intent);

        });

        Button addCurBtn = findViewById(R.id.addCurLocBtn);
        Button addOpt1Btn = findViewById(R.id.addOptOneBtn);
        Button addOpt2Btn = findViewById(R.id.addOptTwoBtn);
        Button addOpt3Btn = findViewById(R.id.addOptThreeBtn);

        addCurBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddLocationActivity.class);
            startActivity(intent);
        });

        addOpt1Btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddLocationActivity.class);
            startActivity(intent);
        });

        addOpt2Btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddLocationActivity.class);
            startActivity(intent);
        });

        addOpt3Btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddLocationActivity.class);
            startActivity(intent);
        });
    }
}