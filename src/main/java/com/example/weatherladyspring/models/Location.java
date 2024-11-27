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
    private String osmType;
    private String category;
    private String type;
    private String addressType;
    private String name;
    private String displayName;

    public Location() {
    }

    public Location(Integer id, Double latitude, Double longitude, String osmType, String category, String type, String addressType, String name, String displayName) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.osmType = osmType;
        this.category = category;
        this.type = type;
        this.addressType = addressType;
        this.name = name;
        this.displayName = displayName;
    }

    public Integer getId() {
        return id;
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

    public String getOsmType() {
        return osmType;
    }

    public void setOsmType(String osmType) {
        this.osmType = osmType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", osm_type='" + osmType + '\'' +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", addresstype='" + addressType + '\'' +
                ", name='" + name + '\'' +
                ", display_name='" + displayName + '\'' +
                '}';
    }
}
