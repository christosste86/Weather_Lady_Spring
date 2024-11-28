package com.example.weatherladyspring.models;

import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name = "Locations")
public class Location {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double latitude;
    private Double longitude;
    private String addressType;
    private String name;
    private String displayName;
    private String country;
    private String county;
    private String municipality;
    private String city;
    private String suburb;

    public Location() {
    }

    public Location(Integer id, Double latitude, Double longitude, String addressType, String name, String displayName, String country, String county, String municipality, String city, String suburb) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.addressType = addressType;
        this.name = name;
        this.displayName = displayName;
        this.country = country;
        this.county = county;
        this.municipality = municipality;
        this.city = city;
        this.suburb = suburb;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", addressType='" + addressType + '\'' +
                ", name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", country='" + country + '\'' +
                ", county='" + county + '\'' +
                ", municipality='" + municipality + '\'' +
                ", city='" + city + '\'' +
                ", suburb='" + suburb + '\'' +
                '}';
    }
}
