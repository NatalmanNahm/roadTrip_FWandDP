package com.example.roadtrip_fwanddp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.example.roadtrip_fwanddp.Model.Location;
import com.example.roadtrip_fwanddp.Model.Node;
import com.example.roadtrip_fwanddp.Utils.NetworkUtil;

import java.util.concurrent.ExecutionException;

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

        Node nodeOrigin = new Node(curLoc.getLocation());
        Node nodeDestination1 = new Node(option1.getLocation());
        Node nodeDestination2 = new Node(option2.getLocation());
        Node nodeDestination3 = new Node(option3.getLocation());

        try {
            distToLocOpt1 = new FetchDistance(curLoc, option1).execute().get();
            Log.i("DISTANCEONE", String.valueOf(distToLocOpt1));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public class FetchDistance extends AsyncTask<String, Void, Integer>{

        private Location origin;
        private Location destination;

        public FetchDistance(Location origin, Location destination) {
            this.origin = origin;
            this.destination = destination;
        }

        @Override
        protected Integer doInBackground(String... strings) {
            int distance = NetworkUtil.fetchDistance(origin, destination);

            return distance;
        }
    }
}