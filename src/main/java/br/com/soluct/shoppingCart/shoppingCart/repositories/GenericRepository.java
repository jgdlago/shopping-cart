package br.com.soluct.shoppingCart.shoppingCart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.soluct.shoppingCart.shoppingCart.entities.GenericEntity;

@NoRepositoryBean
public interface GenericRepository<TEntidade extends GenericEntity> extends JpaRepository<TEntidade, Long> {

}
