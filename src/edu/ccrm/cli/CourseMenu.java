package edu.ccrm.cli;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import edu.ccrm.exception.DuplicateResourceException;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.DataStore;

import java.util.Scanner;

public class CourseMenu {
    private final Scanner scanner;
    private final CourseService courseService;
    private final DataStore dataStore;

    public CourseMenu(Scanner scanner, CourseService courseService, DataStore dataStore) {
        this.scanner = scanner;
        this.courseService = courseService;
        this.dataStore = dataStore;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- Course Management ---");
            System.out.println("1. Add New Course");
            System.out.println("2. List All Courses");
            System.out.println("3. Filter Courses by Semester");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) break;
            handleChoice(choice);
        }
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1: addCourse(); break;
            case 2: listCourses(); break;
            case 3: filterCourses(); break;
            default: System.out.println("Invalid choice.");
        }
    }

    private void addCourse() {
        try {
            System.out.print("Enter Course Code (e.g., CS101): ");
            String code = scanner.nextLine();
            System.out.print("Enter Title: ");
            String title = scanner.nextLine();
            System.out.print("Enter Credits: ");
            int credits = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Semester (SPRING, FALL, SUMMER): ");
            Semester semester = Semester.valueOf(scanner.nextLine().toUpperCase());

            // Using the builder pattern
            Course course = new Course.Builder(code, title)
                .credits(credits)
                .semester(semester)
                .build();
            
            courseService.addCourse(course);
            System.out.println("Course added successfully: " + course);
        } catch (DuplicateResourceException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void listCourses() {
        System.out.println("\n--- All Courses ---");
        dataStore.courses.values().forEach(System.out::println);
    }
    
    private void filterCourses() {
        try {
            System.out.print("Enter Semester to filter by (SPRING, FALL, SUMMER): ");
            Semester semester = Semester.valueOf(scanner.nextLine().toUpperCase());
            System.out.println("\n--- Courses in " + semester + " ---");
            courseService.filterCoursesBySemester(semester).forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid semester entered.");
        }
    }
}