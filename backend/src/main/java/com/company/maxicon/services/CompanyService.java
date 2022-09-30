package com.company.maxicon.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.maxicon.dto.CompanyDTO;
import com.company.maxicon.entities.Company;
import com.company.maxicon.repositories.CompanyRepository;
import com.company.maxicon.services.exceptions.DatabaseException;
import com.company.maxicon.services.exceptions.ResourceNotFoundException;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository repository;
	
	@Transactional(readOnly = true)
	public List<CompanyDTO> findAll() {
		List<Company> list = repository.findAll();
		return list.stream().map(x -> new CompanyDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public CompanyDTO findById(Long id) {
		Optional<Company> obj = repository.findById(id);
		Company entity = obj.orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada!"));
		return new CompanyDTO(entity);
	}


	@Transactional
	public CompanyDTO insert(CompanyDTO dto) {
		Company company = repository.findByCnpj(dto.getCnpj());
		if (company != null) {
			throw new ResourceNotFoundException("CNPJ já cadastrado!");
		} else {
			Company entity = new Company();
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new CompanyDTO(entity);
		}
	}

	@Transactional
	public CompanyDTO update(Long id, CompanyDTO dto) {
		try {
			Company entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new CompanyDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	public void delete(Long id) {
		try {
		repository.deleteById(id);
		} 
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Registro não encontrado para exclusão " + id);
		}
		catch (DataIntegrityViolationException d) {
			throw new DatabaseException("Violação de integridade " + id);
		}
	}
	
}
