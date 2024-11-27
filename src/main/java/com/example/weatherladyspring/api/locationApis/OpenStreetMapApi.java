package com.example.weatherladyspring.api.locationApis;

import com.example.weatherladyspring.api.ApiConnect;
import com.example.weatherladyspring.models.Location;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OpenStreetMapApi {
    //https://nominatim.openstreetmap.org/search.php?q=epanomi&format=jsonv2
    private ApiConnect geocode;


    public OpenStreetMapApi(String search) {
        String url = String.format("https://nominatim.openstreetmap.org/search.php?q=%s&format=jsonv2",search);
        this.geocode = new ApiConnect(url);
    }

    public List<Location> getLocationObjects(){
        JSONArray jsonArray = new JSONArray(geocode.getJsonString());
        List<Location> locationlist  = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = jsonArray.getJSONObject(i);
            int id = i;
            Double latitude = item.getDouble("lat");
            Double longitude = item.getDouble("lon");
            String osmType = item.getString("osm_type");
            String category = item.getString("category");
            String type = item.getString("type");
            String addressType = item.getString("addresstype");
            String name = item.getString("name");
            String displayName = item.getString("display_name");
            locationlist.add(new Location(id, latitude, longitude, osmType, category, type, addressType, name, displayName));
        }return locationlist;
    }

}
