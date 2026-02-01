package org.example.salon_project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerCreateRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        String phone,
        @Email String email,
        String language
) {}