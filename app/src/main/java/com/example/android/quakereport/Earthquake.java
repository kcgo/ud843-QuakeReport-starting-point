package com.example.android.quakereport;
/**
 * Created by katia.goncalves on 09/11/2017.
 */

public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;

    public Earthquake(double magnitude, String location, long timeInMilliseconds) {
        mMagnitude =  magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
    }


    public String getmLocation() {
        return mLocation;
    }

    
    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }


    public double getmMagnitude() {
        return mMagnitude;
    }
}
