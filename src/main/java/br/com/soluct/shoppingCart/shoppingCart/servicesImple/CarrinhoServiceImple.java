package br.com.soluct.shoppingCart.shoppingCart.servicesImple;

import org.springframework.stereotype.Service;

import br.com.soluct.shoppingCart.shoppingCart.entities.Carrinho;
import br.com.soluct.shoppingCart.shoppingCart.repositories.CarrinhoRepository;
import br.com.soluct.shoppingCart.shoppingCart.repositories.GenericRepository;
import br.com.soluct.shoppingCart.shoppingCart.services.CarrinhoService;

@Service
public class CarrinhoServiceImple implements CarrinhoService {
	
	private final CarrinhoRepository carrinhoRepository;
	public CarrinhoServiceImple(CarrinhoRepository carrinhoRepository) {
		super();
		this.carrinhoRepository = carrinhoRepository;
	}
	
	@Override
	public GenericRepository<Carrinho> getRepository() {
		return carrinhoRepository;
	}
	
}
