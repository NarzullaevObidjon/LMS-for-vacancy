package com.company.lmsforvacancy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableCaching
@Slf4j
@RequiredArgsConstructor
public class LmsForVacancyApplication {
    private final CacheManager cacheManager;

    public static void main(String[] args) {
        SpringApplication.run(LmsForVacancyApplication.class, args);
    }

    @Scheduled(initialDelay = 5, fixedDelay = 200, timeUnit = TimeUnit.SECONDS)
    public void evict() {
        cacheManager.getCacheNames()
                .forEach(cacheName -> Objects.requireNonNull(cacheManager.getCache(cacheName)).clear());
        log.info("Killing All Entries Of Cache");
    }

}
