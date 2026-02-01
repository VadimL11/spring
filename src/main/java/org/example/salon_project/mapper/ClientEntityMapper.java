package org.example.salon_project.mapper;

import org.example.salon_project.domain.Client;
import org.example.salon_project.model.ClientEntity;

public final class ClientEntityMapper {
    private ClientEntityMapper() {}

    public static Client toDomain(ClientEntity e) {
        return new Client(
                e.getId(),
                e.getFirstName(),
                e.getLastName(),
                e.getPhone(),
                e.getEmail(),
                e.getLanguage(),
                e.getCreatedAt()
        );
    }

    public static ClientEntity toEntity(Client d) {
        ClientEntity e = new ClientEntity();
        e.setId(d.getId());
        e.setFirstName(d.getFirstName());
        e.setLastName(d.getLastName());
        e.setPhone(d.getPhone());
        e.setEmail(d.getEmail());
        e.setLanguage(d.getLanguage());
        e.setCreatedAt(d.getCreatedAt());
        return e;
    }

    public static void applyDomainToEntity(Client d, ClientEntity e) {
        e.setFirstName(d.getFirstName());
        e.setLastName(d.getLastName());
        e.setPhone(d.getPhone());
        e.setEmail(d.getEmail());
        e.setLanguage(d.getLanguage());

    }
}