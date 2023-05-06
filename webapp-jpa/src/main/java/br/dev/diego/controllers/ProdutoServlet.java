package br.dev.diego.controllers;

import br.dev.diego.config.LoginServicePrincipal;
import br.dev.diego.config.ProdutoServicePrincipal;
import br.dev.diego.entities.Produto;
import br.dev.diego.services.LoginService;
import br.dev.diego.services.ProdutoService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet({"/produtos.html", "/produtos"})
public class ProdutoServlet extends HttpServlet {

    @Inject
    @ProdutoServicePrincipal
    private ProdutoService service;
    @Inject
    @LoginServicePrincipal
    private LoginService auth;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Produto> produtos = service.listarProdutos();

        Optional<String> username = auth.getUsername(req);

        req.setAttribute("produtos", produtos);
        req.setAttribute("username", username);
        req.setAttribute("title", req.getAttribute("title") + ": Listando produtos");

        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);

    }

}
