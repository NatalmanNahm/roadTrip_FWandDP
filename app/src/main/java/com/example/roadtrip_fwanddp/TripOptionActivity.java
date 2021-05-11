package com.example.roadtrip_fwanddp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.example.roadtrip_fwanddp.Model.Graph;
import com.example.roadtrip_fwanddp.Model.Location;
import com.example.roadtrip_fwanddp.Model.Node;
import com.example.roadtrip_fwanddp.Utils.Dk_Algorithm;
import com.example.roadtrip_fwanddp.Utils.NetworkUtil;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class TripOptionActivity extends AppCompatActivity {

    Location curLoc;
    Location option1;
    Location option2;
    Location option3;
    TextView tripOneTxt;
    TextView tripTwoTxt;
    TextView tripThreeTxt;
    TextView currentTxt;
    ArrayList<Node> shortestPath;
    ArrayList<Node> getShortestPath1;
    ArrayList<Node> getShortestPath3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_option);

        curLoc = getIntent().getParcelableExtra("currentLoc");
        option1 = getIntent().getParcelableExtra("option1");
        option2 = getIntent().getParcelableExtra("option2");
        option3 = getIntent().getParcelableExtra("option3");
        tripOneTxt = findViewById(R.id.opt1Trip1_txt);
        tripTwoTxt = findViewById(R.id.opt1Trip2_txt);
        tripThreeTxt = findViewById(R.id.opt1Trip3_txt);
        currentTxt = findViewById(R.id.option1);

        Node nodeOrigin = new Node(curLoc.getLocation());
        Node nodeDestination1 = new Node(option1.getLocation());
        Node nodeDestination2 = new Node(option2.getLocation());
        Node nodeDestination3 = new Node(option3.getLocation());

        try {
            int org2dest1 = new FetchDistance(curLoc, option1).execute().get();
            int org2dest2 = new FetchDistance(curLoc, option2).execute().get();
            int org2dest3 = new FetchDistance(curLoc, option3).execute().get();
            nodeOrigin.addDestination(nodeDestination1, org2dest1);
            nodeOrigin.addDestination(nodeDestination2, org2dest2);
            nodeOrigin.addDestination(nodeDestination3, org2dest3);

            int dest1_2_dest2 = new FetchDistance(option1, option2).execute().get();
            int dest1_2_dest3 = new FetchDistance(option1, option3).execute().get();
            nodeDestination1.addDestination(nodeDestination2, dest1_2_dest2);
            nodeDestination1.addDestination(nodeDestination3, dest1_2_dest3);

            int dest2_2_dest1 = new FetchDistance(option2, option1).execute().get();
            int dest2_2_dest3 = new FetchDistance(option2, option3).execute().get();
            nodeDestination2.addDestination(nodeDestination1, dest2_2_dest1);
            nodeDestination2.addDestination(nodeDestination3,dest2_2_dest3);

            int dest3_2_dest1 = new FetchDistance(option3, option1).execute().get();
            int dest3_2_dest2 = new FetchDistance(option3, option2).execute().get();
            nodeDestination3.addDestination(nodeDestination1, dest3_2_dest1);
            nodeDestination3.addDestination(nodeDestination2,dest3_2_dest2);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Graph graph = new Graph();
        graph.addNode(nodeOrigin);
        graph.addNode(nodeDestination1);
        graph.addNode(nodeDestination2);
        graph.addNode(nodeDestination3);

        shortestPath = Dk_Algorithm.calculateShortestPathFromSource(nodeOrigin);
        Node current = shortestPath.get(0);
        Node firstStop = shortestPath.get(1);
        Node secondStop = shortestPath.get(2);
        Node thirdStop = shortestPath.get(3);

        String origin = current.getName();
        String stopOne = firstStop.getName();
        String stopTwo = secondStop.getName();
        String stopThird = thirdStop.getName();

        currentTxt.setText("Starting from " + origin + " travel to:");
        tripOneTxt.setText("first Stop: " + stopOne);
        tripTwoTxt.setText("Second Stop: " + stopTwo);
        tripThreeTxt.setText("Third Stop: " + stopThird);



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