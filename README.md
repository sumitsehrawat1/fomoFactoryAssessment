# Coin Tracker Java Application

## Overview

This is a Spring Boot application designed to collect and store cryptocurrency quotes from the CoinMarketCap API. The application schedules tasks to fetch data at regular intervals and stores the results in a MongoDB database.

## Prerequisites

- Java 17 or later
- Maven
- MongoDB

## Setup

1. **Clone the Repository**

   git clone <repository-url>
   cd <repository-directory>

2. **Build the Application**

   Use Maven to build the application:

   mvn clean install

   This command compiles the code and packages it into a JAR file located in the `target` directory.

3. **Run the Application**

   Use Maven to run the application:

   mvn spring-boot:run

   Alternatively, you can run the JAR file directly:

   java -jar target/coin-tracker-app.jar


## CRONS

### `COLLECT QUOTES`

The application collects cryptocurrency quotes and stores them in MongoDB. This endpoint is triggered based on the scheduled job configuration.

## Development and Contribution

Feel free to submit issues or pull requests to improve the application. For major changes, please open an issue first to discuss what you would like to change.