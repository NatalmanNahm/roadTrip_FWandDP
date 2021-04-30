package com.example.roadtrip_fwanddp.Model;

public class Location {
    private String state;
    private String city;

    public Location(String state, String city) {
        this.state = state;
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }
}
