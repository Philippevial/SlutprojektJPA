package org.example.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    public int getTeacherID() {
        return teacherID;
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



    @Override
    public String toString() {
        return "Teacher{" +
                "teacherID=" + teacherID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssn=" + ssn +
                '}';
    }


}
