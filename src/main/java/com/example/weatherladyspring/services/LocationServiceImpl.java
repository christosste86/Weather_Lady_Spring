package com.example.weatherladyspring.services;


import com.example.weatherladyspring.api.weatherApis.OpenMeteoApi;
import com.example.weatherladyspring.models.FavoriteLocations;
import com.example.weatherladyspring.models.Location;
import com.example.weatherladyspring.models.Weather;
import com.example.weatherladyspring.repositories.FavoriteLocationsRepository;
import com.example.weatherladyspring.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService{

    private final LocationRepository locationRepository;
    private final FavoriteLocationsRepository favoriteLocationsRepository;
    private String search;
    private List<Location> favoriteLocations = new ArrayList<>();

    @Autowired //
    public LocationServiceImpl(LocationRepository locationRepository, FavoriteLocationsRepository favoriteLocationsRepository) {
        this.locationRepository = locationRepository;
        this.favoriteLocationsRepository = favoriteLocationsRepository;
        this.favoriteLocations = getFavoriteLocationsFromDB();
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
    public List<Location> getLocationsFromDb() {
        return locationRepository.findAll();
    }


    @Override
    public List<Location> getFavoriteLocationsFromDB() {
        List<Location> favoriteLocations = new ArrayList<>();
        favoriteLocationsRepository.findAll().forEach(fl ->{
//           favoriteLocationsRepository.findFavoriteLocationById(fl.getLocationId());
            locationRepository.getLocationById(fl.getLocationId());
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
        return !favoriteLocationsRepository.findByLocationId(id).isEmpty();
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

    @Override
    public boolean isExist(double latitude, double longitude) {
        return !locationRepository.getLocationsFromDb(latitude,longitude).isEmpty();
    }

    @Override
    public Location getFavoriteLocationFromDbByid(long id) {
        return favoriteLocationsRepository.findFavoriteLocationById(id).getFirst();
    }



    @Override
    public List<Weather> favoriteLocationWeathers(){
        List<Weather> favoriteLocationWeathers = new ArrayList<>();
        getFavoriteLocations().forEach(fl->{
            OpenMeteoApi openMeteoApi = new OpenMeteoApi(fl.getLatitude(),fl.getLongitude());
            Weather actualWeather = openMeteoApi.getWeatherLocationPerHour()
                    .stream()
                    .filter(w->w.getTime().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()
                            && w.getTime().getHour() == LocalDateTime.now().getHour()).toList().getFirst();
            actualWeather.setLocationId(fl.getId());
            favoriteLocationWeathers.add(actualWeather);
        });return  favoriteLocationWeathers;
    }

    @Override
    public List<Location> getFavoriteLocations() {
        List<Location> list = new ArrayList<>();
        favoriteLocationsRepository.findAll().forEach(fl->{
            System.out.printf(fl.getLocationId()+"");
            Location location = locationRepository.getLocationById(fl.getLocationId()).getFirst();
            System.out.printf(location.toString());
            list.add(location);
        });return list;
    }
}
