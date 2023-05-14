package br.dev.diego.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Named
@SessionScoped
public class IdiomaController implements Serializable {

    private static final Long serialVersionUID = 12231231L;

    private Locale locale;
    private String idioma;
    private Map<String, String> idiomasCompativeis;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        idiomasCompativeis = new HashMap<>();
        idiomasCompativeis.put("Ingles", "en");
        idiomasCompativeis.put("Portugues", "pt-br");
    }

    public void selecionarIdioma(ValueChangeEvent event) {
        String novo = event.getNewValue().toString();

        idiomasCompativeis.values().forEach(idioma -> {
           if(idioma.equals(novo)) {
               this.locale = new Locale(novo);
               FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale);
           }
        });

    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Map<String, String> getIdiomasCompativeis() {
        return idiomasCompativeis;
    }

    public void setIdiomasCompativeis(Map<String, String> idiomasCompativeis) {
        this.idiomasCompativeis = idiomasCompativeis;
    }
}
