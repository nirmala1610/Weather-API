package com.weather.weatherapi.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.weather.weatherapi.entity.Weather;
import com.weather.weatherapi.repository.WeatherRepository;

@Service
public class WeatherService {

	private final WeatherRepository repository;
	
	public WeatherService(WeatherRepository repository) {
		this.repository=repository;
	}
	
	public List<Weather> getWeatherByDate(LocalDate date){
		
		LocalDateTime start = date.atStartOfDay();
		LocalDateTime end = date.atTime(23, 59, 59);
		
		return repository.findByDateTimeBetween(start, end);
	}
	
	public List<Weather> getWeatherByMonth(int year,int month){
		LocalDateTime start = LocalDate.of(year, month, 1).atStartOfDay();
		LocalDateTime end = YearMonth.of(year, month).atEndOfMonth().atTime(23,59,59);
		
		return repository.findByDateTimeBetween(start, end);
	}
	
	public Map<String, Double> getTemperatureStats(int year, int month) {

	    List<Double> temps = getWeatherByMonth(year, month)
	            .stream()
	            .map(Weather::getTemperature)
	            .filter(Objects::nonNull)
	            .sorted()   // 🔥 REQUIRED
	            .toList();

	    Map<String, Double> stats = new HashMap<>();

	    if (temps.isEmpty()) {
	        return stats;
	    }

	    double min = temps.get(0);
	    double max = temps.get(temps.size() - 1);

	    double median;
	    int size = temps.size();
	    if (size % 2 == 0) {
	        median = (temps.get(size / 2 - 1) + temps.get(size / 2)) / 2;
	    } else {
	        median = temps.get(size / 2);
	    }

	    stats.put("min", min);
	    stats.put("median", median);
	    stats.put("max", max);

	    return stats;
	}
}
