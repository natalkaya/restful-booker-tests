package org.example.qa.healthcheck;

import org.apache.http.HttpStatus;
import org.example.qa.resfulbooker.RestfulBookerService;
import org.example.qa.test.TestFlow;

public class BookerHealthCheck {
    public static void ping() {
        TestFlow.step("Sending the ping request to Booker Service...");
        new RestfulBookerService().given().basePath("ping").get()
                .then().assertThat().statusCode(HttpStatus.SC_CREATED);
    }
}
