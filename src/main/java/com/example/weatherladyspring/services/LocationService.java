package com.example.weatherladyspring.services;


import com.example.weatherladyspring.models.Location;
import com.example.weatherladyspring.models.Weather;

import java.time.LocalDateTime;
import java.util.List;

public interface LocationService {

    List<Location> getLocations(String search);

    List<Location> getLocationsFromDb();

    List<Location> getFavoriteLocationsFromDB();

    Location getLocationById(Long id);

    void saveLocationToFavoriteLocations(Long id);

    void deleteLocationFromFavoriteLocations(Long id);

    void updateFavoriteLocation(Long id);

    boolean isExist(double latitude, double longitude);

    Location getFavoriteLocationFromDbByid(long id);

    List<Weather> favoriteLocationWeathers();

    List<Location> getFavoriteLocations();
}
