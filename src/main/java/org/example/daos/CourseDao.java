package org.example.daos;

import org.example.entities.Course;

import java.util.List;

public interface CourseDao {
    void add(Course course);
    void update(Course course);
    void delete(Course course);
    List<Course> showAll();
    Course getById(String output);
    void numberOfCoursesInSchool();
    void numberOfCoursesInProgramme(int Id);
}
