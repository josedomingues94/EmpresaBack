package com.empresa.spring.boot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.empresa.spring.boot.backend.apirest.models.entity.Departamento;


public interface IDepartamentoService {

	public List<Departamento> findAll();
	
	public Page<Departamento> findAll(Pageable pageable);
	
	public Departamento findById(Long id);
	
	public Departamento save(Departamento departaemtno);
	
	public void delete(Long id);


}
