# restful-booker-tests

API tests for [Hotel booking endpoints](https://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-GetBookings)
have been added for the following endpoints:
- CreateBooking
- DeleteBooking
- GetBookingById
- GetBookings
- PartialUpdateBooking (TBD)

Tests check JSON content-type. XML and query params can be added in the future 

Healthcheck is being executed before running the tests  

## Stack

- Java 17
- JUnit5
- Rest-Assured
- Allure Report

## Run all rests

``mvn clean test``

## Generate allure report

``mvn allure:serve``