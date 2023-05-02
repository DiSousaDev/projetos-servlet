package br.dev.diego.repositories.impl;

import br.dev.diego.entities.Usuario;
import br.dev.diego.repositories.UsuarioRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private Connection conn;

    public UsuarioRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        return null;
    }

    @Override
    public Usuario buscarPorId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void salvar(Usuario usuario) throws SQLException {

    }

    @Override
    public void excluir(Long id) throws SQLException {

    }

    @Override
    public Usuario buscarPorUsername(String username) throws SQLException {
        Usuario user = null;
        String query = """
                SELECT *
                FROM usuarios
                WHERE username = ?
                """;
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                   user = getUsuario(rs);
                }
            }
        }
        return user;
    }

    private Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario user = new Usuario();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        return user;
    }

}
