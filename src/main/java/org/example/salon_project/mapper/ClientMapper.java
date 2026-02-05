package org.example.salon_project.mapper;

import org.example.salon_project.domain.Client;
import org.example.salon_project.dto.ClientCreateRequest;
import org.example.salon_project.dto.ClientDto;
import org.example.salon_project.dto.ClientUpdateRequest;

import java.time.OffsetDateTime;

public final class ClientMapper {
    private ClientMapper() {}

    public static ClientDto toDto(Client c) {
        return new ClientDto(
                c.getId(),
                c.getFirstName(),
                c.getLastName(),
                c.getPhone(),
                c.getEmail(),
                c.getLanguage(),
                c.getCreatedAt()
        );
    }

    public static Client fromCreate(ClientCreateRequest r) {
        Client c = new Client();
        c.setFirstName(r.firstName());
        c.setLastName(r.lastName());
        c.setPhone(r.phone());
        c.setEmail(r.email());
        c.setLanguage(r.language());
        c.setCreatedAt(OffsetDateTime.now());
        return c;
    }

    public static Client fromUpdate(ClientUpdateRequest r) {
        Client c = new Client();
        c.setFirstName(r.firstName());
        c.setLastName(r.lastName());
        c.setPhone(r.phone());
        c.setEmail(r.email());
        c.setLanguage(r.language());
        return c;
    }
}