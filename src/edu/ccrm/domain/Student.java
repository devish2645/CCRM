package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Student extends Person {
    private final String regNo;
    private final LocalDate enrollmentDate;
    private List<Enrollment> enrolledCourses;

    public Student(String id, String regNo, String fullName, String email) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.enrollmentDate = LocalDate.now(); // Using Date/Time API [cite: 18]
        this.enrolledCourses = new ArrayList<>();
    }

    public String getRegNo() {
        return regNo;
    }
    
    public List<Enrollment> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollInCourse(Enrollment enrollment) {
        this.enrolledCourses.add(enrollment);
    }
    
    @Override
    public String getProfileDetails() {
        String courses = enrolledCourses.stream()
            .map(e -> e.getCourse().getTitle())
            .collect(Collectors.joining(", "));
        return String.format("Student Profile:%n  ID: %s%n  RegNo: %s%n  Name: %s%n  Enrolled On: %s%n  Courses: [%s]",
            getId(), regNo, getFullName(), enrollmentDate, courses.isEmpty() ? "None" : courses);
    }
}