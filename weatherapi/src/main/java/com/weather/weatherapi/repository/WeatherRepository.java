package com.weather.weatherapi.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weather.weatherapi.entity.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Long>{

    List<Weather> findByDateTimeBetween(
            LocalDateTime start,
            LocalDateTime end
    );
}
