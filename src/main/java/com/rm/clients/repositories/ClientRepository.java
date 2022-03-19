package com.rm.clients.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rm.clients.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}