package br.dev.diego.repositories.impl;

import br.dev.diego.config.MySqlConn;
import br.dev.diego.config.Repository;
import br.dev.diego.entities.Categoria;
import br.dev.diego.entities.Produto;
import br.dev.diego.repositories.CrudRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ProdutoJdbcImpl implements CrudRepository<Produto> {

    @Inject
    @MySqlConn
    private Connection conn;

    @Inject
    private Logger log;

    @PostConstruct
    public void iniciar() {
        log.info("Iniciando beam " + this.getClass().getName());
    }

    @PreDestroy
    public void encerrar() {
        log.info("Finalizando beam " + this.getClass().getName());
    }

    @Override
    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String query = """
                SELECT p.*, c.nome as categoria
                FROM produtos as p
                INNER JOIN categorias as c on p.categoria_id = c.id
                ORDER BY p.id ASC;
                """;

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                produtos.add(getProduto(rs));
            }
        }
        return produtos;
    }

    @Override
    public Produto buscarPorId(Long id) throws SQLException {
        Produto produto = null;
        String query = """
                SELECT p.*, c.nome as categoria FROM produtos as p
                INNER JOIN categorias as c ON (p.categoria_id = c.id)
                WHERE p.id = ?;
                """;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produto = getProduto(rs);
                }
            }

        }
        return produto;
    }

    @Override
    public void salvar(Produto produto) throws SQLException {
        String sql;
        if (isUpdate(produto)) {
            sql = "update produtos set nome=?, preco=?, sku=?, categoria_id=? where id=?";
        } else {
            sql = "insert into produtos (nome, preco, sku, categoria_id, data_registro) values (?, ?, ?, ?, ?)";
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getPreco());
            stmt.setString(3, produto.getSku());
            stmt.setLong(4, produto.getCategoria().getId());

            if (isUpdate(produto)) {
                stmt.setLong(5, produto.getId());
            } else {
                stmt.setDate(5, Date.valueOf(produto.getDataRegistro()));
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(Long id) throws SQLException {
        String sql = "delete from produtos where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private static boolean isUpdate(Produto produto) {
        return produto.getId() != null && produto.getId() > 0;
    }

    private static Produto getProduto(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("categoria_id"));
        categoria.setNome(rs.getString("categoria"));

        Produto produto = new Produto();
        produto.setId(rs.getLong("id"));
        produto.setNome(rs.getString("nome"));
        produto.setPreco(rs.getInt("preco"));
        produto.setCategoria(categoria);
        produto.setSku(rs.getString("sku"));
        produto.setDataRegistro(rs.getDate("data_registro").toLocalDate());
        return produto;
    }
}
