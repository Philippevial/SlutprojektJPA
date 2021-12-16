package org.example.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Programme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int progID;
    private String progName;


    @OneToMany(targetEntity = Course.class)
    private List<Course> courseList;

    @OneToMany(targetEntity = Student.class)
    private List<Student> studentList;

    public Programme(){}

    public Programme(String progName, List<Course> courseList, List<Student> studentList) {
        this.progName = progName;
        this.courseList = courseList;
        this.studentList = studentList;
    }

    public String getProgName() {
        return progName;
    }

    public void setProgName(String progName) {
        this.progName = progName;
    }


    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Programme{" +
                "progID=" + progID +
                ", progName='" + progName + '\'' +
                ", courseList=" + courseList +
                ", studentList=" + studentList +
                '}';
    }





}
