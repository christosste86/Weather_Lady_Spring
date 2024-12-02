package com.example.weatherladyspring.services;


import com.example.weatherladyspring.models.Location;

import java.time.LocalDateTime;
import java.util.List;

public interface LocationService {

    List<Location> getLocations(String search);

    List<Location> getFavoriteLocationsFromDB();

    Location getLocationById(Long id);

    void saveLocationToFavoriteLocations(Long id);

    void deleteLocationFromFavoriteLocations(Long id);

    void updateFavoriteLocation(Long id);
}
