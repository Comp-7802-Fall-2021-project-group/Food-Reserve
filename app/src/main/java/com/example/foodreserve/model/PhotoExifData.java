package com.example.foodreserve.model;

import java.util.Date;

public class PhotoExifData {

    String caption;
    double latitude;
    double longitude;
    Date lastModified;
    int orientation;

    public PhotoExifData() {
        caption = "";
        latitude = 0;
        longitude = 0;
        this.lastModified = null;
    }

    public PhotoExifData(String cap, double lat, double lon) {
        caption = cap;
        latitude = lat;
        longitude = lon;
    }

    public String getCaption() {
        return caption;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Date getLastModified() { return lastModified; }

    public void setCaption(String cap) {
        caption = cap;
    }

    public void setLatitude(double lat) {
        latitude = lat;
    }

    public void setLongitude(double lon) {
        longitude = lon;
    }

    public void setLastModified(Date lastModified) { this.lastModified = lastModified; }

    public int getOrientation() {return orientation;}

    public void setOrientation(int orientation) {this.orientation = orientation;}


}
