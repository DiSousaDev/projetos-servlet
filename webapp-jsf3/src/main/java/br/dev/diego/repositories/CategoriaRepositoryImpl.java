package br.dev.diego.repositories;

import br.dev.diego.entities.Categoria;
import br.dev.diego.entities.Categoria;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@RequestScoped
public class CategoriaRepositoryImpl implements CrudRepository<Categoria> {

    @Inject
    private EntityManager em;

    @Override
    public List<Categoria> buscarTodos() {
        return em.createNativeQuery("select * from categorias", Categoria.class).getResultList();
    }

    @Override
    public Categoria buscarPorId(Long id) {
        return em.find(Categoria.class, id);
    }

    @Override
    public void salvar(Categoria categoria) {
        if(categoria.getId() != null && categoria.getId() > 0) {
            em.merge(categoria);
        } else {
            em.persist(categoria);
        }
    }

    @Override
    public void excluir(Long id) {
        Categoria categoria = buscarPorId(id);
        em.remove(categoria);
    }
}
