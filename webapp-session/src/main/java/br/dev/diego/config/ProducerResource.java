package br.dev.diego.config;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@ApplicationScoped
public class ProducerResource {

    @Resource(name = "jdbc/TestDB")
    private DataSource ds;

    @Inject
    private Logger log;

    @Produces
    @RequestScoped
    @MySqlConn
    private Connection beanConnection() throws SQLException {
//        Context initContext;
//        DataSource ds;
//        try {
//            initContext = new InitialContext();
//            Context envContext = (Context) initContext.lookup("java:/comp/env");
//            ds = (DataSource) envContext.lookup("jdbc/TestDB");
        return ds.getConnection();
//        } catch (NamingException e) {
//            throw new ConnectionJdbcException("Erro ao obter conexão, " + e.getMessage());
//        }
    }

    @Produces
    public Logger beanLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    public void close(@Disposes @MySqlConn Connection connection) throws SQLException {
        connection.close();
        log.info("Encerranco conexão BDD mysql!");
    }

}
