package com.example.roadtrip_fwanddp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.roadtrip_fwanddp.Model.PopulationCities;
import com.example.roadtrip_fwanddp.Model.State;
import com.example.roadtrip_fwanddp.Utils.HashTable_Algorithm;
import com.example.roadtrip_fwanddp.Utils.ReadJson;

import java.util.ArrayList;

public class SearchCitiesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    ArrayList<State> stateArrayList = new ArrayList<>();
    HashTable_Algorithm<String, ArrayList<PopulationCities>> hashtable = new HashTable_Algorithm<>();
    String selectedState;
    ArrayAdapter<String> adapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_cities);

        String jsonState = ReadJson.loadJSONFromAsset(getApplicationContext(), "states.json");
        stateArrayList = ReadJson.extractState(jsonState);

        for (int i = 0; i < stateArrayList.size(); i++) {
            String state = stateArrayList.get(i).getName();

            String jsonCity = ReadJson.loadJSONFromAsset(getApplicationContext(), "population.json");
            ArrayList<PopulationCities> populationCities = ReadJson.extractPopCities(jsonCity, state);

            hashtable.add(state, populationCities);

        }

        ArrayList<String> statesList = new ArrayList<>();

        for (int i = 0; i < stateArrayList.size(); i ++){
            State state = stateArrayList.get(i);
            String stateName = state.getName();
            statesList.add(stateName);
        }

        Spinner stateSpinner = findViewById(R.id.stateDropdown);
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, statesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(adapter);
        stateSpinner.setOnItemSelectedListener(this);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedState = parent.getItemAtPosition(position).toString();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}