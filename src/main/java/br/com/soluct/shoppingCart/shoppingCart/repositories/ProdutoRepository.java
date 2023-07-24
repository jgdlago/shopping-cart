package br.com.soluct.shoppingCart.shoppingCart.repositories;

import org.springframework.stereotype.Repository;

import br.com.soluct.shoppingCart.shoppingCart.entities.Produto;

@Repository
public interface ProdutoRepository extends GenericRepository<Produto> {

	Produto findByCodigo(String codigo);

}
