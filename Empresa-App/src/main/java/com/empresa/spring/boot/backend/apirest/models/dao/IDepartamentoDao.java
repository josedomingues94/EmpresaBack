package com.empresa.spring.boot.backend.apirest.models.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.empresa.spring.boot.backend.apirest.models.entity.Departamento;

/**
 * Intefaz Departamento se encarga de realizar las consultas de los empleados
 * a nuestra base de datos
 * 
 * @author jose
 */

public interface IDepartamentoDao extends JpaRepository<Departamento, Long> {

}
