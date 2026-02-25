package com.weather.weatherapi.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.weatherapi.dto.WeatherResponse;
import com.weather.weatherapi.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

	private final WeatherService weatherService;
	
	public WeatherController(WeatherService weatherService) {
		this.weatherService=weatherService;
	}
	
	@GetMapping("/date")
	public List<WeatherResponse> getByDate(@RequestParam String date){
		LocalDate localDate=LocalDate.parse(date);
		return weatherService.getWeatherByDate(localDate)
				             .stream().map(WeatherResponse::fromEntity).toList();
	}
	
	@GetMapping("/month")
	public List<WeatherResponse> getByMonth(@RequestParam int year,@RequestParam int month){
		return weatherService.getWeatherByMonth(year, month)
				             .stream().map(WeatherResponse::fromEntity).toList();
	}
	
	@GetMapping("/stats")
	 public Map<String, Double> getTemperatureStats(
	            @RequestParam int year,
	            @RequestParam int month
	    ) {
	        return weatherService.getTemperatureStats(year, month);
	    }
}
