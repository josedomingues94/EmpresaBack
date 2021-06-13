package com.empresa.spring.boot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.spring.boot.backend.apirest.models.entity.Usuario;
import com.empresa.spring.boot.backend.apirest.models.services.IUsuarioService;
import com.empresa.spring.boot.backend.apirest.util.StringHash;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "*" })
public class UsuarioRestController {

	@Autowired
	private IUsuarioService usuarioService;

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping("/usuarios")
	public List<Usuario> listaDeUsuarios() {
		return this.usuarioService.getListaDeUsuarios();
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> getUsuario(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();
		Usuario usuario = null;

		try {
			usuario = this.usuarioService.findById(id);
			System.out.println("TODO BIEN GET USUARIO");
		} catch (DataAccessException dae) {
			response.put("mensaje", "Error de acceso a la base de datos");
			response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		if (usuario == null) {
			response.put("mensaje",
					"El usuario con el Id ".concat(id.toString().concat(" no se encuentra en el sistema")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@GetMapping("/usuarios/login")
	@ResponseBody
	public ResponseEntity<?> getUsuarioLogin(@RequestParam(name = "username") String username,
			@RequestParam String password) {

		Map<String, Object> response = new HashMap<>();
		System.out.println(DigestUtils.sha256Hex(password));

		password = StringHash.getHashString(password);

		Usuario usuario = null;
		try {
			usuario = this.usuarioService.findByUsernameAndPassword(username, username);
		} catch (DataAccessException dae) {
			response.put("mensaje", "Error de acceso a la base de datos");
			response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		if (usuario == null) {
			response.put("mensaje",
					"El usuario de Empresa-App ".concat(username.concat(" no se encuentra en el sistema")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "Acceso concedido");
		response.put("usuario", usuario);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping("/usuario")
	public Usuario getUsuario2(@RequestBody String requestidjson) {

		JSONObject obj = new JSONObject(requestidjson);
		Long id = obj.getLong("id");
		return this.usuarioService.findById(id);
	}

	@Secured({ "ROLE_ADMIN" })
	@PostMapping("/usuario")
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result) {

		Map<String, Object> response = new HashMap<>();
		Usuario nuevousuario = null;

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(error -> {
				return "El campo '" + error.getField() + "' " + error.getDefaultMessage();
			}).collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			nuevousuario = this.usuarioService.save(usuario);
		} catch (DataAccessException dae) {
			response.put("mensaje", "Error al crear el usuario");
			response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Usuario creado en base de datos");
		response.put("usuario", nuevousuario);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured({ "ROLE_ADMIN" })
	@PutMapping("/usuario/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();
		System.out.println("EDITAR USUARIO");
		Usuario usuariosujeto = this.usuarioService.findById(id);
		Usuario usuarioactualizado = null;

		if (result.hasErrors()) {
			System.out.println("ERRORES EN UPDATE USUARIO 1");
			List<String> errors = result.getFieldErrors().stream().map(error -> {
				return "El campo '" + error.getField() + "' " + error.getDefaultMessage();
			}).collect(Collectors.toList());

			response.put("error", errors);
			System.out.println(errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (usuariosujeto == null) {
			System.out.println("ERRORES EN UPDATE USUARIO 2");
			response.put("mensaje",
					"El usuario con el Id ".concat(id.toString().concat(" no se encuentra en el sistema")));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			usuariosujeto.setEmail(usuario.getEmail());
			usuariosujeto.setPassword(usuario.getPassword());
			usuariosujeto.setUsername(usuario.getUsername());
			usuariosujeto.setNombre(usuario.getNombre());
			usuariosujeto.setApellido(usuario.getApellido());

			usuarioactualizado = this.usuarioService.save(usuariosujeto);

		} catch (DataAccessException dae) {
			System.out.println("ERRORES EN UPDATE USUARIO 3");
			response.put("mensaje", "Error al actualizar el usuario");
			response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Usuario editado y actualizado en la base de datos!");
		response.put("usuario", usuarioactualizado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured({ "ROLE_ADMIN" })
	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			this.usuarioService.delete(id);
		} catch (DataAccessException dae) {
			response.put("mensaje", "Error al eliminar el usuario");
			response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Usuario eliminado de la base de datos");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
