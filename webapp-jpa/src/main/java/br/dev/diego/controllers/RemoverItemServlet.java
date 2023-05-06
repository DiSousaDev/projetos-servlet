package br.dev.diego.controllers;

import br.dev.diego.config.ProdutoServicePrincipal;
import br.dev.diego.entities.ItemPedido;
import br.dev.diego.entities.Pedido;
import br.dev.diego.entities.Produto;
import br.dev.diego.services.ProdutoService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/remover-item")
public class RemoverItemServlet extends HttpServlet {

    @Inject
    private Pedido pedido;
    @Inject
    @ProdutoServicePrincipal
    private ProdutoService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        Optional<Produto> produtoOptional = service.buscarPorId(id);

        if (produtoOptional.isPresent()) {
            ItemPedido itemPedido = new ItemPedido(1, produtoOptional.get());
            //HttpSession session = req.getSession();
            //Pedido pedido = (Pedido) session.getAttribute("pedido");
            pedido.removerItem(itemPedido);
        }
        resp.sendRedirect(req.getContextPath() + "/ver-carrinho");
    }
}

