package com.empresa.spring.boot.backend.apirest.models.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.spring.boot.backend.apirest.models.dao.IDepartamentoDao;
import com.empresa.spring.boot.backend.apirest.models.entity.Departamento;
import com.empresa.spring.boot.backend.apirest.models.services.IDepartamentoService;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService {

	@Autowired
	private IDepartamentoDao departamentoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Departamento> findAll() {
		return (List<Departamento>) departamentoDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Departamento> findAll(Pageable pageable) {
		return departamentoDao.findAll(pageable);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Departamento findById(Long id) {
		return departamentoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Departamento save(Departamento departamento) {
		return departamentoDao.save(departamento);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		departamentoDao.deleteById(id);
	}

	


}
