package com.example.roadtrip_fwanddp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.roadtrip_fwanddp.JsonUtil.ReadJson;

import java.util.ArrayList;

public class AddLocationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String selectedState;
    String selectedCity;
    Button addLocBtn;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> cAdapter;
    Spinner stateSpinner;
    Spinner citySpinner;
    ArrayList<String> cityList;
    String jsonCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        String jsonState = ReadJson.loadJSONFromAsset(getApplicationContext(), "states.json");
        ArrayList<String> statesList = ReadJson.extractState(jsonState);
        stateSpinner = findViewById(R.id.stateSpinner);
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, statesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(adapter);
        stateSpinner.setOnItemSelectedListener(this);
        adapter.notifyDataSetChanged();

        selectedState = stateSpinner.getSelectedItem().toString();
        Log.i("STATESELECTED", selectedState);
        jsonCity = ReadJson.loadJSONFromAsset(getApplicationContext(), "citiesStates.json");
        cityList = ReadJson.extractCities(jsonCity, selectedState);
        citySpinner = findViewById(R.id.citiesSpinner);
        cAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, cityList);
        cAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = parent.getItemAtPosition(position).toString();;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        citySpinner.setAdapter(cAdapter);
        selectedCity = citySpinner.getSelectedItem().toString();

        addLocBtn = findViewById(R.id.addLocBtn);
        addLocBtn.setOnClickListener(v -> {
            Log.i("LOCATIONTRIP", selectedCity + selectedState);
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedState = parent.getItemAtPosition(position).toString();
        adapter.notifyDataSetChanged();
        cityList = ReadJson.extractCities(jsonCity, selectedState);
        cAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, cityList);
        cAdapter.notifyDataSetChanged();
        citySpinner.setAdapter(cAdapter);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}