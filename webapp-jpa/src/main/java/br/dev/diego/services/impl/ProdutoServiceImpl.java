package br.dev.diego.services.impl;

import br.dev.diego.config.ProdutoServicePrincipal;
import br.dev.diego.config.RepositoryJPA;
import br.dev.diego.config.Service;
import br.dev.diego.entities.Categoria;
import br.dev.diego.entities.Produto;
import br.dev.diego.interceptors.TransactionalJpa;
import br.dev.diego.repositories.CrudRepository;
import br.dev.diego.services.ProdutoService;
import br.dev.diego.services.exceptions.ServerJdbcException;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Service
@ProdutoServicePrincipal
@TransactionalJpa
public class ProdutoServiceImpl implements ProdutoService {

    @Inject
    @RepositoryJPA
    private CrudRepository<Categoria> categoriaRepository;
    @Inject
    @RepositoryJPA
    private CrudRepository<Produto> produtoRepository;

    @Override
    public List<Produto> listarProdutos() {
        try {
            return produtoRepository.listar();
        } catch (Exception e) {
            throw new ServerJdbcException("Erro ao listar produtos", e.getCause());
        }
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        try {
            return Optional.ofNullable(produtoRepository.buscarPorId(id));
        } catch (Exception e) {
            throw new ServerJdbcException("Erro ao buscar produtos", e.getCause());
        }
    }

    @Override
    public void salvar(Produto produto) {
        try {
            produtoRepository.salvar(produto);
        } catch (Exception e) {
            throw new ServerJdbcException("Erro ao salvar produtos", e.getCause());
        }
    }

    @Override
    public void excluir(Long id) {
        try {
            produtoRepository.excluir(id);
        } catch (Exception e) {
            throw new ServerJdbcException("Erro ao excluir produtos", e.getCause());
        }
    }

    @Override
    public List<Categoria> buscarCategorias() {
        try {
            return categoriaRepository.listar();
        } catch (Exception e) {
            throw new ServerJdbcException("Erro ao bucar categorias produtos", e.getCause());
        }
    }

    @Override
    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        try {
            return Optional.ofNullable(categoriaRepository.buscarPorId(id));
        } catch (Exception e) {
            throw new ServerJdbcException("Erro ao bucar categorias produtos", e.getCause());
        }
    }
}
