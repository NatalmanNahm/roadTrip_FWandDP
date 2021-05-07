package com.example.roadtrip_fwanddp.Utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.roadtrip_fwanddp.Model.State;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ReadJson {

    private static final String TAG = ReadJson.class.getSimpleName();

    public static String loadJSONFromAsset(Context context, String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private static String isEmptyStringJson (String json){
        if (TextUtils.isEmpty(json)){
            return null;
        }
        return null;
    }

    public static ArrayList<State> extractState(String json){
        isEmptyStringJson(json);

        ArrayList<State> statesList = new ArrayList<>();

        try{

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i< jsonArray.length(); i++){
                JSONObject statesObject = jsonArray.getJSONObject(i);
                String state = statesObject.getString("name");
                String abr = statesObject.getString("abbreviation");
                statesList.add(new State(state, abr));
            }

        }catch (JSONException e) {
            //If there is a problem parsing the Json object print this message
            Log.e(TAG, "Error parsing the Json object");
        }

        return statesList;
    }

    public static ArrayList<String> extractCities(String json, String state){
        isEmptyStringJson(json);

        ArrayList<String> citiesList = new ArrayList<>();

        try{
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i<jsonArray.length(); i++){
                JSONObject s = jsonArray.getJSONObject(i);
                String st = s.getString("state");

                if (st.equals(state)){
                    JSONObject cityObject = jsonArray.getJSONObject(i);
                    String city = cityObject.getString("city");
                    Log.i("CITY", city);
                    citiesList.add(city);
                }
            }
        }catch (JSONException e) {
            //If there is a problem parsing the Json object print this message
            Log.e(TAG, "Error parsing the Json object");
        }

        return citiesList;
    }

    public static String getStateAbr(String json, String state){
        isEmptyStringJson(json);

        String stateAbr = "";

        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i<jsonArray.length(); i++){
                JSONObject s = jsonArray.getJSONObject(i);
                String st = s.getString("name");

                if (st.equals(state)){
                    stateAbr = s.getString("abbreviation");
                }
            }
        }catch (JSONException e) {
            //If there is a problem parsing the Json object print this message
            Log.e(TAG, "Error parsing the Json object");
        }

        return stateAbr;
    }


    public static int getDistance(String json){
        isEmptyStringJson(json);

        int distance = 0;

        try {
            JSONObject root = new JSONObject(json);
            JSONArray rows = root.getJSONArray("rows");

            JSONObject r = rows.getJSONObject(0);
            JSONArray elements = r.getJSONArray("elements");

            JSONObject elementContent = elements.optJSONObject(0);
            JSONObject dist = elementContent.getJSONObject("distance");
            int meter = dist.getInt("value");
            double miles = meter * 0.00062137;
            distance = (int) miles;
            Log.i("DISTAANCEO", String.valueOf(distance));

        }catch (JSONException e) {
            //If there is a problem parsing the Json object print this message
            Log.e(TAG, "Error parsing the Json object");
        }

        return distance;
    }
}
