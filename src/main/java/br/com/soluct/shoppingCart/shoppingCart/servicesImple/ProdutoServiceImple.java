package br.com.soluct.shoppingCart.shoppingCart.servicesImple;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public ProdutoServiceImple(ProdutoRepository produtoRepository, CarrinhoRepository carrinhoRepository) {
		super();
		this.produtoRepository = produtoRepository;
		this.carrinhoRepository = carrinhoRepository;
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
    public void addToCart(Produto produto, long carrinhoId) throws Exception {
        Carrinho carrinho;
        Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(carrinhoId);

        if (carrinhoOptional.isPresent()) {
            carrinho = carrinhoOptional.get();
        } else {
            // Se o carrinho não existe, crie um novo carrinho
            carrinho = new Carrinho();
            carrinho.setProdutos(new ArrayList<>());
        }

        List<Produto> listaProdutos = carrinho.getProdutos();
        listaProdutos.add(produto);

        // Defina o carrinho para o produto
        produto.setCarrinho(carrinho);

        carrinho.setProdutos(listaProdutos);
        carrinhoRepository.save(carrinho);
    }





}
