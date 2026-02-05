package org.example.salon_project.repository;

import org.example.salon_project.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    boolean existsByEmail(String email);
}