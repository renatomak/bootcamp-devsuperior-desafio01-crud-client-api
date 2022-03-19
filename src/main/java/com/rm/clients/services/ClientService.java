package com.rm.clients.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rm.clients.dto.ClientDto;
import com.rm.clients.entities.Client;
import com.rm.clients.repositories.ClientRepository;
import com.rm.clients.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public Page<ClientDto> findAllPaged(PageRequest pageRequest) {
		Page<Client> clients = clientRepository.findAll(pageRequest);
		return clients.map(ClientDto::new);
	}
	
	@Transactional(readOnly = true)
	public ClientDto findById(Long id) {
		 Optional<Client> optionalClient = clientRepository.findById(id);
		 Client entity = optionalClient.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
	     return new ClientDto(entity);
	}
	
	@Transactional
	public ClientDto create(ClientDto clientDto) {
		Client entity = new Client(clientDto);
	    return new ClientDto(clientRepository.save(entity));
	}

    @Transactional
    public ClientDto update(Long id, ClientDto dto) {
        try {
            Client entity = clientRepository.getOne(id);
            entity.setName(dto.getName());
            entity = clientRepository.save(entity);
            return new ClientDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        }
    }
}
