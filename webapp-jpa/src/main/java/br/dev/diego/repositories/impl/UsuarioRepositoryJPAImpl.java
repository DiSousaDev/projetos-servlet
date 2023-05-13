package br.dev.diego.repositories.impl;

import br.dev.diego.config.Repository;
import br.dev.diego.config.RepositoryJPA;
import br.dev.diego.entities.Usuario;
import br.dev.diego.repositories.UsuarioRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@RepositoryJPA
@Repository
public class UsuarioRepositoryJPAImpl implements UsuarioRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Usuario> listar() throws Exception {
        return em.createQuery("from Usuario", Usuario.class).getResultList();
    }

    @Override
    public Usuario buscarPorId(Long id) throws Exception {
        return em.find(Usuario.class, id);
    }

    @Override
    public void salvar(Usuario usuario) throws Exception {
        if (usuario.getId() != null && usuario.getId() > 0) {
            em.merge(usuario);
        } else {
            em.persist(usuario);
        }
    }

    @Override
    public void excluir(Long id) throws Exception {
        em.remove(buscarPorId(id));
    }

    @Override
    public Usuario buscarPorUsername(String username) throws Exception {
        String query = """
                SELECT u
                FROM Usuario u
                WHERE u.username = :username
                """;
        return em.createQuery(query, Usuario.class).setParameter("username", username).getSingleResult();
    }
}
