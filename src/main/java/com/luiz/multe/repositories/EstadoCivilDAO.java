package com.luiz.multe.repositories;

import com.luiz.multe.models.EstadoCivil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoCivilDAO extends JpaRepository<EstadoCivil, Integer>{
	
}
