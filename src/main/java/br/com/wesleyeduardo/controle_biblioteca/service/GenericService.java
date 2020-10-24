package br.com.wesleyeduardo.controle_biblioteca.service;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

final class GenericService<T> {

    private final JpaRepository<T, Integer> repository;

    GenericService(JpaRepository<T, Integer> repository ) {
        this.repository = repository;
    }


    T salva(T entity) {
        return repository.save(entity ) ;
    }


    List<T> todos() {
        return repository.findAll();
    }


    T atualiza(T entity, Integer id) {
        T entityDoBanco = this.buscaPor(id );
        BeanUtils.copyProperties(entity, entityDoBanco, "id" );

        return entityDoBanco;
    }


    T buscaPor(Integer id) {
        return repository
                .findById(id )
                .orElseThrow(
                        () -> new EmptyResultDataAccessException(1) );
    }

    public void excluirPor(Integer id) {
        this.repository.deleteById(id );
    }
}
