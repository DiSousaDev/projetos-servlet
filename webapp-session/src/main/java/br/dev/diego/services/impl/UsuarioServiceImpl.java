package br.dev.diego.services.impl;

import br.dev.diego.entities.Usuario;
import br.dev.diego.repositories.Repository;
import br.dev.diego.repositories.UsuarioRepository;
import br.dev.diego.repositories.impl.UsuarioRepositoryImpl;
import br.dev.diego.services.UsuarioService;
import br.dev.diego.services.exceptions.ServerJdbcException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository repository;

    public UsuarioServiceImpl(Connection conn) {
        this.repository = new UsuarioRepositoryImpl(conn);
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(repository.buscarPorUsername(username))
                    .filter(usuario -> usuario.getPassword().equals(password));
        } catch (SQLException e) {
            throw new ServerJdbcException(e.getMessage(), e.getCause());
        }
    }
}
