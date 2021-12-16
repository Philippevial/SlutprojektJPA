package org.example;

import org.example.daos.TeacherDao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacherID;

    private String firstName;
    private String lastName;
    private String ssn;

    public Teacher () {}

    public Teacher(String firstName, String lastName, String ssn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
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

    public Teacher personInfoInput() {
        Menu menu = new Menu();

        firstName = menu.inputReadString("First name: ");
        lastName = menu.inputReadString("Last name: ");
        ssn = menu.inputReadString("SSN(12 numbers): ");

        return new Teacher(firstName,lastName,ssn);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherID=" + teacherID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssn=" + ssn +
                '}';
    }

    public Teacher updateInfo() {
            TeacherDao teacherDao = new TeacherDao();
            Menu menu = new Menu();

            Teacher newTeacher = teacherDao.getById("Enter ID for to update values: ");
            newTeacher.setFirstName(menu.inputReadString("First name: "));
            newTeacher.setLastName(menu.inputReadString("Ange efternamn: "));
            newTeacher.setSsn(menu.inputReadString("SSN: "));

            return newTeacher;

    }
}
