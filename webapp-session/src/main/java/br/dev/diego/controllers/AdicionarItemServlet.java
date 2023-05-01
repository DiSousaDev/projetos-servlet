package br.dev.diego.controllers;

import br.dev.diego.entities.ItemPedido;
import br.dev.diego.entities.Pedido;
import br.dev.diego.entities.Produto;
import br.dev.diego.services.ProdutoService;
import br.dev.diego.services.impl.ProdutoServiceJdbcImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/adicionar-item")
public class AdicionarItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        Connection conn = (Connection) req.getAttribute("conn");
        ProdutoService service = new ProdutoServiceJdbcImpl(conn);
        Optional<Produto> produtoOptional = service.buscarPorId(id);

        if (produtoOptional.isPresent()) {
            ItemPedido itemPedido = new ItemPedido(1, produtoOptional.get());
            HttpSession session = req.getSession();
            Pedido pedido = (Pedido) session.getAttribute("pedido");
            pedido.adicionarItem(itemPedido);
        }
        resp.sendRedirect(req.getContextPath() + "/ver-carrinho");

    }
}
