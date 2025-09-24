package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;

public class TranscriptService {

    public String generateTranscript(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------------------\n");
        sb.append("        ACADEMIC TRANSCRIPT\n");
        sb.append("----------------------------------------\n");
        sb.append(student.getProfileDetails()).append("\n\n");
        sb.append("Course Enrollments:\n");

        if (student.getEnrolledCourses().isEmpty()) {
            sb.append("  No courses enrolled.\n");
        } else {
            // Polymorphism in action: calling toString() on different objects [cite: 28, 62]
            for (Enrollment enrollment : student.getEnrolledCourses()) {
                sb.append(String.format("  - %-35s | Grade: %s%n",
                    enrollment.getCourse().getTitle(), enrollment.getGrade()));
            }
        }

        sb.append("\nGPA: ").append(String.format("%.2f", calculateGPA(student))).append("\n");
        sb.append("----------------------------------------\n");
        return sb.toString();
    }

    private double calculateGPA(Student student) {
        double totalPoints = 0;
        int totalCredits = 0;

        for (Enrollment e : student.getEnrolledCourses()) {
            if (e.getGrade() != Grade.NA) {
                totalPoints += e.getGrade().getGradePoint() * e.getCourse().getCredits();
                totalCredits += e.getCourse().getCredits();
            }
        }
        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }
}