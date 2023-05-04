package br.dev.diego.services.impl;

import br.dev.diego.entities.Categoria;
import br.dev.diego.entities.Produto;
import br.dev.diego.services.ProdutoService;
import jakarta.enterprise.inject.Alternative;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@Alternative
public class ProdutoServiceImpl implements ProdutoService {
    @Override
    public List<Produto> listarProdutos() {
        return Arrays.asList(
                new Produto(1L, "Notebook", 1L, 3500, "sku", LocalDate.now()),
                new Produto(2L, "Mesa de Escritório", 1L, 1200, "sku", LocalDate.now()),
                new Produto(3L, "Teclado Mecânico", 1L, 289, "sku", LocalDate.now())
        );
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        if (id == null || id == 0) {
            return Optional.empty();
        }
        List<Produto> produtos = listarProdutos();
        return produtos.stream().filter(produto -> Objects.equals(produto.getId(), id)).findFirst();
    }

    @Override
    public void salvar(Produto produto) {

    }

    @Override
    public void excluir(Long id) {

    }

    @Override
    public List<Categoria> buscarCategorias() {
        return null;
    }

    @Override
    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        return Optional.empty();
    }

}
