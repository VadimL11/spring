package org.example.salon_project.common;

import jakarta.servlet.http.HttpServletRequest;
import org.example.salon_project.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException ex, HttpServletRequest req) {
        log.warn("NOT_FOUND {} {} -> {}", req.getMethod(), req.getRequestURI(), ex.getMessage());
        ApiError body = new ApiError(
                "NOT_FOUND",
                ex.getMessage(),
                OffsetDateTime.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        List<ApiError.FieldError> fields = ex.getBindingResult().getFieldErrors().stream()
                .map(this::toApiFieldError)
                .toList();

        log.warn("VALIDATION_ERROR {} {} -> {} field error(s)", req.getMethod(), req.getRequestURI(), fields.size());

        ApiError body = new ApiError(
                "VALIDATION_ERROR",
                "Некоректні дані запиту",
                OffsetDateTime.now(),
                fields
        );
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleBadRequest(IllegalArgumentException ex, HttpServletRequest req) {
        log.warn("BAD_REQUEST {} {} -> {}", req.getMethod(), req.getRequestURI(), ex.getMessage());
        ApiError body = new ApiError(
                "BAD_REQUEST",
                ex.getMessage(),
                OffsetDateTime.now(),
                null
        );
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAny(Exception ex, HttpServletRequest req) {
        log.error("INTERNAL_ERROR {} {}", req.getMethod(), req.getRequestURI(), ex);

        ApiError body = new ApiError(
                "INTERNAL_ERROR",
                "Неочікувана помилка",
                OffsetDateTime.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    private ApiError.FieldError toApiFieldError(FieldError fe) {
        // якщо fe.getDefaultMessage() null — краще хоч щось показати
        String msg = (fe.getDefaultMessage() != null) ? fe.getDefaultMessage() : "Invalid value";
        return new ApiError.FieldError(fe.getField(), msg);
    }
}