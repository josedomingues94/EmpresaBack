package com.empresa.spring.boot.backend.apirest.models.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.spring.boot.backend.apirest.models.dao.IEmpleadoDao;
import com.empresa.spring.boot.backend.apirest.models.entity.Departamento;
import com.empresa.spring.boot.backend.apirest.models.entity.Empleado;
import com.empresa.spring.boot.backend.apirest.models.services.IEmpleadoService;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

	@Autowired
	private IEmpleadoDao empleadoDao;
	

	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAll() {
		return (List<Empleado>) empleadoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Empleado> findAll(Pageable pageable) {
		return empleadoDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Empleado> findEmpleadoFiltrado(String nombre, String apellido1, String apellido2, String email,
		 Pageable pageable) {
		return empleadoDao.findEmpleadoFiltrado(nombre, apellido1, apellido2, email, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Empleado findById(Long id) {
		return empleadoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Empleado save(Empleado empleado) {
		return empleadoDao.save(empleado);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		empleadoDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Departamento> findAllDepartamentos() {
		return empleadoDao.findAllDepartamentos();
	}

	@Override
	public Empleado findByDepartamento(Long id) {
		return empleadoDao.findByDepartamento(id);
	}
	
	

}
