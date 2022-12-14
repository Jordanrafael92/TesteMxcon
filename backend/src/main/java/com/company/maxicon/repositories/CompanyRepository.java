package com.company.maxicon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.maxicon.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

	Company findByCnpj(String cnpj);
}
