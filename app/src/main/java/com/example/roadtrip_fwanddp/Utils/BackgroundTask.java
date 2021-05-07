package com.example.roadtrip_fwanddp.Utils;

import com.example.roadtrip_fwanddp.Model.Location;

public class BackgroundTask {

    public int doInTheBackgroundDistance(Location origin, Location destination){
        return NetworkUtil.fetchDistance(origin, destination);

    }
}
