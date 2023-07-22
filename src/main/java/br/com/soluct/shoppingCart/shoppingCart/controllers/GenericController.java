package br.com.soluct.shoppingCart.shoppingCart.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.soluct.shoppingCart.shoppingCart.entities.GenericEntity;
import br.com.soluct.shoppingCart.shoppingCart.services.GenericService;

public abstract class GenericController <TEntidade extends GenericEntity> {
	
	abstract GenericService<TEntidade> getService();

	// Salvar
		@PostMapping
		public ResponseEntity<Object> save(@RequestBody TEntidade entidade) {
			try {
				getService().save(entidade);
				return ResponseEntity.status(HttpStatus.CREATED).body(entidade);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		}
		
	// Busca por id
		@GetMapping("/{id}")
		public ResponseEntity<Object> findById(@PathVariable long id) {
			try {
				Optional<TEntidade> entidade = getService().findById(id);
				if (entidade.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID não encontrado");
				}
				return ResponseEntity.status(HttpStatus.OK).body(entidade);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		}
		
	// Busca todos
		@GetMapping
		public ResponseEntity<Object> findAll() {
			try {
				List<TEntidade> entidades = getService().findAll();
				return ResponseEntity.status(HttpStatus.OK).body(entidades);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		}
		
	// Deleta por ID
		@DeleteMapping("/{id}")
		public ResponseEntity<Object> delete(@PathVariable long id) {
			try {			
				getService().deleteByid(id);
				return ResponseEntity.status(HttpStatus.OK).body("ID "+id+" Deletado");
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		}
		
}
