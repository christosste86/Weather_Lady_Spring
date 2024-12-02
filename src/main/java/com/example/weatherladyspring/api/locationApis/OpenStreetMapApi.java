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
            Location location = new Location();
            JSONObject item = jsonArray.getJSONObject(i);
            location.setLatitude(item.getDouble("lat"));
            location.setLongitude(item.getDouble("lon"));
            location.setAddressType(item.getString("addresstype"));
            location.setName(item.getString("name"));
            location.setDisplayName(item.getString("display_name"));
            locationlist.add(location);
        }return locationlist;
    }

    private String[] addressTypes(){
        return new String[] {"city", "hamlet", "town", "village"};
    }

    private boolean isInAddressTypesList(Location location){
        for (String addressType : addressTypes()){
            if (location.getAddressType().equalsIgnoreCase(addressType)){
                return true;
            }
        }return false;
    }

    public List<Location> getFilteredLocations(){
        return getLocationObjects().stream().filter(this::isInAddressTypesList).toList();
    }

}
