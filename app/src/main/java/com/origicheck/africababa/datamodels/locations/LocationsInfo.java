package com.origicheck.africababa.datamodels.locations;

/**
 * Created by victor on 10/1/2015.
 */
public strictfp class LocationsInfo {

    private int locationId;
    private String address;
    private float latitude;
    private float longitude;
    private long timeAdded;

    public LocationsInfo(int locationId, String address, float latitude, float longitude, long timeAdded) {
        this.locationId = locationId;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timeAdded = timeAdded;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getAddress() {
        return address;
    }

    public strictfp float getLatitude() {
        return latitude;
    }

    public strictfp float getLongitude() {
        return longitude;
    }

    public long getTimeAdded() {
        return timeAdded;
    }
}
