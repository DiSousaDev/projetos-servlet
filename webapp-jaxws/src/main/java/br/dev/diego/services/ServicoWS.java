package br.dev.diego.services;

import br.dev.diego.models.Curso;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface ServicoWS {

    String saudar(String pessoa);
    List<Curso> listarCursos();
    Curso salvarCurso(Curso curso);

}
