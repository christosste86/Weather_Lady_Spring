package com.example.weatherladyspring.services;


import com.example.weatherladyspring.api.locationApis.ApiCombination;
import com.example.weatherladyspring.models.FavoriteLocations;
import com.example.weatherladyspring.models.Location;
import com.example.weatherladyspring.repositories.FavoriteLocationsRepository;
import com.example.weatherladyspring.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LocationServiceImpl implements LocationService{

    private final LocationRepository locationRepository;
    private final FavoriteLocationsRepository favoriteLocationsRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, FavoriteLocationsRepository favoriteLocationsRepository) {
        this.locationRepository = locationRepository;
        this.favoriteLocationsRepository = favoriteLocationsRepository;
    }


    private List<Location> getLocationsFromApi(String search){
        ApiCombination apiCombination = new ApiCombination(search);
        return apiCombination.getFilteredLocation();
    }


    private List<Location> getLocationsFromDB(String search) {
        return locationRepository.findAll().stream().filter(l->l.getCityTownVillage().equalsIgnoreCase(search)).toList();
    }

    @Override
    public List<Location> getLocations(String search){
        if(!getLocationsFromDB(search).isEmpty()){
            return getLocationsFromDB(search);
        }else{
            getLocationsFromApi(search).forEach(location -> {
                locationRepository.save(location);
            });
        }return getLocationsFromDB(search);
    }

    @Override
    public List<Location> getFavoriteLocationsFromDB() {
        List<Location> favoriteLocations = new ArrayList<>();
        favoriteLocationsRepository.findAll().forEach(fl ->{
            locationRepository.findAll().forEach(l -> {
                if(l.getId() == fl.getLocationId()){
                    favoriteLocations.add(l);
                }
            });
        });
        return favoriteLocations;
    }

    @Override
    public Location getLocationById(Long id) {
        return locationRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Location with id (%s) not found.", id))
                );
    }

    @Override
    public void saveLocationToFavoriteLocations(Long id) {
        FavoriteLocations favoriteLocation = new FavoriteLocations(id);
        favoriteLocationsRepository.save(favoriteLocation);
    }

    @Override
    public void deleteLocationFromFavoriteLocations(Long id) {
        Optional<FavoriteLocations> favoriteLocations = favoriteLocationsRepository.findById(id);
        if (favoriteLocations.isPresent()){
            favoriteLocationsRepository.delete(favoriteLocations.get());
        }
    }

    @Override
    public void updateFavoriteLocation(Long id) {
        Optional<FavoriteLocations> favoriteLocation = favoriteLocationsRepository.findById(id);
        if (favoriteLocation.isPresent()){
            FavoriteLocations l = favoriteLocation.get();
        }
    }
}
