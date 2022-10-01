package com.company.maxicon.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.company.maxicon.entities.Client;
import com.company.maxicon.entities.Company;

public class CompanyDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String cnpj;
	
	private List<ClientDTO> clients = new ArrayList<>();

	public CompanyDTO() {

	}

	public CompanyDTO(Company entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.cnpj = entity.getCnpj();
	}

	public CompanyDTO(Long id, String name, String cnpj) {
		this.id = id;
		this.name = name;
		this.cnpj = cnpj;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public CompanyDTO(Company entity, Set<Client> clients) {
		this(entity);
		clients.forEach(comp -> this.clients.add(new ClientDTO(comp)));
	}

	public List<ClientDTO> getClients() {
		return clients;
	}

	public void setClients(List<ClientDTO> clients) {
		this.clients = clients;
	}
}
