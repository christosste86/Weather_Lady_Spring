package com.example.weatherladyspring.repositories;

import com.example.weatherladyspring.models.Location;
import com.example.weatherladyspring.api.locationApis.ApiCombination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    default List<Location> getLocationsFromApi(String search){
        ApiCombination apiCombination = new ApiCombination(search);
        return apiCombination.getFilteredLocation();
    }

    @Query("select l from Location l where l.latitude = :latitude and l.longitude = :longitude")
    List<Location> getLocationsFromDb(@Param("latitude") double latitude, @Param("longitude") double longitude);

    @Query ("select l from Location l where l.id = :id")
    List<Location> getLocationById(@Param("id") Long id);
}
