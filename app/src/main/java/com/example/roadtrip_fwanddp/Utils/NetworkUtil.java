package com.example.roadtrip_fwanddp.Utils;

import android.net.Uri;
import android.util.Log;

import com.example.roadtrip_fwanddp.BuildConfig;
import com.example.roadtrip_fwanddp.Model.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class NetworkUtil {

    private static final String TAG = NetworkUtil.class.getSimpleName();
    private static final String GOOGLE_MAP_URL = "https://maps.googleapis.com/maps/api/distancematrix/json";
    private static String key = BuildConfig.MAPS_API_KEY;
    private static final String API_KEY = "key";
    private static final String UNITS = "units";
    private static final String units = "imperial";
    private static final String ORIGIN = "origins";
    private static final String DEST = "destinations";
    private static final String ERROR = "Problem making the HTTP request";



    /**
     * Helper method to simplify the need of trying to build the Url
     * @param uri
     */
    public static URL tryBuildUrl (Uri uri){
        URL url = null;

        try {
            url = new URL(uri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;

    }

    /**
     * Build api to get JSON data from the google map api
     * @return a url
     */
    private static URL build_distance_url(Location origin, Location destination){
        String org = origin.getLocation();
        String dest = destination.getLocation();


        Uri uriBuilder = Uri.parse(GOOGLE_MAP_URL).buildUpon()
                .appendQueryParameter(UNITS, units)
                .appendQueryParameter(ORIGIN, org)
                .appendQueryParameter(DEST, dest)
                .appendQueryParameter(API_KEY, key)
                .build();

        URL url = tryBuildUrl(uriBuilder);
        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {

        String jsonResponse = "";

        //if the url is null the return early
        if (url == null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        //Trying to get connection the server and if the status is 200
        //That means the connection was a success and then we can retrieve data needed
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /*milliseconds*/);
            urlConnection.setConnectTimeout(15000 /*milliseconds*/);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(TAG, "MakeHTTPRequest: Problem retrieving data from JSON result ", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream (InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null){
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();

            while (line != null){
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }

    public static int fetchDistance(Location origin, Location destination ){
        URL url = build_distance_url(origin, destination);

        String jsonResponse = null;

        try {
            jsonResponse = getResponseFromHttpUrl(url);
        } catch (IOException e) {
            Log.e(TAG, ERROR, e);
        }

        return ReadJson.getDistance(jsonResponse);

    }




}
