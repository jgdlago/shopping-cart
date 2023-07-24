package br.com.soluct.shoppingCart.shoppingCart.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "carrinho")
public class Carrinho extends GenericEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carrinho", orphanRemoval = true)
	private List<Produto> produtos;
    
    @Column
    private Double valorTotal;

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
    
    
}
