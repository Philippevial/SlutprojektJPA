package org.example.daos;

import org.example.entities.Course;

import java.util.List;

public interface CourseDao {
    void add(Course course);
    void update(Course course);
    void delete(Course course);
    List<Course> showAll();
    void showInfo();
    Course getById(String output);
}
