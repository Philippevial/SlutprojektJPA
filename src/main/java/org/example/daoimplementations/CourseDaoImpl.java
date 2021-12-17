package org.example.daoimplementations;

import org.example.daos.CourseDao;
import org.example.entities.Course;
import org.example.Menu;

import javax.persistence.*;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
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
    public void add(Course course) {
        try {
            begin();
            em.persist(course);
            commit();
        } catch (Exception e) {
            System.out.println("Något gick fel vid inmatning av uppgifter, försök igen");
        }
    }

    @Override
    public void update(Course course) {
        try {
            begin();
            em.merge(course);
            commit();
        } catch (Exception e) {
            System.out.println("Wrong input, try again!");
        }
    }

    @Override
    public void delete(Course course) {
        try {
            begin();
            em.refresh(course);
            commit();
        } catch (Exception e) {
            System.out.println("Wrong input, try again!");
        }
    }

    @Override
    public List<Course> showAll() {
        return em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
    }

    @Override
    public Course getById(String output) {
        int id = menu.inputReadInteger(output);
        return em.find(Course.class, id);
    }

    @Override
    public void numberOfCoursesInSchool() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) AS Courses FROM Course c", Long.class);
        System.out.println("Numbers of courses in school: " + query.getResultList());
    }

    @Override
    public void numberOfCoursesInProgramme(int progId) {
        Query query = em.createNativeQuery("SELECT COUNT(courseList_courseID) FROM programme_course" +
               " WHERE Programme_progId = :progId");
        query.setParameter("progId", progId);
        System.out.println("Number of courses in programme: "+progId+" is: "+ query.getResultList());
    }


}
