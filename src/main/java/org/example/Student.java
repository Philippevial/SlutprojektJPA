package org.example;

import org.example.daos.StudentDao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentID;

    private String firstName;
    private String lastName;
    private String ssn;

    public Student(){}

    public Student(String firstName, String lastName, String ssn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Student personInfoInput() {
        Menu menu = new Menu();

        firstName = menu.inputReadString("First name: ");
        lastName = menu.inputReadString("Last name: ");
        ssn = menu.inputReadString("ssn(12 numbers): ");

        while (ssn.length() != 12)
            ssn = menu.inputReadString("Wrong input try again");
        return new Student(firstName ,lastName ,ssn);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssn=" + ssn +
                '}';
    }

    public Student updateInfo() {
        StudentDao studentDao = new StudentDao();
        Menu menu = new Menu();

        Student newStudent = studentDao.getById("Enter student ID to update values: ");
        newStudent.setFirstName(menu.inputReadString("Firstname: "));
        newStudent.setLastName(menu.inputReadString("Lastname: "));
        newStudent.setSsn(menu.inputReadString("SSN: "));

        return newStudent;
    }
}
