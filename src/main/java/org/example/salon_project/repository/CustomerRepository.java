package org.example.salon_project.repository;

import org.example.salon_project.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customers, Long> {
    boolean existsByEmail(String email);
}