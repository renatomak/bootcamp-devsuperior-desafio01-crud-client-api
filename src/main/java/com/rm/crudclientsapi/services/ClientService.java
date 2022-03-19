package com.rm.crudclientsapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rm.crudclientsapi.dto.ClientDto;
import com.rm.crudclientsapi.entities.Client;
import com.rm.crudclientsapi.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public Page<ClientDto> findAllPaged(PageRequest pageRequest) {
		Page<Client> clients = clientRepository.findAll(pageRequest);
		return clients.map(ClientDto::new);
	}
}
