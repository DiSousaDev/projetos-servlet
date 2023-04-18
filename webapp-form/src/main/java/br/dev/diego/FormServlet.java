package br.dev.diego;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cadastrar")
public class FormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String pais = req.getParameter("pais");
        String[] linguagens = req.getParameterValues("linguagens");
        String[] roles = req.getParameterValues("roles");
        String idioma = req.getParameter("idioma");
        boolean habilitar = req.getParameter("habilitar") != null &&
                req.getParameter("habilitar").equals("on");
        String secret = req.getParameter("secret");

        Map<String, String> errors = new HashMap<>();

        if (username == null || username.isBlank()) {
            errors.put("username", "Nome deve ser preenchido.");
        }
        if (password == null || password.isBlank()) {
            errors.put("password", "Password deve ser preenchido.");
        }
        if (email == null || email.isBlank()) {
            errors.put("email", "E-mail deve ser preenchido.");
        }
        if (pais == null || pais.isBlank()) {
            errors.put("pais", "Selecione um País.");
        }
        if (linguagens == null || linguagens.length == 0) {
            errors.put("linguagens", "Selecione uma Linguagem de Programação.");
        }
        if (roles == null || roles.length == 0) {
            errors.put("roles", "Selecione uma Role.");
        }
        if (idioma == null) {
            errors.put("idioma", "Selecione um idioma.");
        }

        if (errors.isEmpty()) {
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Cadastro de Usuários</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Cadastro de Usuários</h1>");
                out.println("        <ul>");
                out.println("           <li>Username: " + username + "</li>");
                out.println("           <li>Password: " + password + "</li>");
                out.println("           <li>E-mail: " + email + "</li>");
                out.println("           <li>País: " + pais + "</li>");
                out.println("           <li>Linguagens:<ul>");
                Arrays.asList(linguagens).forEach(s -> {
                    out.println("               <li>" + s + "</li>");
                });
                out.println("           </ul></li>");
                out.println("           <li>Roles:<ul>");
                Arrays.asList(roles).forEach(s -> {
                    out.println("               <li>" + s + "</li>");
                });
                out.println("           </ul></li>");
                out.println("           <li>Idioma: " + idioma + "</li>");
                out.println("           <li>Habilitar: " + habilitar + "</li>");
                out.println("           <li>Secret: " + secret + "</li>");
                out.println("        </ul>");
                out.println("    </body>");
                out.println("</html>");
            }
        } else {
                /*errors.forEach(erro -> {
                    out.println("<li>" + erro + "</li>");
                });
                out.println("<p><a href=\"/webapp-form/index.html\">Voltar</a></p>"); */
            req.setAttribute("errors", errors);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}