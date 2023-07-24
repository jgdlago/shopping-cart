package br.com.soluct.shoppingCart.shoppingCart.services;

import java.util.List;
import java.util.Optional;

import br.com.soluct.shoppingCart.shoppingCart.entities.GenericEntity;
import br.com.soluct.shoppingCart.shoppingCart.repositories.GenericRepository;

public interface GenericService<TEntidade extends GenericEntity> {

	GenericRepository<TEntidade> getRepository();

	default TEntidade save(TEntidade entidade) throws Exception {
		return this.getRepository().save(entidade);
	}
	
	default Optional<TEntidade> findById(Long id) throws Exception {
		return this.getRepository().findById(id);
	}
	
	default List<TEntidade> findAll() throws Exception {
		return this.getRepository().findAll();
	}
	
	default void deleteByid(Long id) throws Exception {
		if (!this.getRepository().existsById(id)) {
			throw new Exception("ID não encontrado");
		}
		this.getRepository().deleteById(id);
	}
	
}
