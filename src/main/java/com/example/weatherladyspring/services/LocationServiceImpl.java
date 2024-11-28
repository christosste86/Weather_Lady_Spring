package com.example.weatherladyspring.services;

import com.example.weatherladyspring.api.locationApis.OpenStreetMapApi;
import com.example.weatherladyspring.models.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationServiceImpl {

    private List<Location> favoritesLocations = new ArrayList<>();
    private List<Location> locationsFromSearch = new ArrayList<>();

    public List<Location> getFavoritesLocations() {
        return favoritesLocations;
    }

    public List<Location> getLocationsFromSearch() {
        return locationsFromSearch;
    }

    public void setLocationsFromSearch(List<Location> locationsFromSearch) {

    }

    public void setFavoritesLocations(List<Location> favoritesLocations) {
        this.favoritesLocations = favoritesLocations;
    }

    public static List<Location> getLocationsFromSearch(String search){
        OpenStreetMapApi openStreetMapApi = new OpenStreetMapApi(search);
        return openStreetMapApi.getLocationObjects();
    }

    public void addYourFavoriteLocationToTheList( Integer id){

    }




}
