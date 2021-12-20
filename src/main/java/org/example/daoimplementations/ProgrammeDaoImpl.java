package org.example.daoimplementations;

import org.example.*;
import org.example.daos.ProgrammeDao;
import org.example.entities.Programme;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProgrammeDaoImpl implements ProgrammeDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Slutprojekt");
    EntityManager em = emf.createEntityManager();

    Menu menu = new Menu();

    private void begin() {
        em.getTransaction().begin();
    }

    private void commit() {
        em.getTransaction().commit();
    }

    @Override
    public void add(Programme programme) {
        try {
            begin();
            em.persist(programme);
            commit();
        } catch (Exception e) {
            System.out.println("Wrong input, try again!");
        }
    }

    @Override
    public void update(Programme programme) {
        try {
            begin();
            em.merge(programme);
            commit();
        } catch (Exception e) {
            System.out.println("Wrong input, try again!");
        }
    }

    @Override
    public void delete(Programme programme) {
        try {
            begin();
            em.refresh(programme);
            commit();
        } catch (Exception e) {
            System.out.println("Wrong input, try again!");
        }
    }

    @Override
    public List<Programme> showAll() {
        return em.createQuery("SELECT p FROM Programme p", Programme.class).getResultList();
    }



    @Override
    public Programme getById(String output) {
        int id = menu.inputReadInteger(output);
        return em.find(Programme.class, id);
    }



}
