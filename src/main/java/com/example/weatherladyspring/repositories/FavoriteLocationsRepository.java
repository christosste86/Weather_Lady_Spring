package com.example.weatherladyspring.repositories;

import com.example.weatherladyspring.models.FavoriteLocations;
import com.example.weatherladyspring.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteLocationsRepository extends JpaRepository<FavoriteLocations, Long> {

    @Query("select fl from FavoriteLocations fl where fl.id = :id")
    List<FavoriteLocations> findByLocationId(@Param("id") Long id);

    @Query("SELECT l FROM FavoriteLocations fl " +
            "INNER JOIN Location l ON fl.locationId = l.id " +
            "WHERE fl.id = :id")
    List<Location> findFavoriteLocationById(@Param("id") Long id);

}
