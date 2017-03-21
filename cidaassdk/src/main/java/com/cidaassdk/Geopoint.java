package com.cidaassdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by suprada on 28/3/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geopoint implements Serializable{
    private double lon;
    private double lat;
    private double alt;
    @JsonIgnore
    private String geoadress;
    private boolean valid;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getAlt() {
        return alt;
    }

    public void setAlt(double alt) {
        this.alt = alt;
    }

    public String getGeoadress() {
        return geoadress;
    }

    public void setGeoadress(String geoadress) {
        this.geoadress = geoadress;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
