package br.com.soluct.shoppingCart.shoppingCart.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@DeleteMapping("/codigo/{codigo}")
	public ResponseEntity<Object> deleteByCodigo(@PathVariable String codigo) {
	    try {
	        produtoService.deleteByCodigo(codigo);
	        return ResponseEntity.status(HttpStatus.OK).body("Produto " + codigo + " deletado");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto " + codigo + " não encontrado");
	    }
	}
	
	@PutMapping("/{produtoId}/addCarrinho/{carrinhoId}")
	public ResponseEntity<Object> addToCart(@PathVariable long produtoId, @PathVariable Long carrinhoId) {
        try {
            produtoService.addToCart(produtoId, carrinhoId);
            return ResponseEntity.status(HttpStatus.OK).body("Produto adicionado ao carrinho!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
	}
	
	@PutMapping("/{produtoId}/updateQtd/{quantidade}")
	public ResponseEntity<Object> updateQtd(@PathVariable long produtoId, @PathVariable int quantidade){
        try {
            produtoService.updateQtd(produtoId, quantidade);
            return ResponseEntity.status(HttpStatus.OK).body("Produto atualizado!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
	}
	
}
