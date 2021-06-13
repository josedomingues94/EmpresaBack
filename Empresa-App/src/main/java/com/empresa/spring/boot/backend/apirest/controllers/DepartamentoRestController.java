package com.empresa.spring.boot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.empresa.spring.boot.backend.apirest.models.entity.Departamento;

import com.empresa.spring.boot.backend.apirest.models.services.IDepartamentoService;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "*" })
public class DepartamentoRestController {

	@Autowired
	private IDepartamentoService departamentoService;
	


	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/departamentos")
	public List<Departamento> index() {
		return departamentoService.findAll();
	}
	

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/departamentos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Departamento departamento = null;
		Map<String, Object> response = new HashMap<>();

		try {
			departamento = departamentoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (departamento == null) {
			response.put("mensaje",
					"El departamento ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Departamento>(departamento, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/departamentos")
	public ResponseEntity<?> create(@Valid @RequestBody Departamento departamento, BindingResult result) {

		Departamento departamentoNuevo = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			departamentoNuevo = departamentoService.save(departamento);
		} catch (DataAccessException e) {
			response.put("mensaje", "Este departamento ya existe");
			response.put("error", "Tiene que crear un departamento con un nombre distinto");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El departamento ha sido creado con éxito!");
		response.put("departamento", departamentoNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/departamentos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Departamento departamento, BindingResult result,
			@PathVariable Long id) {

		Departamento departamentoActual = departamentoService.findById(id);
		Departamento departamentoActualizado = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (departamentoActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el departamento ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			departamentoActual.setNombre(departamento.getNombre());
			departamentoActualizado = departamentoService.save(departamentoActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el empleado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El departamento ha sido actualizado con éxito!");
		response.put("departamento", departamentoActualizado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/departamentos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			departamentoService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el departamento de la base de datos");
			response.put("error", "Solo podra dar de baja un departamento cuando no tenga empleados este departamento");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente eliminado con éxito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}