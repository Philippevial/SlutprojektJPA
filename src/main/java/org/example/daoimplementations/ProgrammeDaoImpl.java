package org.example.daoimplementations;

import org.example.*;
import org.example.daos.ProgrammeDao;
import org.example.daos.StudentDao;
import org.example.entities.Programme;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProgrammeDaoImpl implements ProgrammeDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Slutprojekt");
    EntityManager em = emf.createEntityManager();

    Menu menu = new Menu();

    @Override
    public void add(Programme programme) {
        em.getTransaction().begin();
        em.persist(programme);
        em.getTransaction().commit();
    }

    @Override
    public void update(Programme programme) {
        em.getTransaction().begin();
        em.merge(programme);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Programme programme) {
        em.getTransaction().begin();
        em.remove(programme);
        em.getTransaction().commit();
    }

    @Override
    public List<Programme> showAll() {
        return em.createQuery("SELECT p FROM Programme p", Programme.class).getResultList();
    }

    @Override
    public void showInfo() {

    }

    @Override
    public Programme getById(String output) {
        int id = menu.inputReadInteger(output);
        return em.find(Programme.class, id);
    }



}
