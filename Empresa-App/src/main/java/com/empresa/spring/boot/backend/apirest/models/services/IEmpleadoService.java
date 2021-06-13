package com.empresa.spring.boot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.empresa.spring.boot.backend.apirest.models.entity.Departamento;
import com.empresa.spring.boot.backend.apirest.models.entity.Empleado;

public interface IEmpleadoService {

	public List<Empleado> findAll();

	public Page<Empleado> findAll(Pageable pageable);

	public Page<Empleado> findEmpleadoFiltrado(String nombre, String apellido1, String apellido2, String email,
		 Pageable pageable);

	public Empleado findById(Long id);

	public Empleado save(Empleado empleado);

	public void delete(Long id);

	public List<Departamento> findAllDepartamentos();
	
	public Empleado findByDepartamento(Long id);


}
