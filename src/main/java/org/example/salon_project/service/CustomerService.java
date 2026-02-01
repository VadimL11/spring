package org.example.salon_project.service;


import org.example.salon_project.dto.CustomerCreateRequest;
import org.example.salon_project.dto.CustomerDto;
import org.example.salon_project.dto.CustomerUpdateRequest;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAll(int limit, int offset);
    CustomerDto getById(Long id);
    CustomerDto create(CustomerCreateRequest request);
    CustomerDto update(Long id, CustomerUpdateRequest request);
    void delete(Long id);
}