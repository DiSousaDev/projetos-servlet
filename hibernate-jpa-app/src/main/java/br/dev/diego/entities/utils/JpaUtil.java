package br.dev.diego.entities.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {

    private static final EntityManagerFactory emf = buildEntityManager();

    private static EntityManagerFactory buildEntityManager() {
        return Persistence.createEntityManagerFactory("exemploJPA");
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
