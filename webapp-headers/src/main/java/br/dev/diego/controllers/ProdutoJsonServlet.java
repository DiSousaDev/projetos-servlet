package br.dev.diego.controllers;

import br.dev.diego.entities.Produto;
import br.dev.diego.services.ProdutoService;
import br.dev.diego.services.impl.ProdutoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/produtos.json")
public class ProdutoJsonServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Produto produto = mapper.readValue(jsonStream, Produto.class);

        resp.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = resp.getWriter()){

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("    <head>");
        out.println("        <meta charset=\"UTF-8\">");
        out.println("        <title>Obter produto do Json</title>");
        out.println("    </head>");
        out.println("    <body>");
        out.println("        <h1>Obter produto do Json!</h1>");
        out.println("        <ul>");
        out.println("           <li>Id: " + produto.getId() + "</li>");
        out.println("           <li>Produto: " + produto.getNome() + "</li>");
        out.println("           <li>Tipo: " + produto.getTipo() + "</li>");
        out.println("           <li>Preço: " + produto.getPreco() + "</li>");
        out.println("        </ul>");
        out.println("    </body>");
        out.println("</html>");

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProdutoService service = new ProdutoServiceImpl();
        List<Produto> produtos = service.listarProdutos();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(produtos);
        resp.setContentType("application/json");
        resp.getWriter().write(json);

    }

}
