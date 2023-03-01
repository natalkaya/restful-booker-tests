package org.example.qa.restfulbooker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.qa.cleanup.Booking;
import org.example.qa.cleanup.CleanUp;
import org.example.qa.healthcheck.HealthCheck;
import org.example.qa.healthcheck.HealthCheckTypes;
import org.example.qa.resfulbooker.RestfulBookerService;
import org.example.qa.resfulbooker.model.BookingDto;
import org.example.qa.resfulbooker.model.BookingInfoDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class TestBaseRestfulBooker {
    protected final static Logger log = LoggerFactory.getLogger("TestBaseRestfulBooker");
    protected static RestfulBookerService restfulBookerService = new RestfulBookerService();
    protected static List<BookingDto> predefinedBookings = new ArrayList<BookingDto>();
    public ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void testsSetupBeforeAll() {
        HealthCheck.check(HealthCheckTypes.RestfulBooker);
        log.info("Creating predefined bookings for tests...");
        predefinedBookings.add(
                restfulBookerService.getAllBookingIds().stream().findFirst()
                        .map(b -> BookingDto.builder().bookingId(b.getBookingId())
                                .booking(restfulBookerService.getBookingOrThrow(b.getBookingId()))
                                .build())
                        .orElseGet(() -> {
                            BookingDto bookingDto = restfulBookerService.createOrThrow(new BookingInfoDto());
                            CleanUp.add(new Booking(bookingDto.getBookingId()));
                            return bookingDto;
                        })
        );
    }

    @AfterAll
    static void testsCleanUpAfterAll() {
        CleanUp.autoCleanUp();
    }
}
