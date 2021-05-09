package com.example.roadtrip_fwanddp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.example.roadtrip_fwanddp.Model.Location;
import com.example.roadtrip_fwanddp.Utils.NetworkUtil;

public class TripOptionActivity extends AppCompatActivity {

    int distToLocOpt1 = 23;
    Location curLoc;
    Location option1;
    Location option2;
    Location option3;
    TextView tripOneTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_option);

        curLoc = getIntent().getParcelableExtra("currentLoc");
        option1 = getIntent().getParcelableExtra("option1");
        option2 = getIntent().getParcelableExtra("option2");
        option3 = getIntent().getParcelableExtra("option3");
        tripOneTxt = findViewById(R.id.opt1Trip1_txt);

        new FetchDistance().execute();

    }
    public class FetchDistance extends AsyncTask<String, Void, Integer>{

        @Override
        protected Integer doInBackground(String... strings) {
            distToLocOpt1 = NetworkUtil.fetchDistance(curLoc, option1);

            return distToLocOpt1;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            tripOneTxt.setText(distToLocOpt1);
        }
    }
}