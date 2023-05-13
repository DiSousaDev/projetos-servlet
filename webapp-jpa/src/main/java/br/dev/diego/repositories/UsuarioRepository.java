package br.dev.diego.repositories;

import br.dev.diego.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario> {

    Usuario buscarPorUsername(String username) throws Exception;

}
