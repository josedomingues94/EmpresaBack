package com.empresa.spring.boot.backend.apirest.models.services;

import java.util.List;

import com.empresa.spring.boot.backend.apirest.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> getListaDeUsuarios();

	public Usuario findById(Long id);

	public Usuario save(Usuario usuario);

	public void delete(Long id);

	public Usuario findByUsername(String username);

	public Usuario findByUsernameAndPassword(String username, String username2);
}
