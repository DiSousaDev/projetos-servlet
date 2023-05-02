package br.dev.diego.filters;

import br.dev.diego.services.exceptions.ConnectionJdbcException;
import br.dev.diego.services.exceptions.ServerJdbcException;
import br.dev.diego.util.DBConnectionDSUtil;
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

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try (Connection conn = DBConnectionDSUtil.getConnection()) {

            if(conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try {
                servletRequest.setAttribute("conn", conn);
                filterChain.doFilter(servletRequest, servletResponse);
                conn.commit();
            } catch (SQLException | ServerJdbcException e) {
                conn.rollback();
                ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException | ConnectionJdbcException e) {
            e.printStackTrace();
        }

    }
}
