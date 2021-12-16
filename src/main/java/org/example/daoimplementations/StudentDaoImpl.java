package org.example.daoimplementations;

import org.example.Menu;
import org.example.daos.StudentDao;
import org.example.entities.Student;

import javax.persistence.*;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Slutprojekt");
    EntityManager em = emf.createEntityManager();
    Menu menu = new Menu();

    public StudentDaoImpl(){
    }

    @Override
    public void add(Student student) {
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
    }

    @Override
    public void update(Student student) {
        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Student student) {
        em.getTransaction().begin();
        em.refresh(student);
        em.getTransaction().commit();
    }

    @Override
    public List<Student> showAll() {
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
            return query.getResultList();
    }


    @Override
    public Student getById(String output) {
        int id = menu.inputReadInteger(output);
        return em.find(Student.class, id);
    }

    @Override
    public void numberOfStudentsInSchool() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(s) AS Students FROM Student s", Long.class);
        System.out.println("Number of students in school: " + query.getResultList());
    }

    @Override
    public void numberOfStudentsInProgramme(int progID) {
        Query query = em.createNativeQuery("SELECT COUNT(studentList_studentId) FROM programme_student WHERE Programme_progId = :progID");
        query.setParameter("progID", progID);
        System.out.println("Number of students in programme: "+ progID +" is: "+ query.getResultList());
    }
}
