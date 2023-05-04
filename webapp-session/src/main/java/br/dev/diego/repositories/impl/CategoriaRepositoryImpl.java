package br.dev.diego.repositories.impl;

import br.dev.diego.config.MySqlConn;
import br.dev.diego.config.Repository;
import br.dev.diego.entities.Categoria;
import br.dev.diego.repositories.CrudRepository;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoriaRepositoryImpl implements CrudRepository<Categoria> {

    private Connection conn;

    @Inject
    public CategoriaRepositoryImpl(@MySqlConn Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String query = """
                SELECT *
                FROM categorias;
                """;

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                categorias.add(getCategoria(rs));
            }
        }
        return categorias;
    }

    @Override
    public Categoria buscarPorId(Long id) throws SQLException {
        Categoria categoria = null;
        String query = """
                SELECT *
                FROM categorias
                WHERE categorias.id = ?;
                """;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categoria = getCategoria(rs);
                }
            }

        }
        return categoria;
    }

    @Override
    public void salvar(Categoria categoria) {

    }

    @Override
    public void excluir(Long id) throws SQLException {

    }

    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("id"));
        categoria.setNome(rs.getString("nome"));
        return categoria;
    }
}
