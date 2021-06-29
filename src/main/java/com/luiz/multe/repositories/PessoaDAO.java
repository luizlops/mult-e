package com.luiz.multe.repositories;

import java.util.List;

import com.luiz.multe.models.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaDAO extends JpaRepository<Pessoa, Integer>{
	@Query(value = "SELECT * FROM pessoas WHERE nome = ?1", nativeQuery = true)
    List<Pessoa> findByNome(String nome);

    @Query(value = "SELECT * FROM pessoas WHERE data_nascimento = ?1", nativeQuery = true)
    List<Pessoa> findByData(String data);
}
