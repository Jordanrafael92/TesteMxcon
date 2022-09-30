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

import com.company.maxicon.dto.ClientDTO;
import com.company.maxicon.dto.CompanyDTO;
import com.company.maxicon.entities.Client;
import com.company.maxicon.entities.Company;
import com.company.maxicon.repositories.ClientRepository;
import com.company.maxicon.repositories.CompanyRepository;
import com.company.maxicon.services.exceptions.DatabaseException;
import com.company.maxicon.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private CompanyRepository companyRepository;

	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		List<Client> list = repository.findAll();
		return list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));
		return new ClientDTO(entity, entity.getCompanies());
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client existClient = repository.findByCpf(dto.getCpf());
		Client entity = new Client();
		if (existClient != null) {
			throw new ResourceNotFoundException("CPF já cadastrado!");
		} else {
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ClientDTO(entity);
		}
	}

	private void copyDtoToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.getCompanies().clear();

		for (CompanyDTO compDTO : dto.getCompanies()) {
			Company company = companyRepository.getOne(compDTO.getId());
			entity.getCompanies().add(company);
		}

	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new ClientDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Código não encontrado " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Registro não encontrado para exclusão " + id);
		} catch (DataIntegrityViolationException d) {
			throw new DatabaseException("Violação de integridade " + id);
		}
	}

}
