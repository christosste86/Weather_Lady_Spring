package com.example.weatherladyspring.repositories;

import com.example.weatherladyspring.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l from Location l WHERE l .city = ")
    List<Location> findLocationsOfPlace();

}
