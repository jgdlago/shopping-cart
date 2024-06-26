package br.com.soluct.shoppingCart.shoppingCart.services;

import br.com.soluct.shoppingCart.shoppingCart.entities.Produto;

public interface ProdutoService extends GenericService<Produto> {
	
	void deleteByCodigo(String codigo) throws Exception;

	void addToCart(long produtoId, long carrinhoId) throws Exception;
	
    void updateQtd(long produtoId, int novaQuantidade) throws Exception;
}
