package com.example.weatherladyspring.services;

import com.example.weatherladyspring.api.locationApis.OpenStreetMapApi;
import com.example.weatherladyspring.api.weatherApis.OpenMeteoApi;
import com.example.weatherladyspring.models.Location;
import com.example.weatherladyspring.models.Weather;

import java.util.List;

public class LocationService {
    public static List<Location> getLocationsFromSearch(String search){
        OpenStreetMapApi openStreetMapApi = new OpenStreetMapApi(search);
        return openStreetMapApi.getLocationObjects();
    }


}
