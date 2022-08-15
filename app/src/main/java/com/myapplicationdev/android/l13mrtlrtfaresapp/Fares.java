package com.myapplicationdev.android.l13mrtlrtfaresapp;

public class Fares {
    private String type;
    private String time;
    private String distance;
    private int fare;

    public Fares(String type, String time, String distance, int fare) {
        this.type = type;
        this.time = time;
        this.distance = distance;
        this.fare = fare;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    @Override
    public String toString() {
        return "Type: " + type + "\n" +
                "Time: " + time + "\n" +
                "Distance: " + distance + "\n" +
                "Fare: " + fare + " Cents";
    }
}
