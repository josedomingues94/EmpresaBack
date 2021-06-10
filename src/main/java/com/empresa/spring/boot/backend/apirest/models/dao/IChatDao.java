package com.empresa.spring.boot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.empresa.spring.boot.backend.apirest.models.documents.Mensaje;



public interface IChatDao extends MongoRepository<Mensaje, String> {
	
	List<Mensaje> findFirst10ByOrderByFechaDesc();
}
