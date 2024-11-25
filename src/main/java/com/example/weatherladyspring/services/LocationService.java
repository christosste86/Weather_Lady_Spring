package com.example.weatherladyspring.services;

import com.example.weatherladyspring.api.services.LocationApiService;
import com.example.weatherladyspring.models.Location;

import java.util.List;

public class LocationService {
    public static List<Location> getCitiesLocationsFromSearch(String citySearch){
        LocationApiService locationApiService = new LocationApiService();
        return locationApiService.search(citySearch);
    }
}
