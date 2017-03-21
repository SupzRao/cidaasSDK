package com.cidaassdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Giri on 3/29/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressEntity implements Serializable {

    private String city = "";
    private String postcode = "";
    private String street = "";
    private String housenumber = "";
    private String countrycode = "";
    private String county = "";
    private String suburb = "";
    private String state = "";

    @JsonProperty ("geopoint")
    private Geopoint geopoint;

    public Geopoint getGeopoint() {
        return geopoint;
    }

    public void setGeopoint(Geopoint geopoint) {
        this.geopoint = geopoint;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


 }
