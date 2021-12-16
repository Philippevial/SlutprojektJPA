package org.example.daoimplementations;

import org.example.Menu;
import org.example.daos.TeacherDao;
import org.example.entities.Teacher;

import javax.persistence.*;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Slutprojekt");
    EntityManager em = emf.createEntityManager();
    Menu menu = new Menu();

    public TeacherDaoImpl() {
    }

    @Override
    public void add(Teacher teacher) {
        em.getTransaction().begin();
        em.persist(teacher);
        em.getTransaction().commit();
    }

    @Override
    public void update(Teacher teacher) {
        em.getTransaction().begin();
        em.merge(teacher);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Teacher teacher) {
        em.getTransaction().begin();
        em.remove(teacher);
        em.getTransaction().commit();
    }

    @Override
    public List<Teacher> showAll() {
        return em.createQuery("SELECT teacher FROM Teacher teacher", Teacher.class).getResultList();
    }

    @Override
    public Teacher getById(String output) {
        int id = menu.inputReadInteger(output);
        return em.find(Teacher.class, id);
    }

    @Override
    public void numberOfTeachersInSchool() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(t) AS Teachers FROM Teacher t", Long.class);
        System.out.println("Numbers of teachers in school: " + query.getResultList());
    }

    @Override
    public void numberOfTeachersInCourse(int courseId) {
        Query query = em.createNativeQuery("SELECT COUNT(teacherList_teacherID) FROM course_teacher" +
                " WHERE Course_courseId = :courseId");
        query.setParameter("courseId", courseId);
        System.out.println("Number of teachers in course: "+courseId+" is: "+query.getResultList());
    }
}
