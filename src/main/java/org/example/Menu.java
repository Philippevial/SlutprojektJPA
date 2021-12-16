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

    Statistics statistics;
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
                1. Add
                2. Update
                3. Remove
                4. Show all
                5. Show specific
                0. Shut down""");
    }

    private void menuPrint() {
        System.out.println("""
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

        statistics = new Statistics();
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
            case 1 -> courseDao.numberOfCoursesInSchool().forEach(System.out::println);
            /*case 2 -> courseDao.update(student.studentValuesUpdate());
            case 3 -> studentDao.numberOfStudentsInSchool();
            case 4 -> studentDao.numberOfStudentsInProgramme(verifyInteger("Skriv programmets id: "));
            case 5 -> teacherDao.numberOfTeachersInSchool().forEach(System.out::println);
            case 5 -> teacherDao.numberOfTeachersInSchool().forEach(System.out::println);*/
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
            case 1 -> studentDao.update(updateInfo(studentDao));
            case 2 -> teacherDao.update(updateInfo(teacherDao));
            case 3 -> courseDao.update(updateInfo(courseDao, teacherDao));
            case 4 -> programmeDao.update(updateInfo(courseDao, studentDao));
            case 0 -> run = false;
            default -> System.out.println("Wrong choice, try again!");
        }
    }

    private void addMenu() {
        menuPrint();
        choice = inputReadInteger("Enter category");
        switch (choice) {
            case 1 -> studentDao.add(personInfoInput(student));
            case 2 -> teacherDao.add(personInfoInput(teacher));
            case 3 -> courseDao.add(courseInfoInput());
            case 4 -> programmeDao.add(programmeInfoInput());
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

    public Course courseInfoInput() {
        String courseName;
        courseName = inputReadString("Course name: ");
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(teacherDao.getById("Teacher ID: "));

        return new Course(courseName, teacherList);
    }

    private Programme programmeInfoInput() {
        String progName;
        progName = inputReadString("Programme name: ");

        List<Course> courseList = new ArrayList<>();
        courseList.add(courseDao.getById("Enter course ID: "));

        List<Student> studentList = new ArrayList<>();
        studentList.add(studentDao.getById("Enter student ID: "));

        return new Programme(progName, courseList, studentList);
    }

    public Course updateInfo(CourseDaoImpl courseDao, TeacherDaoImpl teacherDao) {

        Course newCourse = courseDao.getById("Enter ID for to update values: ");
        newCourse.setCourseName(inputReadString("Course name: "));

        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(teacherDao.getById("Enter teacher ID to add to the programme: "));
        newCourse.setTeacherList(teacherList);

        return newCourse;
    }

    public Programme updateInfo(CourseDaoImpl courseDao, StudentDaoImpl studentDao) {

        Programme programme = programmeDao.getById("Enter ID to update programme: ");
        programme.setProgName(inputReadString("Programme name: "));

        List<Course> courseList = new ArrayList<>();
        courseList.add(courseDao.getById("Enter course ID: "));
        programme.setCourseList(courseList);

        List<Student> studentList = new ArrayList<>();
        studentList.add(studentDao.getById("Enter student ID: "));
        programme.setStudentList(studentList);

        return programme;
    }

    public Teacher updateInfo(TeacherDaoImpl teacherDaoImp) {
        Teacher newTeacher = teacherDaoImp.getById("Enter teacher ID to update values: ");
        newTeacher.setFirstName(inputReadString("First name: "));
        newTeacher.setLastName(inputReadString("Ange efternamn: "));
        newTeacher.setSsn(inputReadString("SSN: "));

        return newTeacher;

    }

    public Student updateInfo(StudentDaoImpl studentDao) {

        Student newStudent = studentDao.getById("Enter student ID for to update values: ");
        newStudent.setFirstName(inputReadString("First name: "));
        newStudent.setLastName(inputReadString("Ange efternamn: "));
        newStudent.setSsn(inputReadString("SSN: "));

        return newStudent;

    }

    public Student personInfoInput(Student student) {

        student.setFirstName(inputReadString("First name: "));
        student.setLastName(inputReadString("Last name: "));
        student.setSsn(inputReadString("SSN(12 numbers): "));

        return student;
    }

    public Teacher personInfoInput(Teacher teacher) {
        teacher.setFirstName(inputReadString("First name: "));
        teacher.setLastName(inputReadString("Last name: "));
        teacher.setSsn(inputReadString("SSN(12 numbers): "));

        return teacher;
    }
}
