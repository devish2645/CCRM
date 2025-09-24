package edu.ccrm.service;

import edu.ccrm.domain.*;
import edu.ccrm.exception.DuplicateResourceException;
import edu.ccrm.exception.MaxCreditLimitExceededException;

public class EnrollmentService {
    private static final int MAX_CREDITS_PER_SEMESTER = 20;

    public void enrollStudent(Student student, Course course)
        throws DuplicateResourceException, MaxCreditLimitExceededException {
        
        boolean isAlreadyEnrolled = student.getEnrolledCourses().stream()
            .anyMatch(e -> e.getCourse().getCode().equals(course.getCode()));

        if (isAlreadyEnrolled) {
            throw new DuplicateResourceException("Student is already enrolled in " + course.getTitle());
        }

        int currentCredits = student.getEnrolledCourses().stream()
            .filter(e -> e.getCourse().getSemester() == course.getSemester())
            .mapToInt(e -> e.getCourse().getCredits())
            .sum();

        if (currentCredits + course.getCredits() > MAX_CREDITS_PER_SEMESTER) {
            throw new MaxCreditLimitExceededException(
                "Cannot enroll. Exceeds max credit limit of " + MAX_CREDITS_PER_SEMESTER + " for the semester.");
        }

        Enrollment enrollment = new Enrollment(student, course);
        student.enrollInCourse(enrollment);
    }
    
    public void recordGrade(Student student, Course course, Grade grade) {
        student.getEnrolledCourses().stream()
            .filter(e -> e.getCourse().getCode().equals(course.getCode()))
            .findFirst()
            .ifPresent(enrollment -> enrollment.setGrade(grade));
    }
}