package com.empresa.spring.boot.backend.apirest.models.services;

import java.util.List;

import com.empresa.spring.boot.backend.apirest.models.documents.Mensaje;


public interface IChatService {
	
	List<Mensaje> obtenerUltimos10Mensajes();
	
	Mensaje guardar(Mensaje mensaje);
	
}
