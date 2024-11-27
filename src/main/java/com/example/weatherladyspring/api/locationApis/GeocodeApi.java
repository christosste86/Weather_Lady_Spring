package com.example.weatherladyspring.api.locationApis;

import com.example.weatherladyspring.api.ApiConnect;
import com.example.weatherladyspring.models.Coordinates;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class GeocodeApi {
    //for Search = https://geocode.maps.co/search?q=brno&api_key=672603415fb3e308262303soqf25b0d
    //for LatLon = https://geocode.maps.co/reverse?lat=49.195061&lon=16.606836&api_key=672603415fb3e308262303soqf25b0d
    private String apiKey = "672603415fb3e308262303soqf25b0d";
    private ApiConnect geocode;
    private JSONObject locationObject; //Json main Object with data ("place_id", "licence", "osm_type", "lat", "lon", "display_name", "boundingbox")
    private JSONObject addressObject; //Json address Object ("county", "state", "country_code", "postcode" , "city" ...)
    private final List<Coordinates> coordinates = new ArrayList<>();

    public GeocodeApi(String search) {

        try {
            Thread.sleep(1000);
            String url = String.format("https://geocode.maps.co/search?q=%s&api_key=%s", URLEncoder.encode(search, StandardCharsets.UTF_8), this.apiKey);
            this.geocode = new ApiConnect(url);
            coordinates(url);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public GeocodeApi(double latitude, double longitude) {
        try {
            Thread.sleep(1000);
            String url = String.format("https://geocode.maps.co/reverse?lat=%s&lon=%s&api_key=%s", latitude, longitude, this.apiKey);
            this.geocode = new ApiConnect(url);
            this.locationObject = new JSONObject(geocode.getJsonString());
            this.addressObject = this.locationObject.getJSONObject("address");
            this.coordinates.add(new Coordinates(latitude, longitude));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void coordinates(String url) {
        JSONArray jsonArray = new JSONArray(geocode.getJsonString());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = jsonArray.getJSONObject(i);
            double latitude = item.getDouble("lat");
            double longitude = item.getDouble("lon");
            this.coordinates.add(new Coordinates(latitude, longitude));
        }
    }

    public List<Coordinates> getCoordinates() {
        return coordinates;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public JSONObject getLocationObject() {
        return locationObject;
    }

    public JSONObject getAddressObject() {
        return addressObject;
    }
}
