package com.example.roadtrip_fwanddp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
            startActivityForResult(intent, 1);
        });

        addOpt1Btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddLocationActivity.class);
            startActivityForResult(intent, 2);
        });

        addOpt2Btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddLocationActivity.class);
            startActivityForResult(intent, 3);
        });

        addOpt3Btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddLocationActivity.class);
            startActivityForResult(intent, 4);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                TextView cur = findViewById(R.id.currentLocTxt);
                setLocation(data, cur, "Current Location:");
            }

        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK){
                TextView optOne = findViewById(R.id.optionOneTxt);
                setLocation(data, optOne, "Option One:");
            }
        } else if (requestCode == 3) {
            if (resultCode == RESULT_OK){
                TextView optTwo = findViewById(R.id.optionTwoTxt);
                setLocation(data, optTwo, "Option Two:");
            }
        } else {
            if (resultCode == RESULT_OK){
                TextView optThree = findViewById(R.id.optionThreeTxt);
                setLocation(data, optThree, "Option Three:");
            }
        }
    }

    public void setLocation(Intent data, TextView view, String option){
        String state = data.getStringExtra("state");
        String city = data.getStringExtra("city");

        view.setText(option + " " + city + ", " + state);
    }
}