package br.com.soluct.shoppingCart.shoppingCart.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
    @DeleteMapping("/{carrinhoId}/produto/{produtoCodigo}")
    public ResponseEntity<Object> removeProdutoFromCarrinho(@PathVariable long carrinhoId, @PathVariable String produtoCodigo) {
        try {
            carrinhoService.removeProdutoFromCarrinho(carrinhoId, produtoCodigo);
            return ResponseEntity.status(HttpStatus.OK).body("Produto removido do carrinho com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
