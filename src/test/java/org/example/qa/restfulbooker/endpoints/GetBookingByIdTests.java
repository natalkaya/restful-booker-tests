package org.example.qa.restfulbooker.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.HttpStatus;
import org.example.qa.restfulbooker.TestBaseRestfulBooker;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class GetBookingByIdTests
        extends TestBaseRestfulBooker {
    @Test
    void shouldReturnPredefinedBookingById() {
        predefinedBookings.stream().findFirst()
                .map(booking -> {
                    try {
                        return restfulBookerService
                                .getBooking(booking.getBookingId())
                                .then().assertThat()
                                .statusCode(HttpStatus.SC_OK)
                                .assertThat().body(equalTo(objectMapper.writeValueAsString(booking.getBooking())));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }

                }).orElseThrow();
    }

    @Test
    void shouldReturn404WhenNotFound() {
        restfulBookerService
                .getBooking(Long.MAX_VALUE)
                .then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
