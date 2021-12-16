package org.example.daos;

import org.example.entities.Student;

import java.util.List;

public interface StudentDao {
    void add(Student student);
    void update(Student student);
    void delete(Student student);
    List<Student> showAll();
    Student getById(String output);
    void numberOfStudentsInSchool();
    void numberOfStudentsInProgramme(int progID);
}
