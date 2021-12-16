package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityHolder {
    EntityManagerFactory emf;
    EntityManager em;

    public EntityHolder() {
        this.emf = Persistence.createEntityManagerFactory("Slutprojekt");
        this.em = emf.createEntityManager();
    }

    public EntityManager em() {
        return em;
    }
}
