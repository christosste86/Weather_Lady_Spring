package com.example.weatherladyspring.controllers;

import com.example.weatherladyspring.services.LocationService;
import com.example.weatherladyspring.services.LocationServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationControllerTest {
    String search = "thessaloniki";
    LocationService locationService;

    @Test
    void getLocationsFromSearch(){

        assertEquals(2, locationService.getLocations(this.search).size());
    }
}