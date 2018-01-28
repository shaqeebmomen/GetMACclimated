package com.deltahacks4.tommyandshaq.getmacclimated;

import com.google.android.gms.maps.model.LatLng;


/**
 * Created by shaqe on 28/01/2018.
 */

public class Event {
    public String name;
    public String date;
    public String timeSlot;
    public String description;
    public String location;
    public String tag;
    public String extendedInfo;
    public String lat;
    public String lng;

    public Event(String date, String timeSlot, String description, String location, String tag, String extendedInfo, String lat, String lng){
        this.name = name;
        this.date = date;
        this.timeSlot = timeSlot;
        this.description = description;
        this.location = location;
        this.tag = tag;
        this.extendedInfo = extendedInfo;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName(){
        return name;
    }

    public String getDate(){
        return date;
    }

    public String getTimeSlot(){
        return timeSlot;
    }

    public String getDescription(){
        return description;
    }

    public String getLocation(){
        return location;
    }

    public String getTag() {
        return tag;
    }

    public String getExtendedInfo() {
        return extendedInfo;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public LatLng getLatLng(){
        return new LatLng(Double.parseDouble(this.getLat()),Double.parseDouble(this.getLng()));
    }
}


