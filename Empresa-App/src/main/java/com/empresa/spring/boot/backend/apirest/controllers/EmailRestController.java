package com.empresa.spring.boot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.spring.boot.backend.apirest.models.entity.Email;
import com.empresa.spring.boot.backend.apirest.models.services.IEmailService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "*" })
public class EmailRestController {

	@Autowired
	 private IEmailService emailService;

	    public EmailRestController(IEmailService emailService) {
	        this.emailService = emailService;
	    }

	    @PostMapping("/enviar")
	    public ResponseEntity<?> sendEmail(@RequestBody Email mail, BindingResult result) {
	    	
	    	Map<String, Object> response = new HashMap<>();
	    	
	    	try {
	    		emailService.sendEmail(mail);
	    	}
	    	catch(DataAccessException e) {
	    		response.put("mensaje", "Error al enviar el email");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
	    	response.put("mensaje", "El email ha sido creado con éxito!");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
}
