package br.dev.diego.services;

import br.dev.diego.entities.Categoria;
import br.dev.diego.entities.Produto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    List<Produto> listarProdutos();

    Optional<Produto> buscarPorId(Long id);

    void salvar(Produto produto) throws SQLException;

    void excluir(Long id);

    List<Categoria> buscarCategorias();

    Optional<Categoria> buscarCategoriaPorId(Long id);

}
