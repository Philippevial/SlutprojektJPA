package org.example;

import org.example.daos.CourseDao;
import org.example.daos.TeacherDao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseID;
    private String courseName;

    @ManyToMany(targetEntity = Teacher.class)
    private List<Teacher> teacherList;


    public Course () {}

    public Course(String courseName, List<Teacher> teacherList) {
        this.courseName = courseName;
        this.teacherList = teacherList;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", teacherList=" + teacherList +
                '}';
    }


    public Course courseInfoInput() {
        Menu menu = new Menu();
        TeacherDao teacherDao = new TeacherDao();

        courseName = menu.inputReadString("Course name: ");
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(teacherDao.getById("Teacher ID: "));
        return new Course(courseName,teacherList);
    }

    public Course updateInfo() {
        CourseDao courseDao = new CourseDao();
        TeacherDao teacherDao = new TeacherDao();
        Menu menu = new Menu();

        Course newCourse = courseDao.getById("Enter ID for to update values: ");
        newCourse.setCourseName(menu.inputReadString("Course name: "));

        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(teacherDao.getById("Skriv in id för den lärare du vill lägga till i kursen"));
        newCourse.setTeacherList(teacherList);

        return newCourse;
    }
}
