package org.example.qa.resfulbooker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.qa.utils.Generator;

@Getter
@Builder
@AllArgsConstructor
public class BookingInfoDto {
    String firstname;
    String lastname;
    Integer totalprice;
    Boolean depositpaid;
    BookingDates bookingdates;
    @JsonProperty(required = false)
    String additionalneeds;

    @Override
    public String toString() {
        return "BookingInfoDto{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingdates=" + bookingdates +
                ", additionalneeds='" + additionalneeds + '\'' +
                '}';
    }

    //todo maybe not convenient
    public BookingInfoDto() {
        this.firstname = Generator.randomString(10);
        this.lastname = Generator.randomString(10);
        this.totalprice = Generator.random.nextInt(100, 1000);
        this.depositpaid = false;
        this.bookingdates = new BookingDates();
        this.additionalneeds = null;
    }
}

