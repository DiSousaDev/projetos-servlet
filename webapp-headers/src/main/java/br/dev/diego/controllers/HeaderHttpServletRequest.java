package br.dev.diego.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/headers-request")
public class HeaderHttpServletRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String metodoHttp = req.getMethod();
        String requestURI = req.getRequestURI();
        String requestURL = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String ipClient = req.getRemoteAddr();
        String ipLocal = req.getLocalAddr();
        int port = req.getLocalPort();
        String scheme = req.getScheme();
        String host = req.getHeader("host");
        String url = scheme + "://" + host + contextPath + servletPath;
        String url2 = scheme + "://" + ipLocal + ":" + port + contextPath + servletPath;

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Headers Http Request</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Headers Http Request!</h1>");
            out.println("        <ul>");
            out.println("           <li>Método Http: " + metodoHttp + "</li>");
            out.println("           <li>Request URI: " + requestURI + "</li>");
            out.println("           <li>Request URL: " + requestURL + "</li>");
            out.println("           <li>Context Path: " + contextPath + "</li>");
            out.println("           <li>Servlet Path: " + servletPath + "</li>");
            out.println("           <li>IP Client: " + ipClient + "</li>");
            out.println("           <li>IP Local: " + ipLocal + "</li>");
            out.println("           <li>Porta: " + port + "</li>");
            out.println("           <li>Scheme: " + scheme + "</li>");
            out.println("           <li>Host: " + host + "</li>");
            out.println("           <li>URL: " + url + "</li>");
            out.println("           <li>URL2: " + url2 + "</li>");

            Enumeration<String> headerNames = req.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String header = headerNames.nextElement();
                out.println("       <li>" + header + ": " + req.getHeader(header) + "</li>");
            }

            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");

        }
    }

}