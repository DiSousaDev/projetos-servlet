package br.dev.diego.filters;

import br.dev.diego.services.LoginService;
import br.dev.diego.services.impl.LoginServiceSessionImpl;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/ver-carrinho", "/adicionar-item"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginService loginService = new LoginServiceSessionImpl();
        Optional<String> user = loginService.getUsername((HttpServletRequest) request);

        if (user.isPresent()) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não autorizado.");
        }

    }
}
