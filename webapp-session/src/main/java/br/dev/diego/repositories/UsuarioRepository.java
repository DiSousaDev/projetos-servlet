package br.dev.diego.repositories;

import br.dev.diego.entities.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends CrudRepository<Usuario> {

    Usuario buscarPorUsername(String username) throws SQLException;

}
