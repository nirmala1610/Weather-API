package com.weather.weatherapi.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weather.weatherapi.entity.Weather;
import com.weather.weatherapi.repository.WeatherRepository;

@Service
public class CsvService {

    private final WeatherRepository repository;

    public CsvService(WeatherRepository repository) {
        this.repository = repository;
    }

    public void loadCsv(String filePath) {
        List<Weather> records = new ArrayList<>();
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line = br.readLine(); 

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",", -1);

                Weather record = new Weather();

                record.setDateTime(
                        LocalDateTime.parse(data[0], formatter)
                );

                record.setCondition(data[1]);
                record.setTemperature(parseDouble(data[11]));
                record.setHumidity(parseInt(data[6]));
                record.setPressure(parseDouble(data[8]));

                records.add(record);
            }

            repository.saveAll(records);
            System.out.println("CSV loaded successfully. Rows: " + records.size());

        } catch (Exception e) {
            System.err.println("CSV loading failed");
            e.printStackTrace();
        }
    }

    private Double parseDouble(String value) {
        if (value == null) return null;

        value = value.trim();

        if (value.isEmpty()
                || value.equalsIgnoreCase("N/A")
                || value.equals("-9999")) {
            return null;
        }

        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null; // last line of defense
        }
    }

    private Integer parseInt(String value) {
        if (value == null) return null;

        value = value.trim();

        if (value.isEmpty()
                || value.equalsIgnoreCase("N/A")
                || value.equals("-9999")) {
            return null;
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}