package br.dev.diego.controllers;

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
import java.util.Optional;

@WebServlet("/produtos/excluir")
public class ProdutoExcluirServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProdutoService service = new ProdutoServiceJdbcImpl(conn);
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        if(id > 0) {
            Optional<Produto> produtoOptional = service.buscarPorId(id);
            if (produtoOptional.isPresent()) {
                service.excluir(id);
                resp.sendRedirect(req.getContextPath() + "/produtos");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Produto informado não existe.");
            }
        }else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Informe o ID do produto para excluir.");
        }
    }
}
