package br.dev.diego.repositories;

import java.util.List;

public interface CrudRepository<T> {

    List<T> buscarTodos();
    T buscarPorId(Long id);
    void salvar(T t);
    void excluir(Long id);

}
