package br.com.soluct.shoppingCart.shoppingCart.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.soluct.shoppingCart.shoppingCart.entities.Produto;
import br.com.soluct.shoppingCart.shoppingCart.services.GenericService;
import br.com.soluct.shoppingCart.shoppingCart.services.ProdutoService;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/produto")
public class ProdutoController extends GenericController<Produto> {
	
	private final ProdutoService produtoService;
	public ProdutoController(ProdutoService produtoService) {
		super();
		this.produtoService = produtoService;
	}
	
	@Override
	GenericService<Produto> getService() {
		return produtoService;
	}
}
