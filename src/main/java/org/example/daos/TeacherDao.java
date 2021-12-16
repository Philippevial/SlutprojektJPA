package org.example.daos;

import org.example.Menu;
import org.example.Student;
import org.example.Teacher;
import org.example.EntityHolder;

import java.util.List;

public class TeacherDao implements AdminDao<Teacher>{

    EntityHolder entity = new EntityHolder();
    Menu menu = new Menu();

    public TeacherDao() {
    }

    @Override
    public void add(Teacher teacher) {
        entity.em().getTransaction().begin();
        entity.em().persist(teacher);
        entity.em().getTransaction().commit();
    }

    @Override
    public void update(Teacher teacher) {
        entity.em().getTransaction().begin();
        entity.em().merge(teacher);
        entity.em().getTransaction().commit();
    }

    @Override
    public void delete(Teacher teacher) {
        entity.em().getTransaction().begin();
        entity.em().refresh(teacher);
        entity.em().getTransaction().commit();
    }

    @Override
    public List<Teacher> showAll() {
        return entity.em().createQuery("SELECT teacher FROM Teacher teacher", Teacher.class).getResultList();

    }

    @Override
    public void showInfo() {

    }

    @Override
    public Teacher getById(String output) {
        int id = menu.inputReadInteger(output);
        return entity.em().find(Teacher.class, id);
    }
}
