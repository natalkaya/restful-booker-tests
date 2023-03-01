package org.example.qa.cleanup;

import org.example.qa.resfulbooker.RestfulBookerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CleanUp {
    private final static Logger log = LoggerFactory.getLogger(CleanUp.class);
    static final List<Booking> storage = Collections.synchronizedList(new ArrayList<>());

    public static void add(Booking storable) {
        storage.add(storable);
    }

    public static void autoCleanUp() {
        log.info("Start auto clean up...");
        new RestfulBookerService().deleteAll(storage.stream().map(Booking::getBookingId).collect(Collectors.toList()));
    }
}
