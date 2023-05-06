package br.dev.diego.util;

import br.dev.diego.services.exceptions.ConnectionJdbcException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionDSUtil {

    public static Connection getConnection() throws SQLException {
        Context initContext;
        DataSource ds;
        try {
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/TestDB");
            return ds.getConnection();
        } catch (NamingException e) {
            throw new ConnectionJdbcException("Erro ao obter conexão, " + e.getMessage());
        }
    }


}
