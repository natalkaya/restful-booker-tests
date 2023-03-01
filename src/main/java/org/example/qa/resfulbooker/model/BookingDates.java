package org.example.qa.resfulbooker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class BookingDates {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date checkin;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date checkout;

    public static Date generate(LocalDate date, ZoneId zoneId) {
        return Date.from(date.atStartOfDay(zoneId).toInstant());
    }

    public static Date generate(LocalDate date) {
        return generate(date, ZoneId.systemDefault());
    }

    public BookingDates() {
        this.checkin = generate(LocalDate.now());
        this.checkout = generate(LocalDate.now().plusDays(3));
    }
}
