package br.dev.diego.controllers;

import br.dev.diego.entities.Categoria;
import br.dev.diego.entities.Produto;
import br.dev.diego.services.ProdutoService;
import br.dev.diego.services.impl.ProdutoServiceJdbcImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/produtos/form")
public class ProdutoFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProdutoService service = new ProdutoServiceJdbcImpl(conn);
        Long id = getId(req.getParameter("id"));

        Produto produto = new Produto();
        produto.setCategoria(new Categoria());
        if (id > 0) {
            Optional<Produto> produtoOptional = service.buscarPorId(id);
            if (produtoOptional.isPresent()) {
                produto = produtoOptional.get();
            }

        }
        req.setAttribute("categorias", service.buscarCategorias());
        req.setAttribute("produto", produto);
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProdutoService service = new ProdutoServiceJdbcImpl(conn);
        Long id = getId(req.getParameter("id"));
        String nome = req.getParameter("nome");
        Integer preco = getPreco(req.getParameter("preco"));
        String sku = req.getParameter("sku");
        String dataCadastro = getDataCadastro(req.getParameter("data_registro"));
        Long categoriaId = getCategoria(req.getParameter("categoria"));

        Map<String, String> erros = new HashMap<>();
        if (nome == null || nome.isBlank()) {
            erros.put("nome", "Informe o nome.");
        }
        if (sku == null || sku.isBlank()) {
            erros.put("sku", "Informe o sku.");
        }
        if (dataCadastro == null || dataCadastro.isBlank()) {
            erros.put("data_registro", "Informe um data de cadstro válida.");
        }
        if (preco.equals(0)) {
            erros.put("preco", "Informe o preço.");
        }
        if (categoriaId.equals(0L)) {
            erros.put("categoria", "Selecione a categoria.");
        }

        Produto produto = new Produto(id, nome, categoriaId, preco, sku, LocalDate.parse(dataCadastro));
        if (erros.isEmpty()) {
            try {
                service.salvar(produto);
                resp.sendRedirect(req.getContextPath() + "/produtos");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            req.setAttribute("erros", erros);
            req.setAttribute("categorias", service.buscarCategorias());
            req.setAttribute("produto", produto);
            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }

    private Integer getPreco(String parameter) {
        int preco = 0;
        try {
            return Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            return preco;
        }
    }

    private Long getId(String parameter) {
        Long id = 0L;
        try {
            return Long.valueOf(parameter);
        } catch (NumberFormatException e) {
            return id;
        }
    }

    private Long getCategoria(String parameter) {
        Long categoriaId = 0L;
        try {
            return Long.valueOf(parameter);
        } catch (NumberFormatException e) {
            return categoriaId;
        }
    }

    private String getDataCadastro(String parameter) {
        try {
            return LocalDate.parse(parameter, DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
        } catch (DateTimeParseException e) {
            return null;
        }
    }

}
