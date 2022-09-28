package com.company.maxicon.dto;

import java.io.Serializable;

import com.company.maxicon.entities.Company;

public class CompanyDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Long cnpj;

	public CompanyDTO() {

	}

	public CompanyDTO(Company entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.cnpj = entity.getCnpj();
	}

	public CompanyDTO(Long id, String name, Long cnpj) {
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

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

}
