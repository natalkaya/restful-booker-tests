package org.example.qa.restfulbooker.endpoints;

import org.apache.http.HttpStatus;
import org.example.qa.resfulbooker.model.BookingDto;
import org.example.qa.resfulbooker.model.BookingInfoDto;
import org.example.qa.restfulbooker.TestBaseRestfulBooker;
import org.junit.jupiter.api.Test;

public class DeleteBookingTests
        extends TestBaseRestfulBooker {
    @Test
    void shouldDeleteUsingToken() {
        BookingDto createdBooking = restfulBookerService.createOrThrow(new BookingInfoDto());
        restfulBookerService
                .given()
                .basePath("booking")
                .cookie("token=" + restfulBookerService.accessToken())
                .delete(createdBooking.getBookingId().toString())
                .then().assertThat()
                .statusCode(HttpStatus.SC_CREATED);
    }
    @Test void shouldDeleteUsingBasicAuth() {
        BookingDto createdBooking = restfulBookerService.createOrThrow(new BookingInfoDto());
        restfulBookerService
                .delete(createdBooking.getBookingId())
                .then().assertThat()
                .statusCode(HttpStatus.SC_CREATED);
    }
    @Test void shouldReturn404WhenNoFound() {
        //todo: tbd
    }

    @Test void shouldNotDeleteBookingWithoutToken() {
        //todo: tbd
    }
}
