package com.example.weatherladyspring.models;

import java.time.LocalDateTime;


public class Weather {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
    private Location location;
    private Long locationId;
    private final LocalDateTime time;
    private final Double latitude;
    private final Double longitude;
    private final Double temperatureInC;
    private final Integer humidity ;
    private final Double windSpeed;

    public Weather(LocalDateTime time, Double latitude, Double longitude, Double temperatureInC, Integer humidity, Double windSpeed) {
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperatureInC = temperatureInC;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public Location getLocation() {
        return location;
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
        return String.format("%slocationId: %s [%s - %s] Temp: %s°C, Hum: %s%%, Wind: %skm/h", this.locationId, this.time, this.latitude, this.longitude, this.temperatureInC, this.humidity, this.windSpeed);
    }
}
