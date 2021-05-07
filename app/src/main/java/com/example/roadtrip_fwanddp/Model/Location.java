package com.example.roadtrip_fwanddp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {
    private String state;
    private String city;

    public Location(String state, String city) {
        this.state = state;
        this.city = city;
    }

    protected Location(Parcel in) {
        state = in.readString();
        city = in.readString();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public String getLocation(){
        final String s = this.city + ", " + this.state;
        return s;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(state);
        dest.writeString(city);
    }
}
