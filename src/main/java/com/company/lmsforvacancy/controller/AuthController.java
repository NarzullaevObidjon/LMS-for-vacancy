package com.company.lmsforvacancy.controller;

import com.company.lmsforvacancy.dto.GenerateTokenDTO;
import com.company.lmsforvacancy.security.UserDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserDetailsService userDetailsService;

    public AuthController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody @Valid GenerateTokenDTO dto) {
        return ResponseEntity.ok(userDetailsService.generateToken(dto));
    }
}
