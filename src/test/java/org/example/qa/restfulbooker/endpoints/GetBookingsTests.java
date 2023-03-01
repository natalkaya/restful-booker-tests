package org.example.qa.restfulbooker.endpoints;

import org.example.qa.resfulbooker.model.BookingDto;
import org.example.qa.restfulbooker.TestBaseRestfulBooker;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetBookingsTests
        extends TestBaseRestfulBooker {
    @Test
    void getAllBookingIds() {
        assertTrue(restfulBookerService.getAllBookingIds().size() > 0);
    }

    @Test
    void getBookingsByName() {
        BookingDto predefinedBooking = predefinedBookings.stream().findAny().orElseThrow();
        assertTrue(
                restfulBookerService
                        .getBookings(Map.of("firstname", predefinedBooking.getBooking().getFirstname()))
                        .stream().findAny()
                        .map(BookingDto::getBookingId)
                        .map(bookingId -> restfulBookerService.findBooking(bookingId).getFirstname())
                        .stream()
                        .allMatch(actual -> actual.equals(predefinedBooking.getBooking().getFirstname())),
                "Not all found bookings have the requested first name"
        );

    }
}
