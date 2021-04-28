package com.example.roadtrip_fwanddp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.roadtrip_fwanddp.JsonUtil.ReadJson;
import com.example.roadtrip_fwanddp.JsonUtil.State;

import java.util.ArrayList;

public class AddLocationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String selectedState;
    String selectedCity;
    Button addLocBtn;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> cAdapter;
    String jsonState;
    Spinner stateSpinner;
    Spinner citySpinner;
    ArrayList<String> cityList;
    String jsonCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        jsonState = ReadJson.loadJSONFromAsset(getApplicationContext(), "states.json");
        ArrayList<State> statesListAll = ReadJson.extractState(jsonState);

        ArrayList<String> statesList = new ArrayList<>();

        for (int i = 0; i < statesListAll.size(); i ++){
            State state = statesListAll.get(i);
            String stateName = state.getName();
            statesList.add(stateName);
        }

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
            String abrState = ReadJson.getStateAbr(jsonState, selectedState);
            Intent intent = new Intent();
            intent.putExtra("state", abrState);
            intent.putExtra("city", selectedCity);
            setResult(RESULT_OK, intent);
            finish();
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