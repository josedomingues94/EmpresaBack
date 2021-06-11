package com.empresa.spring.boot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.empresa.spring.boot.backend.apirest.models.documents.Mensaje;

/**
 * Intefaz Chat se encarga de realizar las consultas de los empleados
 * a nuestra base de datos 
 * 
 * @author jose
 */

public interface IChatDao extends MongoRepository<Mensaje, String> {

	List<Mensaje> findFirst10ByOrderByFechaDesc();
}
