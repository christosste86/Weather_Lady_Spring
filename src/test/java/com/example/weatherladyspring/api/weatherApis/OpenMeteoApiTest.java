package com.example.weatherladyspring.api.weatherApis;

import com.example.weatherladyspring.api.locationApis.OpenStreetMapApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenMeteoApiTest {
    @Test
    void testLocation(){
        OpenStreetMapApi openStreetMapApi = new OpenStreetMapApi("Thessaloniki");
         assertEquals(40.6403167, openStreetMapApi.getLocationObjects().get(0).getLatitude());
         assertEquals(22.9352716, openStreetMapApi.getLocationObjects().get(0).getLongitude());
    }

    @Test
    void testWeather(){
        OpenMeteoApi openMeteoApi = new OpenMeteoApi(40.6403167, 22.9352716);
        assertEquals("", openMeteoApi.getWeatherLocationPerHour().get(0).toString());
    }
}