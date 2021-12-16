package org.example.daos;

import org.example.*;
import java.util.List;

public class ProgrammeDao implements AdminDao<Programme>{
    EntityHolder entity = new EntityHolder();
    Menu menu = new Menu();

    @Override
    public void add(Programme programme) {
        entity.em().getTransaction().begin();
        entity.em().persist(programme);
        entity.em().getTransaction().commit();
    }

    @Override
    public void update(Programme programme) {
        entity.em().getTransaction().begin();
        entity.em().merge(programme);
        entity.em().getTransaction().commit();
    }

    @Override
    public void delete(Programme programme) {
        entity.em().getTransaction().begin();
        entity.em().remove(programme);
        entity.em().getTransaction().commit();
    }

    @Override
    public List<Programme> showAll() {
        return entity.em().createQuery("SELECT p FROM Programme p", Programme.class).getResultList();
    }

    @Override
    public void showInfo() {

    }

    @Override
    public Programme getById(String output) {
        int id = menu.inputReadInteger(output);
        return entity.em().find(Programme.class, id);
    }

}
