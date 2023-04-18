package br.dev.diego.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    private final static String USERNAME = "admin";
    private final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        Optional<Cookie> cookieOptional = Arrays.stream(cookies).filter(c -> "username".equals(c.getName())).findAny();

        if(cookieOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = resp.getWriter()){

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Bem vindo(a) " + cookieOptional.get().getValue() +"!</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Bem vindo(a) " + cookieOptional.get().getValue() +"!</h1>");
            out.println("    </body>");
            out.println("</html>");

            }
        }

        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {

            Cookie usernameCookie = new Cookie("username", username);
            resp.addCookie(usernameCookie);
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
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não autorizado.");
        }
    }
}
