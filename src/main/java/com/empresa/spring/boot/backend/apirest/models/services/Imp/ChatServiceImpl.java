package com.empresa.spring.boot.backend.apirest.models.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.spring.boot.backend.apirest.models.dao.IChatDao;
import com.empresa.spring.boot.backend.apirest.models.documents.Mensaje;
import com.empresa.spring.boot.backend.apirest.models.services.IChatService;

@Service
public class ChatServiceImpl implements IChatService {

	@Autowired
	private IChatDao chatDao;

	@Override
	public List<Mensaje> obtenerUltimos10Mensajes() {
		return chatDao.findFirst10ByOrderByFechaDesc();
	}

	@Override
	public Mensaje guardar(Mensaje mensaje) {
		return chatDao.save(mensaje);
	}

}
