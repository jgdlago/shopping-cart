package br.com.soluct.shoppingCart.shoppingCart.services;

import br.com.soluct.shoppingCart.shoppingCart.entities.Produto;

public interface ProdutoService extends GenericService<Produto> {
	
	void deleteByCodigo(String codigo) throws Exception;

	void addToCart(Produto produto, long carrinhoId) throws Exception;
}
