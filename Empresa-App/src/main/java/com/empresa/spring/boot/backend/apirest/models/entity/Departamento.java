package com.empresa.spring.boot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Clase departamento que contiene los datos que hacen referencia a la entidad
 * Departamento de la base de datos
 * 
 * @author jose
 */

@Entity
@Table(name = "departamentos")
public class Departamento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@Column(unique = true)
	private String nombre;
	
	
	@JsonIgnoreProperties(value={"empleado", "hibernateLazyInitializer", "handler"}, allowSetters=true) 
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.REMOVE})
	@JoinColumn(name = "departamento")
	private Empleado empleado;


	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}









	private static final long serialVersionUID = 1L;
}