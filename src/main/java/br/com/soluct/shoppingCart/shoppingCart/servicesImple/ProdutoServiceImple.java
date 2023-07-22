package br.com.soluct.shoppingCart.shoppingCart.servicesImple;

import org.springframework.stereotype.Service;

import br.com.soluct.shoppingCart.shoppingCart.entities.Produto;
import br.com.soluct.shoppingCart.shoppingCart.repositories.GenericRepository;
import br.com.soluct.shoppingCart.shoppingCart.repositories.ProdutoRepository;
import br.com.soluct.shoppingCart.shoppingCart.services.ProdutoService;

@Service
public class ProdutoServiceImple implements ProdutoService {
	
	private final ProdutoRepository produtoRepository;
	public ProdutoServiceImple(ProdutoRepository produtoRepository) {
		super();
		this.produtoRepository = produtoRepository;
	}
	
	@Override
	public GenericRepository<Produto> getRepository() {
		return produtoRepository;
	}
	
}
