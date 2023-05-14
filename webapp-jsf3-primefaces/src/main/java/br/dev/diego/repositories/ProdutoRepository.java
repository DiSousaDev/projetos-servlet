package br.dev.diego.repositories;

import br.dev.diego.entities.Produto;

import java.util.List;

public interface ProdutoRepository extends CrudRepository<Produto> {

    List<Produto> buscarPorNome(String nome);

}
