package br.com.soluct.shoppingCart.shoppingCart.services;

import java.util.List;
import java.util.Optional;

import br.com.soluct.shoppingCart.shoppingCart.entities.GenericEntity;
import br.com.soluct.shoppingCart.shoppingCart.repositories.GenericRepository;

public interface GenericService<TEntidade extends GenericEntity> {

	GenericRepository<TEntidade> getRepository();

// 	Salvar
	default TEntidade save(TEntidade entidade) throws Exception {
		return this.getRepository().save(entidade);
	}
	
// 	Busca por Id
	default Optional<TEntidade> findById(Long id) throws Exception {
		return this.getRepository().findById(id);
	}
	
// 	Busca todos
	default List<TEntidade> findAll() throws Exception {
		return this.getRepository().findAll();
	}
	
//	Deleta por id
	default void deleteByid(Long id) throws Exception {
		if (!this.getRepository().existsById(id)) {
			throw new Exception("ID não encontrado");
		}
		this.getRepository().deleteById(id);
	}
	
}
