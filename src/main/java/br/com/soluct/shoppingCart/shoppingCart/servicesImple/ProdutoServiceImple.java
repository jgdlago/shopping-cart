package br.com.soluct.shoppingCart.shoppingCart.servicesImple;


import org.springframework.stereotype.Service;

import br.com.soluct.shoppingCart.shoppingCart.entities.Carrinho;
import br.com.soluct.shoppingCart.shoppingCart.entities.Produto;
import br.com.soluct.shoppingCart.shoppingCart.repositories.CarrinhoRepository;
import br.com.soluct.shoppingCart.shoppingCart.repositories.GenericRepository;
import br.com.soluct.shoppingCart.shoppingCart.repositories.ProdutoRepository;
import br.com.soluct.shoppingCart.shoppingCart.services.ProdutoService;

@Service
public class ProdutoServiceImple implements ProdutoService {
	
	private final ProdutoRepository produtoRepository;
	private final CarrinhoRepository carrinhoRepository;
	private final CarrinhoServiceImple carrinhoServiceImple;
	public ProdutoServiceImple(ProdutoRepository produtoRepository, CarrinhoRepository carrinhoRepository, CarrinhoServiceImple carrinhoServiceImple) {
		super();
		this.produtoRepository = produtoRepository;
		this.carrinhoRepository = carrinhoRepository;
		this.carrinhoServiceImple = carrinhoServiceImple;
	}
	
	@Override
	public GenericRepository<Produto> getRepository() {
		return produtoRepository;
	}
	
    @Override
    public void deleteByCodigo(String codigo) throws Exception {
        Produto produto = produtoRepository.findByCodigo(codigo);
        if (produto != null) {
            produtoRepository.delete(produto);
        } else {
            throw new Exception("Produto não encontrado");
        }
    }

    @Override
    public void addToCart(long produtoId, long carrinhoId) throws Exception {
        Produto produto = produtoRepository.findById(produtoId).orElse(null);
        Carrinho carrinho = null;
        if (carrinhoId != 0) {
            carrinho = carrinhoRepository.findById(carrinhoId).orElse(null);
        }
        if (carrinho == null) {
            carrinho = new Carrinho();
            carrinho.setId(1L);
            carrinhoRepository.save(carrinho);
        }

        if (produto != null && carrinho != null) {
            produto.setCarrinho(carrinho);
            produto.setQuantidade(1);
            produtoRepository.save(produto);
            carrinhoServiceImple.checkoutPromo(carrinhoId);
        } else {
            throw new IllegalArgumentException("Produto ou Carrinho não encontrado");
        }
    }
    
    @Override
    public void updateQtd(long produtoId, int novaQuantidade) throws Exception {
        Produto produto = produtoRepository.findById(produtoId).orElse(null);

        if (produto != null) {
            produto.setQuantidade(novaQuantidade);
            produtoRepository.save(produto);
            
        } else {
            throw new Exception("Produto não encontrado");
        }
    }

}
