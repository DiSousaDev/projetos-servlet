package br.dev.diego.controllers;

import br.dev.diego.entities.Produto;
import br.dev.diego.services.LoginService;
import br.dev.diego.services.ProdutoService;
import br.dev.diego.services.impl.LoginServiceSessionImpl;
import br.dev.diego.services.impl.ProdutoServiceJdbcImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/produtos.html", "/produtos"})
public class ProdutoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProdutoService service = new ProdutoServiceJdbcImpl(conn);
        List<Produto> produtos = service.listarProdutos();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> username = auth.getUsername(req);

        req.setAttribute("produtos", produtos);
        req.setAttribute("username", username);

        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);

    }

}
