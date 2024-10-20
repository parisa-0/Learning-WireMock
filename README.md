# Timezone Service Integration Testing with WireMock

This project demonstrates how to use WireMock for integration and unit testing of a service that retrieves available timezones.

## Project Overview

The goal of this project is to simulate a service call to an external API that provides timezone data. We use WireMock to mock the API responses for testing purposes.

## Features

- Integration testing with WireMock to simulate external service calls.
- Unit testing to verify service call responses.
- Mocking API responses with status code 200 and expected response body.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven or Gradle for dependency management
- WireMock library

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/parisa-0/timezone-service-testing.git
Navigate to the project directory:

cd timezone-service-testing
Build the project using Maven or Gradle:

mvn clean install
or

gradle build
Running Tests
To run the integration and unit tests, use the following command:

mvn test
or

gradle test
WireMock Configuration
WireMock is configured to simulate the external timezone service. The configuration includes:

Mocking a GET request to /api/timezones endpoint.
Returning a response with status code 200 and a JSON body containing the expected timezone data.
