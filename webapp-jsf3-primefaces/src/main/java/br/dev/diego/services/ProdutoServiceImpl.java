package br.dev.diego.services;

import br.dev.diego.entities.Categoria;
import br.dev.diego.entities.Produto;
import br.dev.diego.repositories.CrudRepository;
import br.dev.diego.repositories.ProdutoRepository;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Stateless
@DeclareRoles({"USER", "ADMIN"})
public class ProdutoServiceImpl implements ProdutoService {

    @Inject
    private ProdutoRepository repository;
    @Inject
    private CrudRepository<Categoria> categoriaRepository;

    @Override
    @PermitAll
    public List<Produto> buscarTodos() {
        return repository.buscarTodos();
    }

    @Override
    @RolesAllowed({ "USER", "ADMIN" })
    public List<Produto> buscarPorNome(String nome) {
        return repository.buscarPorNome(nome);
    }

    @Override
    @RolesAllowed({ "USER", "ADMIN" })
    public List<Categoria> buscarTodasCategorias() {
        return categoriaRepository.buscarTodos();
    }

    @Override
    @RolesAllowed({ "USER", "ADMIN" })
    public Optional<Produto> buscarPorId(Long id) {
        return Optional.ofNullable(repository.buscarPorId(id));
    }

    @Override
    @RolesAllowed({ "USER", "ADMIN" })
    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        return Optional.ofNullable(categoriaRepository.buscarPorId(id));
    }

    @Override
    @RolesAllowed({ "ADMIN" })
    public void salvar(Produto produto) {
        repository.salvar(produto);
    }

    @Override
    @RolesAllowed({ "ADMIN" })
    public void excluir(Long id) {
        repository.excluir(id);
    }
}
