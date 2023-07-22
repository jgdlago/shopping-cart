package br.com.soluct.shoppingCart.shoppingCart.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.soluct.shoppingCart.shoppingCart.entities.Carrinho;
import br.com.soluct.shoppingCart.shoppingCart.services.CarrinhoService;
import br.com.soluct.shoppingCart.shoppingCart.services.GenericService;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/carrinho")
public class CarrinhoController extends GenericController<Carrinho> {
	
	private final CarrinhoService carrinhoService;
	public CarrinhoController(CarrinhoService carrinhoService) {
		super();
		this.carrinhoService = carrinhoService;
	}
	
	@Override
	GenericService<Carrinho> getService() {
		return carrinhoService;
	}
}
