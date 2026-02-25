package com.weather.weatherapi.dto;

import java.time.LocalDateTime;

import com.weather.weatherapi.entity.Weather;

public class WeatherResponse {
	private String condition;
    private Double temperature;
    private Integer humidity;
    private Double pressure;


    public static WeatherResponse fromEntity(Weather w) {
        WeatherResponse r = new WeatherResponse();
        r.condition = w.getCondition();
        r.temperature = w.getTemperature();
        r.humidity = w.getHumidity();
        r.pressure = w.getPressure();
        return r;
    }

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	public Double getPressure() {
		return pressure;
	}

	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}
    
}
