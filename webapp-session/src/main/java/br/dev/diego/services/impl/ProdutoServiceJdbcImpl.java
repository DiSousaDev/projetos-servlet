package br.dev.diego.services.impl;

import br.dev.diego.entities.Categoria;
import br.dev.diego.entities.Produto;
import br.dev.diego.repositories.CategoriaRepositoryImpl;
import br.dev.diego.repositories.ProdutoJdbcImpl;
import br.dev.diego.repositories.Repository;
import br.dev.diego.services.ProdutoService;
import br.dev.diego.services.exceptions.ServerJdbcException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProdutoServiceJdbcImpl implements ProdutoService {

    private final Repository<Categoria> categoriaRepository;
    private final Repository<Produto> produtoRepository;

    public ProdutoServiceJdbcImpl(Connection conn) {
        this.produtoRepository = new ProdutoJdbcImpl(conn);
        this.categoriaRepository = new CategoriaRepositoryImpl(conn);
    }

    @Override
    public List<Produto> listarProdutos() {
        try {
            return produtoRepository.listar();
        } catch (SQLException e) {
            throw new ServerJdbcException("Erro ao listar produtos", e.getCause());
        }
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        try {
            return Optional.ofNullable(produtoRepository.buscarPorId(id));
        } catch (SQLException e) {
            throw new ServerJdbcException("Erro ao buscar produtos", e.getCause());
        }
    }

    @Override
    public void salvar(Produto produto) {
        try {
            produtoRepository.salvar(produto);
        } catch (SQLException e) {
            throw new ServerJdbcException("Erro ao salvar produtos", e.getCause());
        }
    }

    @Override
    public void excluir(Long id) {
        try {
            produtoRepository.excluir(id);
        } catch (SQLException e) {
            throw new ServerJdbcException("Erro ao excluir produtos", e.getCause());
        }
    }

    @Override
    public List<Categoria> buscarCategorias() {
        try {
            return categoriaRepository.listar();
        } catch (SQLException e) {
            throw new ServerJdbcException("Erro ao bucar categorias produtos", e.getCause());
        }
    }

    @Override
    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        try {
            return Optional.ofNullable(categoriaRepository.buscarPorId(id));
        } catch (SQLException e) {
            throw new ServerJdbcException("Erro ao bucar categorias produtos", e.getCause());
        }
    }
}
