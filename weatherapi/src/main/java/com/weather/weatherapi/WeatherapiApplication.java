package com.weather.weatherapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.weather.weatherapi.service.CsvService;

@SpringBootApplication
public class WeatherapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherapiApplication.class, args);
    }

    @Bean
    CommandLineRunner loadData(CsvService csvService) {
        return args -> {
            csvService.loadCsv("C:/data/testset.csv");
        };
    }
}
