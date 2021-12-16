package org.example.daos;

import org.example.EntityHolder;
import org.example.Menu;
import org.example.Student;

import javax.persistence.TypedQuery;
import java.util.List;

public class StudentDao implements AdminDao<Student>{

    EntityHolder entity = new EntityHolder();
    Menu menu = new Menu();

    public StudentDao(){
        entity = new EntityHolder();
    }

    @Override
    public void add(Student student) {
        entity.em().getTransaction().begin();
        entity.em().persist(student);
        entity.em().getTransaction().commit();
    }

    @Override
    public void update(Student student) {
        entity.em().getTransaction().begin();
        entity.em().merge(student);
        entity.em().getTransaction().commit();
    }

    @Override
    public void delete(Student student) {
        entity.em().getTransaction().begin();
        entity.em().refresh(student);
        entity.em().getTransaction().commit();
    }

    @Override
    public List<Student> showAll() {
            TypedQuery<Student> query = entity.em().createQuery("SELECT s FROM Student s", Student.class);
            return query.getResultList();
    }

    @Override
    public void showInfo() {

    }


    @Override
    public Student getById(String output) {
        int id = menu.inputReadInteger(output);
        return entity.em().find(Student.class, id);
    }


}
