package org.example.daoimplementations;

import org.example.daos.CourseDao;
import org.example.entities.Course;
import org.example.Menu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Slutprojekt");
    EntityManager em = emf.createEntityManager();

    Menu menu = new Menu();

    @Override
    public void add(Course course) {
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();
    }

    @Override
    public void update(Course course) {
        em.getTransaction().begin();
        em.merge(course);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Course course) {
        em.getTransaction().begin();
        em.remove(course);
        em.getTransaction().commit();
    }

    @Override
    public List<Course> showAll() {
        return em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
    }

    @Override
    public void showInfo() {

    }

    @Override
    public Course getById(String output) {
        int id = menu.inputReadInteger(output);
        return em.find(Course.class, id);
    }

    public List<Course> numberOfCoursesInSchool() {
        TypedQuery<Course> query = em.createQuery("SELECT c.courseID, COUNT(c)" +
                "AS Courses FROM Course c",Course.class);

        return query.getResultList();
    }
}
