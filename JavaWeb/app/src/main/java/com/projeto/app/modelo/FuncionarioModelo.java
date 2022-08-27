package com.projeto.app.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "funcionarios")
@Entity
public class FuncionarioModelo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_funcionario") 
    private int id_funcionario;
	
	@Column(name="nome_funcionario")
	private String nome_funcionario;
	
	@Column(name = "idade")
	private int idade;
	
	@Column(name = "situacao")
	private char situacao;

	public int getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public String getNome_funcionario() {
		return nome_funcionario;
	}

	public void setNome_funcionario(String nome_funcionario) {
		this.nome_funcionario = nome_funcionario;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public char getSituacao() {
		return situacao;
	}

	public void setSituacao(char situacao) {
		this.situacao = situacao;
	}
	
}
