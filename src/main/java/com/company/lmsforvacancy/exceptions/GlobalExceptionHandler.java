package com.company.lmsforvacancy.exceptions;

import com.company.lmsforvacancy.dto.error.AppErrorDTO;
import com.company.lmsforvacancy.exceptions.ItemNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<AppErrorDTO> handler_404(ItemNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(404)
                .body(AppErrorDTO.builder()
                        .error_code(404)
                        .error_path(request.getRequestURI())
                        .error(e.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrorDTO> handler_400(MethodArgumentNotValidException e, HttpServletRequest request) {
        Map<String, List<String>> error = new HashMap<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            error.compute(field, (s, strings) -> {
                strings = Objects.requireNonNullElse(strings, new ArrayList<>());
                strings.add(message);
                return strings;
            });
        }
        return ResponseEntity
                .status(400)
                .body(AppErrorDTO.builder()
                        .error_code(400)
                        .error_path(request.getRequestURI())
                        .error(error)
                        .build());
    }

}