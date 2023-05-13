package br.dev.diego.repositories.impl;

import br.dev.diego.config.Repository;
import br.dev.diego.config.RepositoryJPA;
import br.dev.diego.entities.Produto;
import br.dev.diego.repositories.CrudRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@RepositoryJPA
@Repository
public class ProdutoRepositoryJPAImpl implements CrudRepository<Produto> {

    @Inject
    private EntityManager em;

    @Override
    public List<Produto> listar() throws Exception {
        return em.createQuery("from Produto", Produto.class).getResultList();
    }

    @Override
    public Produto buscarPorId(Long id) throws Exception {
        return em.find(Produto.class, id);
    }

    @Override
    public void salvar(Produto produto) throws Exception {
        if (produto.getId() != null && produto.getId() > 0) {
            em.merge(produto);
        } else {
            em.persist(produto);
        }
    }

    @Override
    public void excluir(Long id) throws Exception {
        em.remove(buscarPorId(id));
    }
}
