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
import java.util.List;

@WebServlet({"/produtos.xls", "/produtos.html"})
public class ProdutoXlsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProdutoService service = new ProdutoServiceImpl();
        List<Produto> produtos = service.listarProdutos();

        resp.setContentType("text/html;charset=UTF-8");
        String servletPath = req.getServletPath();
        boolean isXls = servletPath.endsWith(".xls");

        if (isXls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=produtos.xls");
        }

        try (PrintWriter out = resp.getWriter()) {

            if (!isXls) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Lista de Produtos</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Lista de Produtos</h1>");
                out.println("        <p><a href=\"" + req.getContextPath() + "/produtos.xls" + "\">Exportar XLS</a></p>");
                out.println("        <p><a href=\"" + req.getContextPath() + "/produtos.json" + "\">Exportar JSON</a></p>");
            }
            out.println("        <table>");
            out.println("        <tr>");
            out.println("           <th>Id</th>");
            out.println("           <th>Produto</th>");
            out.println("           <th>Tipo</th>");
            out.println("           <th>Preço</th>");
            out.println("        </tr>");
            produtos.forEach(produto -> {
                out.println("        <tr>");
                out.println("           <td>" + produto.getId() + "</td>");
                out.println("           <td>" + produto.getNome() + "</td>");
                out.println("           <td>" + produto.getTipo() + "</td>");
                out.println("           <td>" + produto.getPreco() + "</td>");
                out.println("        </tr>");
            });
            out.println("        </table>");
            if (!isXls) {
                out.println("    </body>");
                out.println("</html>");
            }
        }
    }

}
