package br.dev.diego.services;

import br.dev.diego.entities.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    List<Produto> listarProdutos();

    Optional<Produto> buscarPorNome(String nome);

}
