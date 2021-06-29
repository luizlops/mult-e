package com.luiz.multe.dtos;

import java.io.Serializable;

import javax.persistence.Column;

import com.luiz.multe.models.Pessoa;

public class PessoaDTO implements Serializable{

    public PessoaDTO(){
        super();
    }

    public PessoaDTO(Pessoa p) {
        super();
        this.nome = p.getNome();
        this.dataNascimento = p.getDataNascimento();
    }

    @Column(name="nome")
	private String nome;
	
	@Column(name="data_nascimento")
	private String dataNascimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    
}
