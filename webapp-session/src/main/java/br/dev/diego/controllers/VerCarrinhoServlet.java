package br.dev.diego.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ver-carrinho")
public class VerCarrinhoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", req.getAttribute("title") + ": Visualizar carrinho");
        getServletContext().getRequestDispatcher("/carrinho.jsp").forward(req, resp);

    }
}
