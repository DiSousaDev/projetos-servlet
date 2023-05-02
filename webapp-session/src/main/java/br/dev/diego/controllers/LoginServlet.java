package br.dev.diego.controllers;

import br.dev.diego.services.LoginService;
import br.dev.diego.services.impl.LoginServiceSessionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    private final static String USERNAME = "admin";
    private final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> username = auth.getUsername(req);

        if (username.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Gerenciamento de Sessão</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Bem vindo(a) " + username.get() + " iniciou a sessão com sucesso!</h1>");
                out.println("        <p><a href='" + req.getContextPath() + "/index.html'>Voltar</a></p>");
                out.println("        <p><a href='" + req.getContextPath() + "/logout'>Logout</a></p>");
                out.println("    </body>");
                out.println("</html>");

            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {

            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            req.setAttribute("title", req.getAttribute("title") + ": Login");
            resp.sendRedirect(req.getContextPath() + "/login.html");

        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não autorizado.");
        }
    }
}
