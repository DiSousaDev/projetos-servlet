package br.dev.diego.listners;

import br.dev.diego.entities.Pedido;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class AppListner implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Iniciando a aplicação.");
        System.out.println("Iniciando a aplicação.");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("att1", "Algum valor disponivel no ciclo de vida da aplicação.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Finalizando a aplicação.");
        servletContext.log("Finalizando a aplicação.");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("Iniciando request.");
        servletContext.log("Iniciando request.");
        sre.getServletContext().setAttribute("att2", "Algum valor disponivel no ciclo de vida da request.");
        sre.getServletRequest().setAttribute("title", "Catalogo Servlet");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("Finalizando request.");
        servletContext.log("Finalizando request.");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Criando sessão http.");
        servletContext.log("Criando sessão http.");
        se.getSession().setAttribute("atributo", "Algum valor disponivel no ciclo de vida da sessão.");
        Pedido pedido = new Pedido();
        se.getSession().setAttribute("pedido", pedido);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Finalizando sessão http.");
        servletContext.log("Finalizando sessão http.");
    }
}
