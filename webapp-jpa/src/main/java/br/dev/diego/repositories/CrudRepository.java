package br.dev.diego.repositories;

import java.util.List;

public interface CrudRepository<T> {

    List<T> listar() throws Exception;

    T buscarPorId(Long id) throws Exception;

    void salvar(T t) throws Exception;

    void excluir(Long id) throws Exception;

}
