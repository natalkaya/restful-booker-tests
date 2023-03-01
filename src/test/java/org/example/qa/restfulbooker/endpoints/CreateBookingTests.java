package org.example.qa.restfulbooker.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.HttpStatus;
import org.example.qa.cleanup.Booking;
import org.example.qa.cleanup.CleanUp;
import org.example.qa.resfulbooker.model.BookingDto;
import org.example.qa.resfulbooker.model.BookingInfoDto;
import org.example.qa.restfulbooker.TestBaseRestfulBooker;
import org.example.qa.restfulbooker.endpoints.dataproviders.CreateBookingNegativeArguments;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.hamcrest.Matchers.equalTo;

public class CreateBookingTests extends TestBaseRestfulBooker {
    @Test
    void shouldCreateBookingWithAllFields() throws JsonProcessingException {
        BookingInfoDto expectedBooking = new BookingInfoDto();
        BookingDto createdBooking = restfulBookerService.create(expectedBooking)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(BookingDto.class);

        restfulBookerService.getBooking(createdBooking.getBookingId())
                .then().assertThat().body(equalTo(objectMapper.writeValueAsString(expectedBooking)));

        CleanUp.add(new Booking(createdBooking.getBookingId()));
    }

    @Test void shouldNotCreateBookingWithoutAuth() {
        //tbd
    }

    @ParameterizedTest
    @ArgumentsSource(CreateBookingNegativeArguments.class)
    void shouldNotCreateBookingWithoutFields(BookingInfoDto bookingDto) {
        restfulBookerService.create(bookingDto).then().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
//                .then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

}

