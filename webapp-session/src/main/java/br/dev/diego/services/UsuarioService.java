package br.dev.diego.services;

import br.dev.diego.entities.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> login(String username, String password);

}
