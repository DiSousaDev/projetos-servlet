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

@WebServlet("/remover-item")
public class RemoverItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        ProdutoService produtoService = new ProdutoServiceImpl();
        Optional<Produto> produtoOptional = produtoService.buscarPorId(id);
        if (produtoOptional.isPresent()) {
            ItemPedido itemPedido = new ItemPedido(1, produtoOptional.get());
            HttpSession session = req.getSession();
            Pedido pedido = (Pedido) session.getAttribute("pedido");
            pedido.removerItem(itemPedido);
        }
        resp.sendRedirect(req.getContextPath() + "/ver-carrinho");
    }
}

