package br.dev.diego.controllers;

import br.dev.diego.entities.Produto;
import br.dev.diego.services.ProdutoService;
import br.dev.diego.services.impl.ProdutoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/buscar-produto")
public class BuscarProduto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        System.out.println(nome);
        ProdutoService service = new ProdutoServiceImpl();
        Optional<Produto> produto = service.buscarPorNome(nome);

        try (PrintWriter out = resp.getWriter()) {

            if (produto.isPresent()) {

                resp.setContentType("text/html;charset=UTF-8");

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Buscar Produto por nome</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Produto encontrado!</h1>");
                out.println("        <h3>Id: " + produto.get().getId() + "</h3>");
                out.println("        <h3>Nome: " + produto.get().getNome() + "</h3>");
                out.println("        <h3>Tipo: " + produto.get().getTipo() + "</h3>");
                out.println("        <h3>Valor: " + produto.get().getPreco() + "</h3>");
                out.println("    </body>");
                out.println("</html>");

            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Produto não localizado");
            }
        }


    }
}
