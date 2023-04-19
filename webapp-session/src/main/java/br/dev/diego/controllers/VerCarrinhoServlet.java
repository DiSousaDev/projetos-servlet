package br.dev.diego.controllers;

import br.dev.diego.entities.ItemPedido;
import br.dev.diego.entities.Pedido;
import br.dev.diego.entities.Produto;
import br.dev.diego.services.ProdutoService;
import br.dev.diego.services.impl.ProdutoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/ver-carrinho")
public class VerCarrinhoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/carrinho.jsp").forward(req, resp);

    }
}
