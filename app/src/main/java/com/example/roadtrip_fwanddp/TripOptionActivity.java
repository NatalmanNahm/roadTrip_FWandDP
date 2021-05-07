package com.example.roadtrip_fwanddp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.example.roadtrip_fwanddp.Model.Location;
import com.example.roadtrip_fwanddp.Utils.NetworkUtil;

public class TripOptionActivity extends AppCompatActivity {

    int distToLocOpt1 = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_option);

        Location curLoc = getIntent().getParcelableExtra("currentLoc");
        Location option1 = getIntent().getParcelableExtra("option1");
        Location option2 = getIntent().getParcelableExtra("option2");
        Location option3 = getIntent().getParcelableExtra("option3");
        TextView tripOneTxt = findViewById(R.id.opt1Trip1_txt);
        Runnable thread = () -> {
            distToLocOpt1 = NetworkUtil.fetchDistance(curLoc, option1);
            Log.i("distToLocOpt1", String.valueOf(distToLocOpt1));
            tripOneTxt.setText(String.valueOf(distToLocOpt1));

        };
        new Thread(thread).start();

    }


}