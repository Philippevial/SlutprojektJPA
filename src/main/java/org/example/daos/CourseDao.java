package org.example.daos;

import org.example.Course;
import org.example.EntityHolder;
import org.example.Menu;

import java.util.List;

public class CourseDao implements AdminDao<Course>{
    EntityHolder entity = new EntityHolder();
    Menu menu = new Menu();

    @Override
    public void add(Course course) {
        entity.em().getTransaction().begin();
        entity.em().persist(course);
        entity.em().getTransaction().commit();
    }

    @Override
    public void update(Course course) {
        entity.em().getTransaction().begin();
        entity.em().merge(course);
        entity.em().getTransaction().commit();
    }

    @Override
    public void delete(Course course) {
        entity.em().getTransaction().begin();
        entity.em().remove(course);
        entity.em().getTransaction().commit();
    }

    @Override
    public List<Course> showAll() {
        return entity.em().createQuery("SELECT c FROM Course c", Course.class).getResultList();
    }

    @Override
    public void showInfo() {

    }

    @Override
    public Course getById(String output) {
        int id = menu.inputReadInteger(output);
        return entity.em().find(Course.class, id);
    }

}
