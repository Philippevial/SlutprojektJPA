package org.example.entities;

import javax.persistence.*;
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





}
