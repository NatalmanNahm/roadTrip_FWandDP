package com.example.roadtrip_fwanddp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.roadtrip_fwanddp.JsonUtil.ReadJson;

import java.util.ArrayList;

public class AddLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        String jsonState = ReadJson.loadJSONFromAsset(getApplicationContext(), "states.json");
        ArrayList<String> statesList = ReadJson.extractState(jsonState);
        Spinner stateSpinner = findViewById(R.id.stateSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, statesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        String selectedState = stateSpinner.getSelectedItem().toString();
        Log.i("STATESELECTED", selectedState);
        String jsonCity = ReadJson.loadJSONFromAsset(getApplicationContext(), "citiesStates.json");
        ArrayList<String> cityList = ReadJson.extractCities(jsonCity, selectedState);
        Spinner citySpinner = findViewById(R.id.citiesSpinner);
        ArrayAdapter<String> cAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, cityList);
        cAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cAdapter);


    }


}