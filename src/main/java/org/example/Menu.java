package org.example;

import org.example.daos.*;

import java.util.Scanner;

public class Menu {

    StudentDao studentDao;
    TeacherDao teacherDao;
    CourseDao courseDao;
    ProgrammeDao programmeDao;

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
                4. Show all""");
    }

    private void initializeObjects() {
        studentDao = new StudentDao();
        teacherDao = new TeacherDao();
        courseDao = new CourseDao();
        programmeDao = new ProgrammeDao();

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
            case 0 -> run = false;
            default -> System.out.println("Wrong choice, try again!");
        }
        return run;
    }

    private void updateMenu() {
        System.out.println("Choose category: ");

        menuPrint();
        choice = inputReadInteger(" ");
        switch (choice) {
            case 1 -> studentDao.update(student.updateInfo());
            case 2 -> teacherDao.update(teacher.updateInfo());
            case 3 -> courseDao.update(course.updateInfo());
            case 4 -> programmeDao.update(programme.updateInfo());
            case 0 -> run = false;
            default -> System.out.println("Wrong choice, try again!");
        }
    }

    private void addMenu() {
        System.out.println("Choose category: ");
        menuPrint();
        choice = inputReadInteger(" ");
        switch (choice) {
            case 1 -> studentDao.add(student.personInfoInput());
            case 2 -> teacherDao.add(teacher.personInfoInput());
            case 3 -> courseDao.add(course.courseInfoInput());
            case 4 -> programmeDao.add(programme.programmeInfoInput());
            case 0 -> run = false;
            default -> System.out.println("Wrong choice, try again!");
        }
    }


    private void menuPrint() {
        System.out.println("""
                1. Student
                2. Teacher
                3. Course
                4. Programme
                0. Avsluta
                """);
    }

    private void removeMenu() {
        System.out.println("Choose category: ");

        menuPrint();
        choice = inputReadInteger(" ");
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
        System.out.println("Choose category: ");
        menuPrint();
        choice = inputReadInteger(" ");
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

}
