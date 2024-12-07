package com.example.weatherladyspring.api.weatherApis;

import com.example.weatherladyspring.api.ApiConnect;

import com.example.weatherladyspring.models.Weather;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OpenMeteoApi {
    //https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current=temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,rain,showers,snowfall,weather_code,cloud_cover,pressure_msl,surface_pressure,wind_speed_10m,wind_direction_10m,wind_gusts_10m&hourly=temperature_2m,precipitation,rain,showers,snowfall,snow_depth
    private final ApiConnect openMeteo;
    private final Double latitude;
    private final Double longitude;

    public OpenMeteoApi(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        String url = String.format("https://api.open-meteo.com/v1/forecast?latitude=%s2&longitude=%s&hourly=temperature_2m,rain,snowfall,cloud_cover,wind_speed_10m,relative_humidity_2m,apparent_temperature,is_day,precipitation,pressure_msl,surface_pressure,wind_direction_10m,wind_gusts_10m", latitude, longitude);
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

    private JSONArray windSpeedArray(){
        return hourlyObject().getJSONArray("wind_speed_10m");
    }

    private JSONArray rainArray(){
        return hourlyObject().getJSONArray("rain");
    }

    private JSONArray cloudCoverArray(){
        return hourlyObject().getJSONArray("cloud_cover");
    }

    private JSONArray snowfallArray(){
        return hourlyObject().getJSONArray("snowfall");
    }

    private JSONArray relativeHumidityArray(){
        return hourlyObject().getJSONArray("relative_humidity_2m");
    }

    private JSONArray apparentTemperatureArray(){
        return hourlyObject().getJSONArray("apparent_temperature");
    }

    private JSONArray isDayArray(){
        return hourlyObject().getJSONArray("is_day");
    }

    private JSONArray precipitationArray(){
        return hourlyObject().getJSONArray("precipitation");
    }

    private JSONArray pressureMslArray(){
        return hourlyObject().getJSONArray("pressure_msl");
    }

    private JSONArray surfacePressureArray(){
        return hourlyObject().getJSONArray("surface_pressure");
    }

    private JSONArray windDirectionArray(){
        return hourlyObject().getJSONArray("wind_direction_10m");
    }

    private JSONArray windGustsArray(){
        return hourlyObject().getJSONArray("wind_gusts_10m");
    }

    public List<Weather> getWeatherLocationPerHour(){
        List<Weather> weatherLocationPerHour = new ArrayList<>();
        for (int i = 0; i < timeArray().length(); i++) {
            LocalDateTime time = LocalDateTime.parse(timeArray().getString(i));
            Boolean isDay = isDayArray().getInt(i) != 0;
            Double temperatureInC = temperatureArray().getDouble(i);
            Double apparentTemperature = apparentTemperatureArray().getDouble(i);
            Integer humidity = humidityArray().getInt(i);
            Integer relativeHumidity= relativeHumidityArray().getInt(i);
            Double windSpeed = windSpeedArray().getDouble(i);
            Integer windDirection = windDirectionArray().getInt(i);
            Integer windGusts = windGustsArray().getInt(i);
            Integer cloudCover = cloudCoverArray().getInt(i);
            Double rain = rainArray().getDouble(i);
            Double snowfall = snowfallArray().getDouble(i);
            Double precipitation = precipitationArray().getDouble(i);
            Double pressureMsl = pressureMslArray().getDouble(i);
            Double surfacePressure = surfacePressureArray().getDouble(i);
            weatherLocationPerHour.add(new Weather(time, isDay, latitude, longitude, temperatureInC, apparentTemperature, humidity, relativeHumidity, windSpeed, windDirection, windGusts, cloudCover, rain, snowfall, precipitation, pressureMsl, surfacePressure));
        }return weatherLocationPerHour;
    }
}
