package com.empresa.spring.boot.backend.apirest.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;


import com.empresa.spring.boot.backend.apirest.models.entity.Departamento;


public interface IDepartamentoDao extends JpaRepository<Departamento, Long>{


}
