package br.dev.diego.listners;

import br.dev.diego.entities.Pedido;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class AppListner implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Iniciando a aplicação.");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("att1", "Algum valor disponivel no ciclo de vida da aplicação.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Finalizando a aplicação.");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("Iniciando request.");
        sre.getServletRequest().setAttribute("att1", "Algum valor disponivel no ciclo de vida da request.");
        sre.getServletRequest().setAttribute("title", "Catalogo Servlet");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Finalizando request.");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Criando sessão http.");
//        Pedido pedido = new Pedido();
//        HttpSession session = se.getSession();
//        session.setAttribute("pedido", pedido);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Finalizando sessão http.");
    }
}
