package ru.tsystems.tproject.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * EntityManager class.
 */
public class Manager {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    private static final EntityManager em = emf.createEntityManager();

    public static EntityManager getEntityManager() {
        return em;
    }
}
