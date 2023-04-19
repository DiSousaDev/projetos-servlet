package br.dev.diego.controllers;

import br.dev.diego.entities.Produto;
import br.dev.diego.services.LoginService;
import br.dev.diego.services.ProdutoService;
import br.dev.diego.services.impl.LoginServiceSessionImpl;
import br.dev.diego.services.impl.ProdutoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/produtos.html", "/produtos"})
public class ProdutoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProdutoService service = new ProdutoServiceImpl();
        List<Produto> produtos = service.listarProdutos();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> username = auth.getUsername(req);

        String atributoApp = (String) getServletContext().getAttribute("att1");
        String atributoRequest = (String) req.getAttribute("att2");

        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Lista de Produtos</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Lista de Produtos</h1>");
            out.println("        <h3>Atributo App: " + atributoApp +"</h3>");
            out.println("        <h3>Atributo request: " + atributoRequest +"</h3>");
            if(username.isPresent()) {
            out.println("        <div style='color:blue;'><h3>Olá " + username.get() + " bem vindo(a).</div></h3>");
            }
            out.println("        <table>");
            out.println("        <tr>");
            out.println("           <th>Id</th>");
            out.println("           <th>Produto</th>");
            out.println("           <th>Tipo</th>");
            if (username.isPresent()) {
                out.println("           <th>Preço</th>");
                out.println("           <th>Comprar</th>");
            }
            out.println("        </tr>");
            produtos.forEach(produto -> {
                out.println("        <tr>");
                out.println("           <td>" + produto.getId() + "</td>");
                out.println("           <td>" + produto.getNome() + "</td>");
                out.println("           <td>" + produto.getTipo() + "</td>");
                if (username.isPresent()) {
                    out.println("           <td>" + produto.getPreco() + "</td>");
                    out.println("           <td><a href=\"" + req.getContextPath() + "/adicionar-item?id=" + produto.getId() + "\">Adicionar ao carrinho</a></td>");
                }
                out.println("        </tr>");
            });
            out.println("        </table>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

}
