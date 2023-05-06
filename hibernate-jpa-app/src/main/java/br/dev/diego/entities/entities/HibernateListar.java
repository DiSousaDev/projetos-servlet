package br.dev.diego.entities.entities;

import br.dev.diego.entities.utils.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class HibernateListar {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        List cliente = em.createQuery("select c from Cliente c").getResultList();

        cliente.forEach(System.out::println);

        em.close();

    }

}
