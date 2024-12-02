package com.example.weatherladyspring.services;


import com.example.weatherladyspring.models.FavoriteLocations;
import com.example.weatherladyspring.models.Location;
import com.example.weatherladyspring.repositories.FavoriteLocationsRepository;
import com.example.weatherladyspring.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService{

    private final LocationRepository locationRepository;
    private final FavoriteLocationsRepository favoriteLocationsRepository;
    private String search;

    @Autowired //
    public LocationServiceImpl(LocationRepository locationRepository, FavoriteLocationsRepository favoriteLocationsRepository) {
        this.locationRepository = locationRepository;
        this.favoriteLocationsRepository = favoriteLocationsRepository;
    }

    private List<Location> apiLocations(){
        List<Location> apiLocations = locationRepository.getLocationsFromApi(this.search);
        apiLocations.forEach(apiL -> {
            if(locationRepository.findAll().isEmpty()){
                locationRepository.save(apiL);
            }else if(locationRepository.findAll()
                        .stream()
                        .filter(dbl -> Objects.equals(dbl.getLatitude(), apiL.getLatitude()) && Objects.equals(dbl.getLongitude(),apiL.getLongitude())).toList().isEmpty()){
                locationRepository.save(apiL);
            }
        });return apiLocations;
    }

    @Override
    public List<Location> getLocations(String search) {
        this.search = search;
        List<Location> filteredLocations = new ArrayList<>();
        apiLocations().forEach(apiL -> {
            locationRepository.findAll().forEach(dbL -> {
                if(Objects.equals(dbL.getLatitude(), apiL.getLatitude()) && Objects.equals(dbL.getLongitude(), apiL.getLongitude())){
                    filteredLocations.add(dbL);
                }
            });
        });
        return filteredLocations;
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
        if(!isExistFavoriteLocation(id)) {
            favoriteLocationsRepository.save(favoriteLocation);
        }
    }

    private boolean isExistFavoriteLocation(Long id){
        if(!favoriteLocationsRepository.findAll().stream().filter(fl -> fl.getLocationId() == id).toList().isEmpty()){
            return true;
        }return false;
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
