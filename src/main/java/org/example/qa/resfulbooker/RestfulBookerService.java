package org.example.qa.resfulbooker;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.example.qa.config.RestfulBookerConfig;
import org.example.qa.resfulbooker.model.BookingDto;
import org.example.qa.resfulbooker.model.BookingInfoDto;
import org.example.qa.resfulbooker.model.RequestAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class RestfulBookerService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final RequestSpecification baseUrlReqSpec;
    public final RestfulBookerConfig restfulBookerConfig;

    public RestfulBookerService(Config config) {
        this.restfulBookerConfig = new RestfulBookerConfig(config);
        this.baseUrlReqSpec = new RequestSpecBuilder().setBaseUri(restfulBookerConfig.getBaseUrl()).build();
    }
    public RestfulBookerService() {
        this.restfulBookerConfig = new RestfulBookerConfig(ConfigFactory.load());
        this.baseUrlReqSpec = new RequestSpecBuilder().setBaseUri(restfulBookerConfig.getBaseUrl()).build();
    }

    public String accessToken() {
        String json =
                given()
                        .basePath("auth")
                        .contentType(ContentType.JSON)
                        .body(new RequestAuth(restfulBookerConfig))
                        .post()
                        .andReturn()
                        .asString();
        String token = JsonPath.from(json).getString("token");
        log.debug("generated token: " + token);
        return token;
    }
    public RequestSpecification given() {
        return RestAssured.given(baseUrlReqSpec);
    }

    public Response getBooking(Long id) {
        return given().basePath("booking").get(id.toString());
    }

    public Response create(BookingInfoDto bookingInfo) {
        return given().basePath("booking")
                .log().all()
                .auth().basic(restfulBookerConfig.getUserName(), restfulBookerConfig.getPassword())
                .contentType(ContentType.JSON)
                .body(bookingInfo)
                .post();
    }

    public Response delete(Long bookingId) {
        return given().basePath("booking")
                .log().all()
                .contentType(ContentType.JSON)
                .auth().basic(restfulBookerConfig.getUserName(), restfulBookerConfig.getPassword())
                .delete(bookingId.toString()).andReturn();
    }

    public List<BookingDto> getAllBookingIds() {
        return
                given().basePath("booking")
                        .get()
                        .then()
                        .extract()
                        .body().as(new TypeRef<>() {
                        });
    }

    public List<BookingDto> getBookings(Map<String, Object> queryParams) {
        return given().basePath("booking")
                .queryParams(queryParams)
                .get()
                .then().extract().as(new TypeRef<List<BookingDto>>() {});
    }

    public BookingInfoDto findBooking(Long id) {
        return getBooking(id).then().extract().as(BookingInfoDto.class);
    }

    public BookingInfoDto getBookingOrThrow(Long id) {
        return getBooking(id).then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(BookingInfoDto.class);
    }

    public BookingDto createOrThrow(BookingInfoDto bookingInfo) {
        return create(bookingInfo).then().log().all()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(BookingDto.class);
    }

    public Boolean deleteAll(List<Long> bookingIds) {
        log.info("Start deleting bookings {}", StringUtils.join(bookingIds.toArray(), ","));
        return bookingIds.stream().map(this::delete)
                .map(ResponseOptions::statusCode)
                .allMatch(rc -> rc.equals(HttpStatus.SC_CREATED));
    }

}
