package com.company.maxicon.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.maxicon.dto.CompanyDTO;
import com.company.maxicon.services.CompanyService;

@RestController
@RequestMapping(value = "/companies")
public class CompanyResource {

	@Autowired
	private CompanyService service;
	
	@GetMapping
	public ResponseEntity<List<CompanyDTO>> findAll() {
		List<CompanyDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
