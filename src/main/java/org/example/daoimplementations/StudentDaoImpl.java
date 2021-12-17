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

    private void begin() {
        em.getTransaction().begin();
    }

    private void commit() {
        em.getTransaction().commit();
    }

    @Override
    public void add(Student student) {
        try {
            begin();
            em.persist(student);
            commit();
        } catch(Exception e ) {
            System.out.println("Wrong input, try again!");
        }

    }

    @Override
    public void update(Student student) {
        try {
            begin();
            em.merge(student);
            commit();
        } catch (Exception e) {
            System.out.println("Något gick fel vid inmatning av uppgifter, försök igen");
        }
    }

    @Override
    public void delete(Student student) {
        try {
        begin();
        em.refresh(student);
        commit();
        } catch (Exception e) {
            System.out.println("Wrong input, try again!");
        }
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
