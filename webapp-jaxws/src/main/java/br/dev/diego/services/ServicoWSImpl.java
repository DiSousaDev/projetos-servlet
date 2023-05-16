package br.dev.diego.services;

import br.dev.diego.models.Curso;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.Arrays;
import java.util.List;

@WebService(endpointInterface = "br.dev.diego.services.ServicoWS")
public class ServicoWSImpl implements ServicoWS {

    private int contador;



    @Override
    @WebMethod
    public String saudar(String pessoa) {
        System.out.println("Imprimindo dentro do web service com instância: " + this);
        contador++;
        System.out.println("Valor do contador método saudar: " + contador);
        return "Olá usuário, " + pessoa + " tudo bem?";
    }

    @Override
    @WebMethod
    public List<Curso> listarCursos() {
        return Arrays.asList(
                new Curso("Java"),
                new Curso("JavaScript"),
                new Curso("Phyton"),
                new Curso("C#")
        );
    }

    @Override
    @WebMethod
    public Curso salvarCurso(Curso curso) {
        System.out.println("Salvando novo curso: " + curso);
        Curso novoCurso = new Curso(curso.getNome());
        return novoCurso;
    }
}
