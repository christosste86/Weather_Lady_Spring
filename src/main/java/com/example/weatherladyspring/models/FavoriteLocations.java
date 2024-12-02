package com.example.weatherladyspring.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Favorite_Locations")
public class FavoriteLocations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "location_id")
    private long locationId;

    public FavoriteLocations() {
    }

    public FavoriteLocations(long locationId) {
        this.locationId = locationId;
    }

    public long getId() {
        return id;
    }

    public long getLocationId() {
        return locationId;
    }
}
