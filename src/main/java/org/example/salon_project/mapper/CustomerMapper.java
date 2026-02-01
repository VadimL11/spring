package org.example.salon_project.mapper;

import org.example.salon_project.dto.CustomerCreateRequest;
import org.example.salon_project.dto.CustomerDto;
import org.example.salon_project.dto.CustomerUpdateRequest;
import org.example.salon_project.model.Customers;

public final class CustomerMapper {

    private CustomerMapper() {}

    public static CustomerDto toDto(Customers c) {
        return new CustomerDto(
                c.getId(),
                c.getFirstName(),
                c.getLastName(),
                c.getPhone(),
                c.getEmail(),
                c.getLanguage(),
                c.getCreatedAt()
        );
    }

    public static Customers fromCreate(CustomerCreateRequest r) {
        Customers c = new Customers();
        c.setFirstName(r.firstName());
        c.setLastName(r.lastName());
        c.setPhone(r.phone());
        c.setEmail(r.email());
        c.setLanguage(r.language());
        return c;
    }

    public static void applyUpdate(Customers c, CustomerUpdateRequest r) {
        if (r.firstName() != null) c.setFirstName(r.firstName());
        if (r.lastName() != null) c.setLastName(r.lastName());
        if (r.phone() != null) c.setPhone(r.phone());
        if (r.email() != null) c.setEmail(r.email());
        if (r.language() != null) c.setLanguage(r.language());
    }
}