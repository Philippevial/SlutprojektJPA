package org.example;

import org.example.daoimplementations.CourseDaoImpl;
import org.example.daoimplementations.ProgrammeDaoImpl;
import org.example.daoimplementations.StudentDaoImpl;
import org.example.daoimplementations.TeacherDaoImpl;
import org.example.entities.Course;
import org.example.entities.Programme;
import org.example.entities.Student;
import org.example.entities.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    StudentDaoImpl studentDao;
    TeacherDaoImpl teacherDao;
    CourseDaoImpl courseDao;
    ProgrammeDaoImpl programmeDao;

    Student student;
    Teacher teacher;
    Course course;
    Programme programme;
    private final Scanner scanner = new Scanner(System.in);
    private boolean run = true;
    private int choice;

    public void runApp() {
        initializeObjects();
        while (run) {
            menuChoices();
            choice = inputReadInteger(" ");
            run = executeChoice();
        }
    }

    public void menuChoices() {
        System.out.println("""
                MAIN MENU
                1. Add
                2. Update
                3. Remove
                4. Show all
                5. Show specific
                6. Statistics
                0. Shut down""");
    }

    private void menuPrint() {
        System.out.println("""
                CATEGORIES
                1. Student
                2. Teacher
                3. Course
                4. Programme
                0. Shut down
                """);
    }

    private void initializeObjects() {
        studentDao = new StudentDaoImpl();
        teacherDao = new TeacherDaoImpl();
        courseDao = new CourseDaoImpl();
        programmeDao = new ProgrammeDaoImpl();

        student = new Student();
        teacher = new Teacher();
        course = new Course();
        programme = new Programme();
    }

    private boolean executeChoice() {
        switch (choice) {
            case 1 -> addMenu();
            case 2 -> updateMenu();
            case 3 -> removeMenu();
            case 4 -> show();
            case 5 -> showSpecificMenu();
            case 6 -> statisticsMenu();
            case 0 -> run = false;
            default -> System.out.println("Wrong choice, try again!");
        }
        return run;
    }

    private void statisticsMenu() {
        printStatisticsChoices();
        choice = inputReadInteger("Enter choice: ");
        switch (choice) {
            case 1 -> courseDao.numberOfCoursesInSchool();
            case 2 -> courseDao.numberOfCoursesInProgramme(inputReadInteger("Enter programme ID: "));
            case 3 -> studentDao.numberOfStudentsInSchool();
            case 4 -> studentDao.numberOfStudentsInProgramme(inputReadInteger("Enter programme ID: "));
            case 5 -> teacherDao.numberOfTeachersInSchool();
            case 6 -> teacherDao.numberOfTeachersInCourse(inputReadInteger("Enter course ID: "));
            case 0 -> run = false;
        }
    }

    private void printStatisticsChoices() {
        System.out.println("""
                1. Number of courses in the school
                2. Number of courses in a programme
                3. Number of students in the school
                4. Number of students in a programme
                5. Number of teachers in the school
                6. Number of teachers in a course
                """);
    }

    private void showSpecificMenu() {
        menuPrint();
        choice = inputReadInteger("Enter your choice: ");
        switch (choice) {
            case 1 -> System.out.println(studentDao.getById("Enter student ID to show student: "));
            case 2 -> System.out.println(teacherDao.getById("Enter teacher ID to show teacher: "));
            case 3 -> System.out.println(courseDao.getById("Enter course ID to show course: "));
            case 4 -> System.out.println(programmeDao.getById("Enter program ID to show program: "));
            default -> System.out.println("Wrong input, try again!");
        }
    }

    private void updateMenu() {
        menuPrint();
        choice = inputReadInteger("Enter your choice: ");
        switch (choice) {
            case 1 -> studentDao.update(studentInfo());
            case 2 -> teacherDao.update(teacherInfo());
            case 3 -> courseDao.update(courseInfo());
            case 4 -> programmeDao.update(programmeInfo());
            case 0 -> run = false;
            default -> System.out.println("Wrong choice, try again!");
        }
    }

    private void addMenu() {
        menuPrint();
        choice = inputReadInteger("Enter category");
        switch (choice) {
            case 1 -> studentDao.add(personValues(student));
            case 2 -> teacherDao.add(personValues(teacher));
            case 3 -> courseDao.add(courseValues());
            case 4 -> programmeDao.add(programmeValues());
            case 0 -> run = false;
            default -> System.out.println("Wrong choice, try again!");
        }
    }


    private void removeMenu() {
        menuPrint();
        choice = inputReadInteger("Enter category: ");
        switch (choice) {
            case 1 -> studentDao.delete(studentDao.getById("Student ID: "));
            case 2 -> teacherDao.delete(teacherDao.getById("Teacher ID: "));
            case 3 -> courseDao.delete(courseDao.getById("Course ID: "));
            case 4 -> programmeDao.delete(programmeDao.getById("Programme ID: "));
            case 0 -> run = false;
            default -> System.out.println("Wrong choice, try again!");
        }
    }

    private void show() {
        menuPrint();
        choice = inputReadInteger("Enter category: ");
        switch (choice) {
            case 1 -> studentDao.showAll().forEach(System.out::println);
            case 2 -> teacherDao.showAll().forEach(System.out::println);
            case 3 -> courseDao.showAll().forEach(System.out::println);
            case 4 -> programmeDao.showAll().forEach(System.out::println);
            case 0 -> run = false;
            default -> System.out.println("Wrong choice, try again!");
        }
    }


    public int inputReadInteger(String output) {
        System.out.println(output);
        while (!scanner.hasNextInt()) {
            System.out.println("Wrong input, only numbers! Try Again!");
            scanner.next();
        }
        return Integer.parseInt(scanner.next());
    }

    public String inputReadString(String output) {
        System.out.println(output);
        while (scanner.hasNextInt()) {
            System.out.println("Wrong input, no numbers! Try Again!");
            scanner.next();
        }
        return scanner.next();
    }

    public Student studentInfo() {
        student = studentDao.getById("Enter student ID for the student you want to update: ");
        student.setFirstName(inputReadString("Enter First name: "));
        student.setLastName(inputReadString("Enter lastname: "));
        student.setSsn(inputReadString("Enter SSN: "));

        return student;

    }

    public Student personValues(Student student) {

        student.setFirstName(inputReadString("Enter First name: "));
        student.setLastName(inputReadString("Enter Last name: "));
        student.setSsn(inputReadString("Enter SSN(12 numbers): "));

        return student;
    }

    public Teacher teacherInfo() {
        teacher = teacherDao.getById("Enter teacher ID for the teacher you want to update: ");
        teacher.setFirstName(inputReadString("Enter first name: "));
        teacher.setLastName(inputReadString("Enter last name: "));
        teacher.setSsn(inputReadString("Enter date of birth (12 numbers): "));

        return teacher;
    }

    public Teacher personValues(Teacher teacher) {
        teacher.setFirstName(inputReadString("Enter First name: "));
        teacher.setLastName(inputReadString("Enter Last name: "));
        teacher.setSsn(inputReadString("Enter SSN(12 numbers): "));

        return teacher;
    }

    public Course courseInfo() {
        course = courseDao.getById("Enter course ID for the course you want to update: ");
        course.setCourseName(inputReadString("Enter course name: "));
        course.setTeacherList(createTeacherList());

        return course;
    }

    public Course courseValues() {
        String courseName;
        courseName = inputReadString("Enter Course name: ");
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(teacherDao.getById("Enter Teacher ID: "));

        return new Course(courseName, teacherList);
    }

    public Programme programmeInfo() {
        programme = programmeDao.getById("Enter programme ID for the program you want to update: ");
        programme.setProgName(inputReadString("Enter programme name: "));

        programme.setCourseList(createCourseList());
        programme.setStudentList(createStudentList());

        return programme;
    }

    private Programme programmeValues() {

        programme.setProgName(inputReadString("Enter a programme name: "));
        programme.setCourseList(createCourseList());
        programme.setStudentList(createStudentList());


        return programme;
    }

    private List<Student> createStudentList() {
        List<Student> studentList = new ArrayList<>();

        int numbOfStudents = inputReadInteger("How many students do you want to add to the programme?");

        for (int i = 1; i <= numbOfStudents; i++)
            studentList.add(studentDao.getById("Enter id for student nr: " + i + ":"));

        return studentList;
    }


    private List<Teacher> createTeacherList() {
        List<Teacher> teacherList = new ArrayList<>();

        int numberOfTeachers = inputReadInteger("How many teachers do you want to add to the course?");

        for (int i = 1; i <= numberOfTeachers; i++)
            teacherList.add(teacherDao.getById("Enter ID for teacher nr " + i + ":"));

        return teacherList;
    }

    private List<Course> createCourseList() {
        List<Course> courseList = new ArrayList<>();
        int numberOfCourse = inputReadInteger("How many courses do you want to add to the programme?");


        for (int i = 1; i <= numberOfCourse; i++)
            courseList.add(courseDao.getById("Enter ID for course nr " + i + ":"));

        return courseList;
    }
}
