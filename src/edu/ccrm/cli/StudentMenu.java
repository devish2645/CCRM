package edu.ccrm.cli;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;
import edu.ccrm.exception.DuplicateResourceException;
import edu.ccrm.exception.MaxCreditLimitExceededException;
import edu.ccrm.exception.ResourceNotFoundException;
import edu.ccrm.service.DataStore;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.StudentService;
import edu.ccrm.service.TranscriptService;

import java.util.Scanner;

public class StudentMenu {
    private final Scanner scanner;
    private final StudentService studentService;
    private final EnrollmentService enrollmentService;
    private final TranscriptService transcriptService;
    private final DataStore dataStore;

    public StudentMenu(Scanner scanner, StudentService studentService, EnrollmentService enrollmentService, TranscriptService transcriptService, DataStore dataStore) {
        this.scanner = scanner;
        this.studentService = studentService;
        this.enrollmentService = enrollmentService;
        this.transcriptService = transcriptService;
        this.dataStore = dataStore;
    }

    public void display() {
        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add New Student");
            System.out.println("2. List All Students");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Record Grade");
            System.out.println("5. Print Student Transcript");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) {
                break;
            }
            handleChoice(choice);
        }
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1: addStudent(); break;
            case 2: listStudents(); break;
            case 3: enrollStudent(); break;
            case 4: recordGrade(); break;
            case 5: printTranscript(); break;
            default: System.out.println("Invalid choice.");
        }
    }

    private void addStudent() {
        try {
            System.out.print("Enter Registration No: ");
            String regNo = scanner.nextLine();
            System.out.print("Enter Full Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            Student newStudent = studentService.addStudent(regNo, name, email);
            System.out.println("Student added successfully: " + newStudent);
        } catch (DuplicateResourceException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void listStudents() {
        System.out.println("\n--- All Students ---");
        dataStore.students.values().forEach(System.out::println);
    }

    private void enrollStudent() {
        try {
            System.out.print("Enter Student ID to enroll: ");
            String studentId = scanner.nextLine();
            Student student = studentService.findStudentById(studentId);

            System.out.print("Enter Course Code to enroll in: ");
            String courseCode = scanner.nextLine();
            Course course = dataStore.courses.get(courseCode);
            if (course == null) throw new ResourceNotFoundException("Course not found.");

            enrollmentService.enrollStudent(student, course);
            System.out.println("Enrollment successful!");
        } catch (ResourceNotFoundException | DuplicateResourceException | MaxCreditLimitExceededException e) {
            System.err.println("Enrollment failed: " + e.getMessage());
        }
    }
    
    private void recordGrade() {
        try {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();
            Student student = studentService.findStudentById(studentId);

            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine();
            Course course = dataStore.courses.get(courseCode);
            if (course == null) throw new ResourceNotFoundException("Course not found.");

            System.out.print("Enter Grade (S, A, B, C, D, F): ");
            Grade grade = Grade.valueOf(scanner.nextLine().toUpperCase());

            enrollmentService.recordGrade(student, course, grade);
            System.out.println("Grade recorded successfully.");
        } catch (ResourceNotFoundException | IllegalArgumentException e) {
            System.err.println("Error recording grade: " + e.getMessage());
        }
    }

    private void printTranscript() {
        try {
            System.out.print("Enter Student ID to print transcript: ");
            String studentId = scanner.nextLine();
            Student student = studentService.findStudentById(studentId);
            String transcript = transcriptService.generateTranscript(student);
            System.out.println(transcript);
        } catch (ResourceNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}