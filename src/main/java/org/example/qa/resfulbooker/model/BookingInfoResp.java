package org.example.qa.resfulbooker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingInfoResp {
    Long bookingid;
    String firstname;
    String lastname;
    Integer totalprice;
    Boolean depositpaid;
    BookingDates bookingdates;
    String additionalneeds;
}

