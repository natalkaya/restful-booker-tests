package org.example.qa.restfulbooker.endpoints.dataproviders;

import org.example.qa.resfulbooker.model.BookingDates;
import org.example.qa.resfulbooker.model.BookingInfoDto;
import org.example.qa.utils.Generator;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalDate;
import java.util.stream.Stream;

public class CreateBookingNegativeArguments implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                BookingInfoDto.builder().build(),
                BookingInfoDto.builder()
                        .firstname(Generator.randomString(10))
                        .lastname(Generator.randomString(10))
                        .totalprice(7000)
                        .bookingdates(
                                BookingDates.builder()
                                        .checkin(BookingDates.generate(LocalDate.now()))
                                        .checkout(BookingDates.generate(LocalDate.now().plusDays(7)))
                                        .build())
                        .build(),
                BookingInfoDto.builder()
                        .firstname(Generator.randomString(10))
                        .lastname(Generator.randomString(10))
                        .totalprice(7000)
                        .depositpaid(false)
                        .bookingdates(
                                BookingDates.builder()
                                        .checkin(BookingDates.generate(LocalDate.now()))
                                        .build())
                        .build(),
                BookingInfoDto.builder()
                        .firstname(Generator.randomString(10))
                        .lastname(Generator.randomString(10))
                        .build()
        ).map(Arguments::of);
    }
}
