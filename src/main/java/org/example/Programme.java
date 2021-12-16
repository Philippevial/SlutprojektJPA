package org.example;

import org.example.daos.CourseDao;
import org.example.daos.ProgrammeDao;
import org.example.daos.StudentDao;
import org.example.daos.TeacherDao;

import javax.persistence.*;
import java.util.ArrayList;
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

    public Programme programmeInfoInput() {
        Menu menu = new Menu();
        StudentDao studentDao = new StudentDao();
        CourseDao courseDao = new CourseDao();
        List<Course> courseList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();

        progName = menu.inputReadString("Programme name: ");
        courseList.add(courseDao.getById("Course ID: "));
        studentList.add(studentDao.getById("Student ID"));
        return new Programme(progName, courseList, studentList);
    }


    public Programme updateInfo() {
        ProgrammeDao programmeDao = new ProgrammeDao();
        CourseDao courseDao = new CourseDao();
        StudentDao studentDao = new StudentDao();
        Menu menu = new Menu();

        Programme programme = programmeDao.getById("Enter ID to update programme: ");
        programme.setProgName(menu.inputReadString("Programme name: "));

        List<Course> courseList = new ArrayList<>();
        courseList.add(courseDao.getById("Enter course ID: "));
        programme.setCourseList(courseList);

        List<Student> studentList = new ArrayList<>();
        studentList.add(studentDao.getById("Enter student ID: "));
        programme.setStudentList(studentList);

        return programme;
    }
}
