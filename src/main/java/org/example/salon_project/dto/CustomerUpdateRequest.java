package org.example.salon_project.dto;

import jakarta.validation.constraints.Email;

public record CustomerUpdateRequest(
        String firstName,
        String lastName,
        String phone,
        @Email String email,
        String language
) {}