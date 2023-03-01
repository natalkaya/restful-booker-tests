package org.example.qa.resfulbooker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    @JsonProperty("bookingid")
    Long bookingId;
    @JsonProperty(required = false)
    BookingInfoDto booking;
}

