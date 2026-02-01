package org.example.salon_project.controller;

import jakarta.validation.Valid;
import org.example.salon_project.dto.ClientCreateRequest;
import org.example.salon_project.dto.ClientDto;
import org.example.salon_project.dto.ClientUpdateRequest;
import org.example.salon_project.mapper.ClientMapper;
import org.example.salon_project.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClientDto> list(
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(defaultValue = "0") int offset
    ) {
        return service.getAll(limit, offset).stream()
                .map(ClientMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ClientDto getById(@PathVariable Long id) {
        return ClientMapper.toDto(service.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto create(@Valid @RequestBody ClientCreateRequest request) {
        return ClientMapper.toDto(service.create(ClientMapper.fromCreate(request)));
    }

    @PatchMapping("/{id}")
    public ClientDto update(@PathVariable Long id, @Valid @RequestBody ClientUpdateRequest request) {
        return ClientMapper.toDto(service.update(id, ClientMapper.fromUpdate(request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}