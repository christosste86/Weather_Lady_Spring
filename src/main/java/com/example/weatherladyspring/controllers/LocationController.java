package com.example.weatherladyspring.controllers;

import com.example.weatherladyspring.api.weatherApis.OpenMeteoApi;
import com.example.weatherladyspring.models.Location;

import com.example.weatherladyspring.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Objects;

@Controller
public class LocationController {
    private String search;
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/")
    public String getMainPage(Model model, @RequestParam(value = "search", required = false) String search){
        this.search = search;
        if(Objects.nonNull(search)){
            model.addAttribute("locations", locationService.getLocations(search));
        }else{
            model.addAttribute("locations", new ArrayList<>());
        }
        model.addAttribute("favoritesLocations", locationService.getFavoriteLocations());
        locationService.getLocationsFromDb().forEach(System.out::println);
        model.addAttribute("favoriteLocationsWeather", locationService.favoriteLocationWeathers());
        locationService.favoriteLocationWeathers().forEach(System.out::println);
        return "index";
    }

    @GetMapping("/add-favorite/{location}")
    public String addToFavorites(@PathVariable("location") Long locationOrder, Model model){
        Location location = locationService.getLocationById(locationOrder);
        model.addAttribute("location", location.getId());
        locationService.saveLocationToFavoriteLocations(location.getId());
        return "redirect:/";
    }


    @GetMapping("/location-weather/{location}")
    public String showWeather(@PathVariable("location") Long locationOrder, Model model){
        Location location = locationService.getLocationById(locationOrder);
        model.addAttribute("location", location);
        OpenMeteoApi openMeteoApi = new OpenMeteoApi(location.getLatitude(), location.getLongitude());
        model.addAttribute("weathers", openMeteoApi.getWeatherLocationPerHour());
        return "location-weather";
    }

    @GetMapping("/delete-from-favorites/{id}")
    public String deleteLocation(@PathVariable("id") Long locationOrder) {
        locationService.deleteLocationFromFavoriteLocations(locationOrder);
        return "redirect:/";
    }

}
