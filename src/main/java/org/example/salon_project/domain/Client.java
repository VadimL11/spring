package org.example.salon_project.domain;



import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
public class Client {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String language;
    private OffsetDateTime createdAt;

    public Client() {}

    public Client(Long id, String firstName, String lastName, String phone, String email, String language, OffsetDateTime createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.language = language;
        this.createdAt = createdAt;
    }

}