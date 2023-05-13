package br.dev.diego.services.impl;

import br.dev.diego.config.RepositoryJPA;
import br.dev.diego.config.Service;
import br.dev.diego.entities.Usuario;
import br.dev.diego.interceptors.TransactionalJpa;
import br.dev.diego.repositories.UsuarioRepository;
import br.dev.diego.services.UsuarioService;
import br.dev.diego.services.exceptions.ServerJdbcException;
import jakarta.inject.Inject;

import java.util.Optional;

@Service
@TransactionalJpa
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository repository;

    @Inject
    public UsuarioServiceImpl(@RepositoryJPA UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(repository.buscarPorUsername(username))
                    .filter(usuario -> usuario.getPassword().equals(password));
        } catch (Exception e) {
            throw new ServerJdbcException(e.getMessage(), e.getCause());
        }
    }
}
