package br.dev.diego.filters;

import br.dev.diego.config.MySqlConn;
import br.dev.diego.services.exceptions.ConnectionJdbcException;
import br.dev.diego.services.exceptions.ServerJdbcException;
import jakarta.inject.Inject;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConnectionFilter implements Filter {

    @Inject
    @MySqlConn
    private Connection conn;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

       /* try {
            Connection connRequest = this.conn;
            if(connRequest.getAutoCommit()) {
                connRequest.setAutoCommit(false);
            } */

            try {
            //    servletRequest.setAttribute("conn", connRequest);
                filterChain.doFilter(servletRequest, servletResponse);
                //connRequest.commit();
            } catch (ServerJdbcException e) {
               // connRequest.rollback();
                ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
 /*       } catch (SQLException | ConnectionJdbcException e) {
            e.printStackTrace();
        }*/

    }
}
