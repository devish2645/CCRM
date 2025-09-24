package edu.ccrm.domain;

public class Enrollment {
    private final Student student;
    private final Course course;
    private Grade grade;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.grade = Grade.NA; // Default grade
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public Grade getGrade() { return grade; }
    public void setGrade(Grade grade) { this.grade = grade; }
}