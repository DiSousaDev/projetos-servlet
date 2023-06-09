package br.dev.diego.services.impl;

import br.dev.diego.config.LoginServicePrincipal;
import br.dev.diego.services.LoginService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@ApplicationScoped
@LoginServicePrincipal
public class LoginServiceSessionImpl implements LoginService {

    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return Optional.empty();
        }

        return Optional.of(username);
    }
}
