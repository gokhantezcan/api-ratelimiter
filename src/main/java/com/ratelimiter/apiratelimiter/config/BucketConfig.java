package com.ratelimiter.apiratelimiter.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class BucketConfig {

    // Bucket Configuration
    @Bean
    public Bucket createBucket() {
        Bandwidth limit = Bandwidth.classic(50, Refill.greedy(50, Duration.ofMinutes(1)));
        Bucket bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
        return bucket;
    }
}
