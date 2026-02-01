package org.example.salon_project.service;


import org.example.salon_project.dto.CustomerCreateRequest;
import org.example.salon_project.dto.CustomerDto;
import org.example.salon_project.dto.CustomerUpdateRequest;
import org.example.salon_project.exception.NotFoundException;
import org.example.salon_project.mapper.CustomerMapper;
import org.example.salon_project.model.Customers;
import org.example.salon_project.repository.CustomerRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> getAll(int limit, int offset) {
        int safeLimit = Math.max(1, Math.min(limit, 100));
        int safeOffset = Math.max(0, offset);
        int page = safeOffset / safeLimit;

        return repository.findAll(PageRequest.of(page, safeLimit))
                .map(CustomerMapper::toDto)
                .getContent();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getById(Long id) {
        Customers c = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found: " + id));
        return CustomerMapper.toDto(c);
    }

    @Override
    public CustomerDto create(CustomerCreateRequest request) {
        if (request.email() != null && repository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already exists: " + request.email());
        }
        Customers saved = repository.save(CustomerMapper.fromCreate(request));
        return CustomerMapper.toDto(saved);
    }

    @Override
    public CustomerDto update(Long id, CustomerUpdateRequest request) {
        Customers c = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found: " + id));

        if (request.email() != null
                && !request.email().equals(c.getEmail())
                && repository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already exists: " + request.email());
        }

        CustomerMapper.applyUpdate(c, request);
        Customers saved = repository.save(c);
        return CustomerMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Customer not found: " + id);
        }
        repository.deleteById(id);
    }
}
