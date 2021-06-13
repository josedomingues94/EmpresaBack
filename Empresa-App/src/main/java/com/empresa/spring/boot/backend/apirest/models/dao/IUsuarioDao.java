package com.empresa.spring.boot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.empresa.spring.boot.backend.apirest.models.entity.Usuario;

/**
 * Intefaz Usuario se encarga de realizar las consultas de los empleados
 * a nuestra base de datos 
 * 
 * @author jose
 */

public interface IUsuarioDao extends JpaRepository<Usuario, Long>, CrudRepository<Usuario, Long> {

	public Usuario findByUsername(String username);

	@Query("select u from Usuario u where u.username=?1")
	public Usuario findByUsername2(String username);

	@Query("select u from Usuario u where u.username=?1 and u.password=?2")
	public Usuario findByUsernameAndPassword(String username, String password);

}
