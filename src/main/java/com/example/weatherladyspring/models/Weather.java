package com.example.weatherladyspring.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "weathers")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final LocalDateTime time;
    private final Boolean isDay;
    private final Double latitude;
    private final Double longitude;
    @Column (name = "temperature_c")
    private final Double temperatureInC;
    @Column (name = "apparent_temperature")
    private final Double apparentTemperature;
    private final Integer humidity;
    @Column (name ="relative_humanity")
    private final Integer relativeHumidity;
    @Column (name ="wind_speed")
    private final Double windSpeed;
    @Column (name = "wind_direction")
    private final Integer windDirection;
    @Column (name = "wind_gusts")
    private final Integer windGusts;
    @Column (name ="cloud_cover")
    private final Integer cloudCover;
    private final Double rain;
    private final Double snowfall;
    private final Double precipitation;
    private final Double pressureMsl;
    @Column (name = "surface_pressure")
    private final Double surfacePressure;

//    @ManyToOne
//    @JoinColumn(name = "location_id")
//    private Location location;
    private Long locationId;


    public Weather(LocalDateTime time, Boolean isDay, Double latitude, Double longitude, Double temperatureInC, Double apparentTemperature, Integer humidity, Integer relativeHumidity, Double windSpeed, Integer windDirection, Integer windGusts, Integer cloudCover, Double rain, Double snowfall, Double precipitation, Double pressureMsl, Double surfacePressure) {

        this.time = time;
        this.isDay = isDay;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperatureInC = temperatureInC;
        this.apparentTemperature = apparentTemperature;
        this.humidity = humidity;
        this.relativeHumidity = relativeHumidity;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.windGusts = windGusts;
        this.cloudCover = cloudCover;
        this.rain = rain;
        this.snowfall = snowfall;
        this.precipitation = precipitation;
        this.pressureMsl = pressureMsl;
        this.surfacePressure = surfacePressure;
    }

    public Weather() {
        this.time = null;
        this.isDay = null;
        this.latitude = null;
        this.longitude = null;
        this.temperatureInC = null;
        this.apparentTemperature = null;
        this.humidity = null;
        this.relativeHumidity = null;
        this.windSpeed = null;
        this.windDirection = null;
        this.windGusts = null;
        this.cloudCover = null;
        this.rain = null;
        this.snowfall = null;
        this.precipitation = null;
        this.pressureMsl = null;
        this.surfacePressure = null;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDay() {
        return isDay;
    }

    public Double getApparentTemperature() {
        return apparentTemperature;
    }

//    public void setLocation(Location location) {
//        this.location = location;
//    }

    public Integer getRelativeHumidity() {
        return relativeHumidity;
    }

    public Integer getWindDirection() {
        return windDirection;
    }

    public Integer getWindGusts() {
        return windGusts;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public Double getPressureMsl() {
        return pressureMsl;
    }

    public Double getSurfacePressure() {
        return surfacePressure;
    }



    public Integer getCloudCover() {
        return cloudCover;
    }

    public Double getRain() {
        return rain;
    }

    public Double getSnowfall() {
        return snowfall;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getTemperatureInC() {
        return temperatureInC;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", time=" + time +
                ", isDay=" + isDay +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", temperatureInC=" + temperatureInC +
                ", apparentTemperature=" + apparentTemperature +
                ", humidity=" + humidity +
                ", relativeHumidity=" + relativeHumidity +
                ", windSpeed=" + windSpeed +
                ", windDirection=" + windDirection +
                ", windGusts=" + windGusts +
                ", cloudCover=" + cloudCover +
                ", rain=" + rain +
                ", snowfall=" + snowfall +
                ", precipitation=" + precipitation +
                ", pressureMsl=" + pressureMsl +
                ", surfacePressure=" + surfacePressure +
                '}';
    }
}
