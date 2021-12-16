package org.example.daoimplementations;

import org.example.Menu;
import org.example.daos.StudentDao;
import org.example.entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
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
    public void showInfo() {

    }


    @Override
    public Student getById(String output) {
        int id = menu.inputReadInteger(output);
        return em.find(Student.class, id);
    }

    public Student personInfoInput() {
        Menu menu = new Menu();
        Student student = new Student();

        student.setFirstName(menu.inputReadString("First name: "));
        student.setLastName(menu.inputReadString("Last name: "));
        student.setSsn(menu.inputReadString("ssn(12 numbers): "));

        while (student.getSsn().length() != 12)
            student.setSsn(menu.inputReadString("Wrong input try again"));
        return student;
    }

    public Student updateInfo() {
        Menu menu = new Menu();

        Student newStudent = getById("Enter student ID to update values: ");
        newStudent.setFirstName(menu.inputReadString("Firstname: "));
        newStudent.setLastName(menu.inputReadString("Lastname: "));
        newStudent.setSsn(menu.inputReadString("SSN: "));

        return newStudent;
    }

}
