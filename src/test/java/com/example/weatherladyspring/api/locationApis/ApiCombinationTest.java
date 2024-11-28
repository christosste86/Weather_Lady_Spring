package com.example.weatherladyspring.api.locationApis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiCombinationTest {
    @Test
    void listTest(){
        ApiCombination apiCombination = new ApiCombination("Thessaloniki");
        apiCombination.getFilteredLocation().forEach(o-> {
            System.out.println(o.getLatitude());
            System.out.println(o.getLongitude());
            System.out.println(o.getCity());
            System.out.println(o.getCountry());
            System.out.println(o.getCounty());
            System.out.println(o.getSuburb());
        });
    }

}