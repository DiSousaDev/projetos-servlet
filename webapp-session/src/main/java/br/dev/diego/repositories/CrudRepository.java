package br.dev.diego.repositories;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T> {

    List<T> listar() throws SQLException;

    T buscarPorId(Long id) throws SQLException;

    void salvar(T t) throws SQLException;

    void excluir(Long id) throws SQLException;

}
