package org.example.salon_project.service;

import org.example.salon_project.domain.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAll(int limit, int offset);
    Client getById(Long id);
    Client create(Client client);
    Client update(Long id, Client client);
    void delete(Long id);
}