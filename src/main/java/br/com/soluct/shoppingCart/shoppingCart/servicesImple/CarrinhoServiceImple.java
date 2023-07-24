package br.com.soluct.shoppingCart.shoppingCart.servicesImple;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.soluct.shoppingCart.shoppingCart.entities.Carrinho;
import br.com.soluct.shoppingCart.shoppingCart.entities.Produto;
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
	
    @Override
    public void removeProdutoFromCarrinho(long carrinhoId, String produtoCodigo) throws Exception {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new Exception("Carrinho não encontrado"));

        Optional<Produto> produtoOptional = carrinho.getProdutos().stream()
                .filter(produto -> produto.getCodigo().equals(produtoCodigo))
                .findFirst();

        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            carrinho.getProdutos().remove(produto);
            carrinhoRepository.save(carrinho);
        } else {
            throw new Exception("Produto não encontrado no carrinho");
        }
    }
	
}
