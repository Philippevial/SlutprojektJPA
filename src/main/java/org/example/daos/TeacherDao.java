package org.example.daos;

import org.example.entities.Teacher;

import java.util.List;

public interface TeacherDao {
    void add(Teacher teacher);
    void update(Teacher teacher);
    void delete(Teacher teacher);
    List<Teacher> showAll();
    Teacher getById(String output);
    void numberOfTeachersInSchool();
    void numberOfTeachersInCourse(int courseId);
}
