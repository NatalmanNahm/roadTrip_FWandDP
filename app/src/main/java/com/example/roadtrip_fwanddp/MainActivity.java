package com.example.roadtrip_fwanddp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roadtrip_fwanddp.Model.Location;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    Location curLoc;
    Location opt1Loc;
    Location opt2Loc;
    Location opt3Loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addLocFab = findViewById(R.id.add_fab);
        addLocFab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TripOptionActivity.class);
            if (curLoc == null || opt1Loc == null || opt2Loc == null || opt3Loc == null ){
                Toast.makeText(this, "Make sure all destination are selected"
                        , Toast.LENGTH_SHORT).show();
            } else{
                intent.putExtra("currentLoc", curLoc);
                intent.putExtra("option1", opt1Loc);
                intent.putExtra("option2", opt2Loc);
                intent.putExtra("option3", opt3Loc);
                startActivity(intent);
            }
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
                curLoc = getLocation(data);
            }

        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK){
                TextView optOne = findViewById(R.id.optionOneTxt);
                setLocation(data, optOne, "Option One:");
                opt1Loc = getLocation(data);
            }
        } else if (requestCode == 3) {
            if (resultCode == RESULT_OK){
                TextView optTwo = findViewById(R.id.optionTwoTxt);
                setLocation(data, optTwo, "Option Two:");
                opt2Loc = getLocation(data);
            }
        } else {
            if (resultCode == RESULT_OK){
                TextView optThree = findViewById(R.id.optionThreeTxt);
                setLocation(data, optThree, "Option Three:");
                opt3Loc = getLocation(data);
            }
        }
    }

    public void setLocation(Intent data, TextView view, String option){
        String state = data.getStringExtra("state");
        String city = data.getStringExtra("city");

        view.setText(option + " " + city + ", " + state);

    }

    public Location getLocation (Intent data){
        String state = data.getStringExtra("state");
        String city = data.getStringExtra("city");

        return new Location(state, city);
    }
}