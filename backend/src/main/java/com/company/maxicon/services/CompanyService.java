package com.company.maxicon.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.maxicon.dto.CompanyDTO;
import com.company.maxicon.entities.Company;
import com.company.maxicon.repositories.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository repository;
	
	@Transactional(readOnly = true)
	public List<CompanyDTO> findAll() {
		List<Company> list = repository.findAll();
		return list.stream().map(x -> new CompanyDTO(x)).collect(Collectors.toList());
	}
}
