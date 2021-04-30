package com.example.roadtrip_fwanddp.Model;

public class State {
    private String name;
    private String nameAbr;

    public State(String name, String nameAbr) {
        this.name = name;
        this.nameAbr = nameAbr;
    }

    public String getName() {
        return name;
    }

    public String getNameAbr() {
        return nameAbr;
    }
}
