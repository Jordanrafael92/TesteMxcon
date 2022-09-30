package com.company.maxicon.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.company.maxicon.entities.Client;
import com.company.maxicon.entities.Company;

public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String cpf;
	
	private List<CompanyDTO> companies = new ArrayList<>();

	public ClientDTO() {

	}

	public ClientDTO(Long id, String name, String cpf) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
	}

	public ClientDTO(Client entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.cpf = entity.getCpf();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public ClientDTO(Client entity, Set<Company> companies) {
		this(entity);
		companies.forEach(cli -> this.companies.add(new CompanyDTO(cli)));
	}

	public List<CompanyDTO> getCompanies() {
		return companies;
	}

	public void setCompanies(List<CompanyDTO> companies) {
		this.companies = companies;
	}
	
	

}
