package com.ratelimiter.apiratelimiter.controller;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final Bucket bucket;

    @PostMapping(value = "/api/v1/example")
    public ResponseEntity<?> rectangle() {
        ConsumptionProbe consumptionProbe = bucket.tryConsumeAndReturnRemaining(1);
        if (consumptionProbe.isConsumed()) {
            System.out.println(consumptionProbe.getRemainingTokens());
            return ResponseEntity.ok(generateUUID());
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    private UUID generateUUID() {
        return UUID.randomUUID();
    }
}
