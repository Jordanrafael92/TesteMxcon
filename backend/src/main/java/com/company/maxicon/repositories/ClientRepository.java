package com.company.maxicon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.maxicon.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
