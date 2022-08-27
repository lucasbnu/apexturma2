package com.projeto.app.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "vendas")
@Entity
public class VendasModelo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_venda") 
    private int id_venda;	 
	
	@Column(name = "fk_cliente")
	private int fk_cliente;
	
	@Column(name = "fk_produto")
	private int fk_produto;
	
	@Column(name = "fk_funcionario")
	private int fk_funcionario;

	public int getId_venda() {
		return id_venda;
	}

	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}

	public int getFk_cliente() {
		return fk_cliente;
	}

	public void setFk_cliente(int fk_cliente) {
		this.fk_cliente = fk_cliente;
	}

	public int getFk_produto() {
		return fk_produto;
	}

	public void setFk_produto(int fk_produto) {
		this.fk_produto = fk_produto;
	}

	public int getFk_funcionario() {
		return fk_funcionario;
	}

	public void setFk_funcionario(int fk_funcionario) {
		this.fk_funcionario = fk_funcionario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Column(name = "quantidade")
	private int quantidade;
}
