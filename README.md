# CountryInformation
# 🌍 CountryInformation Service

A Spring Boot REST API that provides structured country information using an external API or fallback dummy data.

## 🚀 Features

- **GET /countries/{code}** endpoint
- Fetches data from [REST Countries API](https://restcountries.com/v3.1/alpha/)
- Assumptions: As this is not working i have created a static map for data.
- Returns:
  - Country Code
  - Country Name
  - Capital
  - Region
  - Currencies
  - Languages
  - Borders
  - Size Category (based on population)
- Fallback to dummy data if external API fails
- Global exception handling
- Unit and integration testing included

---

## 📦 Technologies Used

- Spring Boot
- Spring Web
- Spring Test (JUnit + Mockito)
- RestTemplate
- Java 17+
- Maven

---
### 📦 Build the Project

```bash
git clone https://github.com/bhanupriya123/CountryInformation.git
cd CountryInformation
./gradlew clean build

