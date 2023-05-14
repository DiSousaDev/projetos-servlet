package br.dev.diego.controllers;

import br.dev.diego.entities.Categoria;
import br.dev.diego.entities.Produto;
import br.dev.diego.services.ProdutoService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;
import java.util.ResourceBundle;

@Model
public class ProdutoController {

    private Produto produto;
    private Long id;

    @Inject
    private FacesContext facesContext;

    @Inject
    private ProdutoService service;

    @Inject
    private ResourceBundle bundle;

    private List<Produto> listarProdutos;

    private String textoPesquisar;

    @PostConstruct
    public void init() {
        this.listarProdutos = service.buscarTodos();
    }

    @Produces
    @Model
    public String titulo() {
        return bundle.getString("produto.texto.titulo");
    }

//    @Produces
//    @RequestScoped
//    @Named("listarProdutos")
//    public List<Produto> findAll() {
//        List<Produto> produtos = service.buscarTodos();
//        System.out.println("FIND ALL");
//        produtos.forEach(System.out::println);
//        return produtos;
//    }

    @Produces
    @Model
    @Named("categorias")
    public List<Categoria> findAllCategories() {
        List<Categoria> categorias = service.buscarTodasCategorias();
        System.out.println("FIND ALL CATEGORIES");
        return categorias;
    }

    @Produces
    @Model
    public Produto produto() {
        this.produto = new Produto();
        if(this.id != null && this.id > 0) {
            service.buscarPorId(this.id).ifPresent(p -> {
                this.produto = p;
            });
        }
        return this.produto;
    }

    public String salvar() {
        if(produto.getId() != null && produto.getId() > 0) {
            facesContext.addMessage(null, new FacesMessage(String.format(bundle.getString("produto.mensagem.editar"), produto.getNome())));
        } else {
            facesContext.addMessage(null, new FacesMessage(String.format(bundle.getString("produto.mensagem.criar"), produto.getNome())));
        }
        service.salvar(produto);
        atualizaListaProdutos();
        return "index.xhtml";
    }

    public String editar(Long id) {
        this.id = id;
        return "form.xhtml";
    }

    public void excluir(Produto produto) {
        service.excluir(produto.getId());
        facesContext.addMessage(null, new FacesMessage(String.format(bundle.getString("produto.mensagem.excluir"), produto.getNome())));
        atualizaListaProdutos();
    }

    public void buscarPorNome() {
        this.listarProdutos = service.buscarPorNome(this.textoPesquisar);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Produto> getListarProdutos() {
        return listarProdutos;
    }

    public void setListarProdutos(List<Produto> listarProdutos) {
        this.listarProdutos = listarProdutos;
    }

    private void atualizaListaProdutos() {
        this.listarProdutos = service.buscarTodos();
    }

    public String getTextoPesquisar() {
        return textoPesquisar;
    }

    public void setTextoPesquisar(String textoPesquisar) {
        this.textoPesquisar = textoPesquisar;
    }
}
