package br.dev.diego.repositories;

import br.dev.diego.entities.Produto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@RequestScoped
public class ProdutoRepositoryImpl implements ProdutoRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Produto> buscarTodos() {
        String query = """
                select p
                from Produto p
                left outer join fetch p.categoria
                """;
        return em.createQuery(query, Produto.class).getResultList();
    }

    @Override
    public Produto buscarPorId(Long id) {
        String query = """
                select p
                from Produto p
                left outer join fetch p.categoria
                where p.id = :id
                """;
        return em.createQuery(query, Produto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void salvar(Produto produto) {
        if(produto.getId() != null && produto.getId() > 0) {
            em.merge(produto);
        } else {
            em.persist(produto);
        }
    }

    @Override
    public void excluir(Long id) {
        Produto produto = buscarPorId(id);
        em.remove(produto);
    }

    @Override
    public List<Produto> buscarPorNome(String nome) {
        String query = """
                select p
                from Produto p
                left outer join fetch p.categoria
                where p.nome like :nome
                """;
        return em.createQuery(query, Produto.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }
}
