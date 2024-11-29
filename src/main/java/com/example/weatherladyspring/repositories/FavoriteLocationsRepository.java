package com.example.weatherladyspring.repositories;

import com.example.weatherladyspring.models.FavoriteLocations;
import com.example.weatherladyspring.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteLocationsRepository extends JpaRepository<FavoriteLocations, Long> {

}
