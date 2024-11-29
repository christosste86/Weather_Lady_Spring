package com.example.weatherladyspring.api.locationApis;

import com.example.weatherladyspring.models.Location;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApiCombination {
    private final List<Location> filteredLocation = new ArrayList<>();

    public ApiCombination(String search) {
        addInfoFromGeocodeApi(search);
    }

    public List<Location> getFilteredLocation() {
        return filteredLocation;
    }

    private Location location(GeocodeApi geocodeApi){
        Location newlocation = new Location();
        JSONObject adressJsonObject = geocodeApi.getAddressObject();
        Iterator<String> keys = adressJsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if(key.equals("county")){newlocation.setCounty(adressJsonObject.get(key).toString());}
            if(key.equals("municipality")){newlocation.setMunicipality(adressJsonObject.get(key).toString());}
            if(key.equals("suburb")){newlocation.setSuburb(adressJsonObject.get(key).toString());}
            if(key.equals("country")){newlocation.setCountry(adressJsonObject.get(key).toString());}
            if(key.equals("city") || key.equals("town") || key.equals("village")){
                newlocation.setCityTownVillage(adressJsonObject.get(key).toString());
            }
        }return newlocation;
    }

    private void addInfoFromGeocodeApi(String search){
        OpenStreetMapApi openStreetMapApi = new OpenStreetMapApi(search);
        openStreetMapApi.getFilteredLocations().forEach(l->{
            GeocodeApi geocodeApi = new GeocodeApi(l.getLatitude(), l.getLongitude());
            Location geocodeLocation = location(geocodeApi);
            l.setCounty(geocodeLocation.getCounty());
            l.setMunicipality(geocodeLocation.getMunicipality());
            l.setSuburb(geocodeLocation.getSuburb());
            l.setCountry(geocodeLocation.getCountry());
            l.setCityTownVillage(geocodeLocation.getCityTownVillage());
            this.filteredLocation.add(l);
        });
    }
}
