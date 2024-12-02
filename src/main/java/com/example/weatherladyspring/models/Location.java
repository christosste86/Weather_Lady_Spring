package com.example.weatherladyspring.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "latitudes")
    private Double latitude;

    @Column(name = "longitudes")
    private Double longitude;

    @Column(name = "address_types")
    private String addressType;

    @Column(name = "place_name")
    private String name;

    @Column(name = "display_name")
    private String displayName;

    private String country;

    private String county;

    private String municipality;

    private String cityTownVillage;

    private String suburb;

    public Location() {
        this.latitude = null;
        this.longitude = null;
        this.addressType = null;
        this.name = null;
        this.displayName = null;
        this.country = null;
        this.county = null;
        this.municipality = null;
        this.cityTownVillage = null;
        this.suburb = null;
    }

    public Location(Double latitude, Double longitude, String addressType, String name, String displayName, String country, String county, String municipality, String cityTownVillage, String suburb) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.addressType = addressType;
        this.name = name;
        this.displayName = displayName;
        this.country = country;
        this.county = county;
        this.municipality = municipality;
        this.cityTownVillage = cityTownVillage;
        this.suburb = suburb;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getCityTownVillage() {
        return cityTownVillage;
    }

    public void setCityTownVillage(String cityTownVillage) {
        this.cityTownVillage = cityTownVillage;
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
                ", cityTownVillage='" + cityTownVillage + '\'' +
                ", suburb='" + suburb + '\'' +
                '}';
    }
}
