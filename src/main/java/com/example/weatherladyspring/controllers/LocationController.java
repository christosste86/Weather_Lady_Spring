package com.example.weatherladyspring.controllers;

import com.example.weatherladyspring.api.services.LocationApiService;
import com.example.weatherladyspring.services.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class LocationController {

    @GetMapping("/")
    public String getMainPage(Model model){
        Date date = new Date();
        model.addAttribute("welcomeString", date.toString());
        model.addAttribute("locations", LocationService.getCitiesLocationsFromSearch("Thessaloniki"));
        return "index";
    }

    @GetMapping("/add-location")
    public String addLocationForm(){

        return "add-location";
    }
}
