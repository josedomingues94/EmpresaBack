package com.empresa.spring.boot.backend.apirest.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.empresa.spring.boot.backend.apirest.models.documents.Mensaje;
import com.empresa.spring.boot.backend.apirest.models.services.IChatService;



@Controller
public class ChatController {
	
	private String[] colores = {"red", "blue", "yellow", "green", "brown", "purple", "orange", "gray", "magenta"};
	
	@Autowired
	private IChatService chatService;
	
	@Autowired
	private SimpMessagingTemplate webSocket;
	
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public Mensaje recibeMensaje(Mensaje mensaje) {
		mensaje.setFecha(new Date().getTime());
		
		if(mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto("nuevo usuario");
		} else {
			chatService.guardar(mensaje);
		}
		
		return mensaje;
	}
	
	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String estaEscribiendo(String username) {
		return username.concat(" esta escribiendo...");
	}
	
	@MessageMapping("/historial")
	public void historial(String clienteId) {
		webSocket.convertAndSend("/chat/historial/"+clienteId, chatService.obtenerUltimos10Mensajes());
	}
	
}