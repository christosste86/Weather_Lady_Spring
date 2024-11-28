package com.example.weatherladyspring.api.locationApis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiCombinationTest {
    @Test
    void filteredListTestSize(){
        ApiCombination apiCombination = new ApiCombination("Thessaloniki");
        assertEquals(1,apiCombination.getFilteredLocation().size());
    }

    @Test
    void ListSizeTest(){
        OpenStreetMapApi openStreetMapApi = new OpenStreetMapApi("Thessaloniki");
        assertEquals(2,openStreetMapApi.getLocationObjects().size());
    }

}