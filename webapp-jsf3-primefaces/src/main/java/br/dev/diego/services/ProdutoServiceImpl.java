package br.dev.diego.services;

import br.dev.diego.entities.Categoria;
import br.dev.diego.entities.Produto;
import br.dev.diego.repositories.CrudRepository;
import br.dev.diego.repositories.ProdutoRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Stateless
public class ProdutoServiceImpl implements ProdutoService {

    @Inject
    private ProdutoRepository repository;
    @Inject
    private CrudRepository<Categoria> categoriaRepository;

    @Override
    public List<Produto> buscarTodos() {
        return repository.buscarTodos();
    }

    @Override
    public List<Produto> buscarPorNome(String nome) {
        return repository.buscarPorNome(nome);
    }

    @Override
    public List<Categoria> buscarTodasCategorias() {
        return categoriaRepository.buscarTodos();
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        return Optional.ofNullable(repository.buscarPorId(id));
    }

    @Override
    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        return Optional.ofNullable(categoriaRepository.buscarPorId(id));
    }

    @Override
    public void salvar(Produto produto) {
        repository.salvar(produto);
    }

    @Override
    public void excluir(Long id) {
        repository.excluir(id);
    }
}
