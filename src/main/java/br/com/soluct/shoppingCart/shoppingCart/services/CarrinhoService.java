package br.com.soluct.shoppingCart.shoppingCart.services;

import br.com.soluct.shoppingCart.shoppingCart.entities.Carrinho;

public interface CarrinhoService extends GenericService<Carrinho> {
	
    void removeProdutoFromCarrinho(long carrinhoId, String produtoCodigo) throws Exception;
    
    Carrinho checkoutPromo(long carrinhoId) throws Exception;
	
}
