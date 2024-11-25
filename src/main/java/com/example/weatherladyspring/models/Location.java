package com.example.weatherladyspring.models;

import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name = "Locations")
public class Location {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;
    private double longitude;
    private String country;
    private String county;
    private String municipality;
    private String city;
    private String suburb;

    public Location() {
    }

    public Location(Long id, double latitude, double longitude, String country, String county, String municipality, String city, String suburb, List<Weather> weathers) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.county = county;
        this.municipality = municipality;
        this.city = city;
        this.suburb = suburb;
        this.weathers = weathers;
    }

    //    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Weather> weathers = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getSuburb() {
        return suburb;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getCounty() {
        return county;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {

        return String.format("%s%s%s%s%s",
                this.suburb == null ? "" : this.suburb +", ",
                this.city == null ? "" : this.city +", ",
                this.municipality == null ? "" : this.municipality +", ",
                this.county == null ? "" : this.county+", ",
                this.country == null ? "" : this.country);
    }

}
