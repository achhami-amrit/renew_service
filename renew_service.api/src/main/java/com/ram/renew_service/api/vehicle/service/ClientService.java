package com.ram.renew_service.api.vehicle.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ram.renew_service.api.vehicle.repo.ClientRepository;
import com.ram.renew_service.entity.vehicle.Client;
import com.ram.renew_service.entity.vehicle.ClientDTO;

@Service
public class ClientService {
    private final ClientRepository repo;

    public ClientService(ClientRepository repo) {
        this.repo = repo;
    }

    private ClientDTO toDTO(Client entity) {
        ClientDTO dto = new ClientDTO();
        dto.setClientId(entity.getClientId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setMobileNo(entity.getMobileNo());
        dto.setEmail(entity.getEmail());
        dto.setGender(entity.getGender());
        dto.setNoOfRenew(entity.getNoOfRenew());
        return dto;
    }

    private Client toEntity(ClientDTO dto) {
        Client entity = new Client();
        entity.setClientId(dto.getClientId());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setMobileNo(dto.getMobileNo());
        entity.setEmail(dto.getEmail());
        entity.setGender(dto.getGender());
        entity.setNoOfRenew(dto.getNoOfRenew());
        return entity;
    }

    public List<ClientDTO> findAll() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<ClientDTO> findById(Integer id) {
        return repo.findById(id).map(this::toDTO);
    }

    public ClientDTO save(ClientDTO dto) {
        Client entity = toEntity(dto);
        return toDTO(repo.save(entity));
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}