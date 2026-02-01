package org.example.salon_project.service;

import org.example.salon_project.domain.Client;
import org.example.salon_project.exception.NotFoundException;
import org.example.salon_project.mapper.ClientEntityMapper;
import org.example.salon_project.model.ClientEntity;
import org.example.salon_project.repository.ClientRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private static final Logger log =
            LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> getAll(int limit, int offset) {
        log.debug("Fetching clients limit={} offset={}", limit, offset);

        int safeLimit = Math.max(1, Math.min(limit, 100));
        int safeOffset = Math.max(0, offset);
        int page = safeOffset / safeLimit;

        return repository.findAll(PageRequest.of(page, safeLimit))
                .map(ClientEntityMapper::toDomain)
                .getContent();
    }

    @Override
    @Transactional(readOnly = true)
    public Client getById(Long id) {
        log.info("Fetching client id={}", id);

        ClientEntity e = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found: " + id));

        return ClientEntityMapper.toDomain(e);
    }

    @Override
    public Client create(Client client) {
        log.info("Creating client with email={}", client.getEmail());

        if (client.getEmail() != null && repository.existsByEmail(client.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + client.getEmail());
        }

        if (client.getCreatedAt() == null) {
            client.setCreatedAt(OffsetDateTime.now());
        }

        ClientEntity saved =
                repository.save(ClientEntityMapper.toEntity(client));

        log.info("Client created with id={}", saved.getId());

        return ClientEntityMapper.toDomain(saved);
    }

    @Override
    public Client update(Long id, Client client) {
        log.info("Updating client id={}", id);

        ClientEntity e = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found: " + id));

        if (client.getEmail() != null
                && !client.getEmail().equals(e.getEmail())
                && repository.existsByEmail(client.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + client.getEmail());
        }

        ClientEntityMapper.applyDomainToEntity(client, e);
        ClientEntity saved = repository.save(e);

        log.info("Client updated id={}", saved.getId());

        return ClientEntityMapper.toDomain(saved);
    }

    @Override
    public void delete(Long id) {
        log.warn("Deleting client id={}", id);

        if (!repository.existsById(id)) {
            throw new NotFoundException("Client not found: " + id);
        }

        repository.deleteById(id);
    }
}