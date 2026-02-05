package org.example.salon_project.dto;


import java.time.OffsetDateTime;

public record ClientDto(
        Long id,
        String firstName,
        String lastName,
        String phone,
        String email,
        String language,
        OffsetDateTime createdAt
) {}