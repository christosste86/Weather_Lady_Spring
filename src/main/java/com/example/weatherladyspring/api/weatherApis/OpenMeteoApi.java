package com.example.weatherladyspring.api.weatherApis;

import com.example.weatherladyspring.api.ApiConnect;

import com.example.weatherladyspring.api.locationApis.OpenStreetMapApi;
import com.example.weatherladyspring.models.Location;
import com.example.weatherladyspring.models.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OpenMeteoApi {
    //https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m
    private final ApiConnect openMeteo;
    private final Double latitude;
    private final Double longitude;

    public OpenMeteoApi(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        String url = String.format("https://api.open-meteo.com/v1/forecast?latitude=%s2&longitude=%s&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m", latitude, longitude);
        this.openMeteo = new ApiConnect(url);
    }

    private JSONObject jsonObject(){
        return new JSONObject(this.openMeteo.getJsonString());
    }

    private JSONObject hourlyObject(){
        return Objects.requireNonNull(jsonObject().getJSONObject("hourly"));
    }


    private JSONArray timeArray(){
        return hourlyObject().getJSONArray("time");
    }

    private JSONArray temperatureArray(){
        return hourlyObject().getJSONArray("temperature_2m");
    }

    private JSONArray humidityArray(){
        return hourlyObject().getJSONArray("relative_humidity_2m");
    }

    private JSONArray windArray(){
        return hourlyObject().getJSONArray("wind_speed_10m");
    }


    public List<Weather> getWeatherLocationPerHour(){
        List<Weather> weatherLocationPerHour = new ArrayList<>();
        for (int i = 0; i < timeArray().length(); i++) {
            LocalDateTime time = LocalDateTime.parse(timeArray().getString(i));
            Double temperatureInC = temperatureArray().getDouble(i);
            Integer humidity = humidityArray().getInt(i);
            Double windSpeed = windArray().getDouble(i);
            weatherLocationPerHour.add(new Weather(time, this.latitude, this.longitude, temperatureInC, humidity, windSpeed));
        }return weatherLocationPerHour;
    }

    public static void main(String[] args) {
        OpenStreetMapApi openStreetMapApi = new OpenStreetMapApi("Thessaloniki");
        openStreetMapApi.getLocationObjects().forEach(location -> {
            OpenMeteoApi openMeteoApi = new OpenMeteoApi(location.getLatitude(), location.getLongitude());
            openMeteoApi.getWeatherLocationPerHour().forEach(System.out::println);
        });
    }
}
