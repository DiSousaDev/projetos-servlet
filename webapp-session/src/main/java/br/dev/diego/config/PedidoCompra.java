package br.dev.diego.config;

import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Named
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Stereotype
@SessionScoped
public @interface PedidoCompra {

}
