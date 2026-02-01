package org.example.salon_project.controller;

import jakarta.validation.Valid;
import org.example.salon_project.dto.CustomerCreateRequest;
import org.example.salon_project.dto.CustomerDto;
import org.example.salon_project.dto.CustomerUpdateRequest;
import org.example.salon_project.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<CustomerDto> list(
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(defaultValue = "0") int offset
    ) {
        return service.getAll(limit, offset);
    }

    @GetMapping("/{id}")
    public CustomerDto get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto create(@Valid @RequestBody CustomerCreateRequest request) {
        return service.create(request);
    }

    @PatchMapping("/{id}")
    public CustomerDto update(@PathVariable Long id, @Valid @RequestBody CustomerUpdateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}