# Temperature Processing Application

Temperature Processing Application is a RESTful application that accepts data in JSON format, processes it, and adds it to a database for future use. This project serves as a demonstration of the capabilities of the Spring framework and as a way to enhance skills in working with RESTful applications.

## Endpoints

***All tests and uses are in [this repository](https://github.com/Salambalam/client-for-temperature-sensor)***
### Sensor Registration

- **POST** `/sensors/registration`
    - Add a new sensor to the system.
    - Request Body Example:
      ```json
      {
          "name": "some_name"
      }
      ```

### Add Measurements

- **POST** `/measurements/add`
    - Add measurements to the system.
    - Request Body Example:
      ```json
      {
          "value": 10.0,
          "raining": true,
          "sensor": {
              "name": "some_name"
          }
      }
      ```

### Retrieve Measurements

- **GET** `/measurements`
    - Retrieve a list of measurements.
    - Response Example:
      ```json
      {
          "measurements": [
              {
                  "value": 23.3,
                  "raining": true,
                  "sensor": {
                      "name": "test_name"
                  }
              },
              {
                  "value": 54.3,
                  "raining": true,
                  "sensor": {
                      "name": "test_name"
                  }
              },
              {
                  "value": 54.3,
                  "raining": true,
                  "sensor": {
                      "name": "test_name"
                  }
              },
              {
                  "value": 54.3,
                  "raining": true,
                  "sensor": {
                      "name": "test_name"
                  }
              }
          ]
      }
      ```
## Dependencies

The Temperature Processing Application relies on the following key dependencies:

- **Spring Boot Starter Data JPA**
- **Spring Boot Starter Web**
- **PostgreSQL Driver**
- **Project Lombok**
- **Spring Boot Starter Thymeleaf**
- **Spring Boot Starter Test**
- **Hibernate Validator**
- **ModelMapper**

These dependencies are defined in the `pom.xml` file and are managed by Maven. You can find more details about each dependency by checking the project's `pom.xml` file.

