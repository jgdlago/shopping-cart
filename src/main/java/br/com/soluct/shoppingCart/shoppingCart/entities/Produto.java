package br.com.soluct.shoppingCart.shoppingCart.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "produto")
public class Produto extends GenericEntity {
	
	@Column(length = 3, nullable = false)
	private String codigo;
	
	@Column(length = 100, nullable = false)
	private String nomeProduto;
	
	@Column(length = 5, nullable = true)
	private String unidadeMedida;
	
	@Column(nullable = false)
	private Double valor;
	
	@Column(nullable = true)
	private int quantidade;

	@ManyToOne
	@JoinColumn(name = "carrinho_id", nullable = true)
	@JsonIgnore
	private Carrinho carrinho;
	
	public String getCodigo() {
		return codigo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
}
