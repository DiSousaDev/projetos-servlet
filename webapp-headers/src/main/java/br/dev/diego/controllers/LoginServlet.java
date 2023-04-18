package br.dev.diego.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final static String USERNAME = "admin";
    private final static String PASSWORD = "12345";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (!USERNAME.equals(username) || !PASSWORD.equals(password)) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não autorizado.");
        } else {

            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Login com sucesso</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Login efetuado com sucesso!</h1>");
                out.println("        <h3>Seja bem vindo " + username + "!</h3>");
                out.println("    </body>");
                out.println("</html>");
                resp.sendRedirect(req.getContextPath() + "/menu.html");
            }
        }
    }
}
