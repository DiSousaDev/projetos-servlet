package br.dev.diego.repositories.impl;

import br.dev.diego.config.Repository;
import br.dev.diego.config.RepositoryJPA;
import br.dev.diego.entities.Categoria;
import br.dev.diego.entities.Categoria;
import br.dev.diego.repositories.CrudRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@RepositoryJPA
@Repository
public class CategoriaRepositoryJPAImpl implements CrudRepository<Categoria> {

    @Inject
    private EntityManager em;

    @Override
    public List<Categoria> listar() throws Exception {
        return em.createQuery("from Categoria", Categoria.class).getResultList();
    }

    @Override
    public Categoria buscarPorId(Long id) throws Exception {
        return em.find(Categoria.class, id);
    }

    @Override
    public void salvar(Categoria categoria) throws Exception {
        if(categoria.getId() != null && categoria.getId() >0) {
            em.merge(categoria);
        } else {
            em.persist(categoria);
        }
    }

    @Override
    public void excluir(Long id) throws Exception {
        em.remove(buscarPorId(id));
    }
}
