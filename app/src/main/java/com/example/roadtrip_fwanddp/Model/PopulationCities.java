package com.example.roadtrip_fwanddp.Model;

public class PopulationCities {

    private String state;
    private String city;
    private String population;

    public PopulationCities(String state, String city, String population) {
        this.state = state;
        this.city = city;
        this.population = population;
    }

    public String getCity() {
        return city;
    }

    public String getPopulation() {
        return population;
    }

    public String getState() {
        return state;
    }
}
