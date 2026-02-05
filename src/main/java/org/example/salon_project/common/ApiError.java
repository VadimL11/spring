package org.example.salon_project.common;

import java.time.OffsetDateTime;
import java.util.List;

public record ApiError(
        String code,
        String message,
        OffsetDateTime timestamp,
        List<FieldError> fieldErrors
) {
    public record FieldError(String field, String message) {}
}