package ru.tsystems.tproject.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by german on 18.10.14.
 */
public class Manager {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    private static EntityManager em = emf.createEntityManager();

    public static EntityManager getEntityManager() {
        return em;
    }
}
