package com.example.weatherladyspring.controllers;

import com.example.weatherladyspring.models.Location;
import com.example.weatherladyspring.services.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Controller
public class LocationController {
    private String search;

    @GetMapping("/")
    public String getMainPage(Model model, @RequestParam(value = "search", required = false) String search){
        this.search = search;
        Date date = new Date();
        model.addAttribute("welcomeString", date.toString());
        if(Objects.nonNull(search)){
            model.addAttribute("locations", LocationService.getLocationsFromSearch(search));
        }else{
            model.addAttribute("locations", new ArrayList<>());
        }

        return "index";
    }

    @GetMapping("/add-favorites-locations")
    public String addLocationForm(){

        return "add-location";
    }

    @GetMapping("/location-weather/{location}")
    public String showWeather(@PathVariable("location") Integer locationOrder, Model model){
        model.addAttribute("location", LocationService.getLocationsFromSearch(this.search).get(locationOrder));

        return "location-weather";
    }

}
