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
            produto.setCarrinho(null);
            produto.setQuantidade(0);
            carrinhoRepository.save(carrinho);
        } else {
            throw new Exception("Produto não encontrado no carrinho");
        }
    }
    

    @Override
    public Carrinho checkoutPromo(long carrinhoId) throws Exception {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new Exception("Carrinho não encontrado com o ID: " + carrinhoId));

        double valorTotal = 0.0;

        for (Produto produto : carrinho.getProdutos()) {
            if (produto.getCodigo().equals("AL3")) {
                promoBiscoitos(produto);
            } else if (produto.getCodigo().equals("UT1")) {
                promoPotes(produto);
            } else if (produto.getCodigo().equals("BD1")) {
                promoEnergetico(produto);
            } else if (produto.getCodigo().equals("BD2")) {
                promoVinho(produto);
            } else if (produto.getCodigo().equals("BD3")) {
                promoSuco(produto);
            } else if (produto.getCodigo().equals("AL5")) {
                promoCremeLeite(produto);
            }

            valorTotal += produto.getValor() * produto.getQuantidade();
        }

        carrinho.setValorTotal(valorTotal);
        return carrinhoRepository.save(carrinho);
    }

    
//  Biscoitos
    public Produto promoBiscoitos(Produto produto) {
        int quantidade = produto.getQuantidade();
        int quantidadePaga = quantidade / 2;
        int quantidadeTotal = quantidade + quantidadePaga;

        double valorUnitarioPromocional = produto.getValor().doubleValue() / quantidadeTotal;
        valorUnitarioPromocional = Math.round(valorUnitarioPromocional * 100.0) / 100.0;

        produto.setQuantidade(quantidadeTotal);
        produto.setValor(Double.valueOf(valorUnitarioPromocional));
        return produto;
    }
    
//	potes
    public Produto promoPotes(Produto produto) {
    	if(produto.getQuantidade() > 1) {
    		produto.setValor(21.70);
    	}
		return produto;
    }
    
// 	Energetico
    public Produto promoEnergetico(Produto produto) {
		if(produto.getQuantidade() > 6) {
			produto.setValor(7.70);
		}
    	return produto;
    }
    
//  vinho
    public Produto promoVinho(Produto produto) {
        int quantidade = produto.getQuantidade();
        int quantidadePagas = quantidade / 3; 
        int quantidadeGratis = quantidadePagas;

        double valorUnitario = produto.getValor();
        double valorUnitarioComDesconto = valorUnitario / (quantidade - quantidadeGratis);

        produto.setValor(valorUnitarioComDesconto);
        return produto;
    }
    
//  Suco
    public Produto promoSuco(Produto produto) {   
        if (produto.getQuantidade() >= 4) {
            double desconto = produto.getValor() * 0.1;
            double valorComDesconto = produto.getValor() - desconto;
            produto.setValor(valorComDesconto);
        }
        return produto;
    }
    
// Creme de leite
    public Produto promoCremeLeite(Produto produto) {
    	if(produto.getQuantidade() >= 3) {
    		produto.setValor(3.00);
    	}
    	
    	return produto;
    }
    
}
