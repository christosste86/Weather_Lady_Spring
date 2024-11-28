package com.example.weatherladyspring.controllers;

import com.example.weatherladyspring.api.locationApis.ApiCombination;
import com.example.weatherladyspring.api.locationApis.OpenStreetMapApi;
import com.example.weatherladyspring.api.weatherApis.OpenMeteoApi;
import com.example.weatherladyspring.api.weatherApis.OpenWeatherMap;
import com.example.weatherladyspring.models.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class LocationController {
    private String search;
    private List<Location> locationsFromSearch = new ArrayList<>();
    private List<Location> favoritesLocations = new ArrayList<>();


    @GetMapping("/")
    public String getMainPage(Model model, @RequestParam(value = "search", required = false) String search){
        this.search = search;
        Date date = new Date();
        model.addAttribute("welcomeString", date.toString());
        if(Objects.nonNull(search)){
            ApiCombination apiCombination = new ApiCombination(search);

            this.locationsFromSearch = apiCombination.getFilteredLocation();
            model.addAttribute("locations", this.locationsFromSearch);
        }else{
            model.addAttribute("locations", new ArrayList<>());
        }

        model.addAttribute("favoritesLocations", this.favoritesLocations);

        return "index";
    }

    @GetMapping("/add-favorite/{location}")
    public String addToFavorites(@PathVariable("location") Integer locationOrder, Model model){
        Location favoriteLocation = this.locationsFromSearch.stream().filter(l->l.getId() == locationOrder).findFirst().get();
        model.addAttribute("location", favoriteLocation);
        this.favoritesLocations.add(favoriteLocation);
        return "redirect:/";
    }


    @GetMapping("/location-weather/{location}")
    public String showWeather(@PathVariable("location") Integer locationOrder, Model model){
        Location location = this.locationsFromSearch.get(locationOrder);
        model.addAttribute("location", location);
        OpenMeteoApi openMeteoApi = new OpenMeteoApi(location.getLatitude(), location.getLongitude());
        model.addAttribute("weathers", openMeteoApi.getWeatherLocationPerHour());
        return "location-weather";
    }


}
