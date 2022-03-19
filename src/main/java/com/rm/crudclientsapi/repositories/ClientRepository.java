package com.rm.crudclientsapi.repositories;

import com.rm.crudclientsapi.entities.Client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}