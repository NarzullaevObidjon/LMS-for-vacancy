package com.company.lmsforvacancy;

import com.company.lmsforvacancy.domain.AuthUser;
import com.company.lmsforvacancy.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Bean
    ApplicationRunner runner(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            AuthUser authUser = AuthUser.builder()
                    .username("Obid")
                    .role(AuthUser.Role.ADMIN)
                    .password(passwordEncoder.encode("123"))
                    .build();
            authUserRepository.save(authUser);
        };
    }


}
