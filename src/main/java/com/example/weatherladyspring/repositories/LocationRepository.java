package com.example.weatherladyspring.repositories;

import com.example.weatherladyspring.models.Location;
import com.example.weatherladyspring.api.locationApis.ApiCombination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    default List<Location> getLocationsFromApi(String search){
        ApiCombination apiCombination = new ApiCombination(search);
        return apiCombination.getFilteredLocation();
    }


}
