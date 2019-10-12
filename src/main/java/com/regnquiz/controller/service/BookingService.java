package com.regnquiz.controller.service;

import com.regnquiz.model.Booking;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Service
public class BookingService {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(BookingService.class);

    private final RestTemplate restTemplate;

    public BookingService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<Booking> addAttendance(String attendanceCode, int userId, int bookingId) throws InterruptedException {
        logger.info("User "+userId+" attending class "+bookingId+" with Code"+ attendanceCode);
        String url = String.format("https://api.github.com/users/%s", user);
        Booking results = restTemplate.getForObject(url, User.class);
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(results);
    }
}
