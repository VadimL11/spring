package org.example.salon_project.common;


import java.time.OffsetDateTime;
import java.util.List;

public record ApiError(
        String code,
        String message,
        List<FieldIssue> details,
        OffsetDateTime timestamp
) {
    public record FieldIssue(String field, String issue) {}
}