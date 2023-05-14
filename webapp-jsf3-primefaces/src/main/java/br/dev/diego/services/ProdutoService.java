package br.dev.diego.services;

import br.dev.diego.entities.Categoria;
import br.dev.diego.entities.Produto;
import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface ProdutoService {

    List<Produto> buscarTodos();
    List<Produto> buscarPorNome(String nome);
    List<Categoria> buscarTodasCategorias();
    Optional<Produto> buscarPorId(Long id);
    Optional<Categoria> buscarCategoriaPorId(Long id);
    void salvar(Produto produto);
    void excluir(Long id);

}
