package org.example.qa.healthcheck;

public class HealthCheck {
    public static void check(HealthCheckTypes healthCheck) {
        healthCheck.ping();
    }
}
