# 🌦️ Weather Data API – Spring Boot

A Spring Boot REST API that processes historical weather data from a CSV file, stores it in an Oracle database, and exposes endpoints to retrieve and analyze weather information with filtering and statistics.

This project was built as part of a **Java Developer assessment**, focusing on:
- Data processing
- Clean layered architecture
- REST API design
- Filtering, sorting, and aggregation logic

---

## 🚀 Features

- Load large CSV weather dataset into Oracle DB
- REST APIs to fetch weather data by **date** and **month**
- Temperature statistics:
  - Minimum temperature
  - Median temperature
  - Maximum temperature
- Handles missing and invalid values (`N/A`, `-9999`)
- Clean separation of layers (Controller, Service, Repository)

---

## 🧱 Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- Oracle Database
- Maven
- RESTful APIs

---

## 📂 Project Structure

    
    src/main/java
    └── com.weather.weatherapi
    ├── controller # REST Controllers
    ├── service # Business logic
    ├── repository # JPA Repositories
    ├── entity # JPA Entities
    ├── dto # Response DTOs
    └── WeatherapiApplication.java


---

## 📄 CSV Data Handling

- CSV file is loaded at application startup using `CommandLineRunner`
- Date format handled: `yyyyMMdd-HH:mm`
- Invalid numeric values like `N/A` or `-9999` are safely ignored
- Only required fields are persisted:
  - Date & Time
  - Weather Condition
  - Temperature
  - Humidity
  - Pressure

---

## 🔌 API Endpoints

### 1️⃣ Get weather by date

GET /api/weather/date?date=1996-11-01

<img width="954" height="672" alt="image" src="https://github.com/user-attachments/assets/6e509f5c-98c4-49de-b5ff-1a28fc0fc372" />


### 2️⃣ Get weather by month

GET /api/weather/month?year=1996&month=11

<img width="959" height="760" alt="image" src="https://github.com/user-attachments/assets/4eb15ce2-9ba4-4c24-9141-bd8dec1f6563" />



### 3️⃣ Get temperature statistics (min, median, max)

GET /api/weather/stats?year=1996&month=11


<img width="953" height="371" alt="image" src="https://github.com/user-attachments/assets/ac478627-0bb9-4eb7-a28c-27716bcb862d" />

---

## ⚙️ Configuration

### application.properties (example)

    spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
    spring.datasource.username=YOUR_USERNAME
    spring.datasource.password=YOUR_PASSWORD
    
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true

## ▶️ How to Run

Clone the repository

Update application.properties with Oracle DB credentials

Update CSV file path if needed:

csvService.loadCsv("C:/data/testset.csv");

Run the application

Access APIs via browser or Postman

