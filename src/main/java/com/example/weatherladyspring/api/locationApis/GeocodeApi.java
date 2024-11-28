package com.example.weatherladyspring.api.locationApis;

import com.example.weatherladyspring.api.ApiConnect;
import org.json.JSONObject;

public class GeocodeApi {
    //for Search = https://geocode.maps.co/search?q=brno&api_key=672603415fb3e308262303soqf25b0d
    //for LatLon = https://geocode.maps.co/reverse?lat=49.195061&lon=16.606836&api_key=672603415fb3e308262303soqf25b0d
    private String apiKey = "672603415fb3e308262303soqf25b0d";
    private ApiConnect geocode;
    private JSONObject locationObject; //Json main Object with data ("place_id", "licence", "osm_type", "lat", "lon", "display_name", "boundingbox")
    private JSONObject addressObject; //Json address Object ("county", "state", "country_code", "postcode" , "city" ...)


    public GeocodeApi(double latitude, double longitude) {
        try {
            Thread.sleep(1000);
            String url = String.format("https://geocode.maps.co/reverse?lat=%s&lon=%s&api_key=%s", latitude, longitude, this.apiKey);
            this.geocode = new ApiConnect(url);
            this.locationObject = new JSONObject(geocode.getJsonString());
            this.addressObject = this.locationObject.getJSONObject("address");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject getAddressObject() {
        return addressObject;
    }
}
