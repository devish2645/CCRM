package edu.ccrm.domain;

public class Course {
    private final String code;
    private final String title;
    private final int credits;
    private final Semester semester;
    private Instructor instructor;

    // Private constructor to enforce builder usage
    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.semester = builder.semester;
        this.instructor = builder.instructor;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public Semester getSemester() { return semester; }
    public Instructor getInstructor() { return instructor; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }

    @Override
    public String toString() {
        return String.format("Course [%s]: %s (%d credits, %s) - Instructor: %s",
            code, title, credits, semester, (instructor != null ? instructor.getFullName() : "TBD"));
    }

    // Static nested Builder class [cite: 67]
    public static class Builder {
        private final String code;
        private final String title;
        private int credits;
        private Semester semester;
        private Instructor instructor;

        public Builder(String code, String title) {
            this.code = code;
            this.title = title;
        }

        public Builder credits(int credits) {
            this.credits = credits;
            return this;
        }

        public Builder semester(Semester semester) {
            this.semester = semester;
            return this;
        }

        public Builder instructor(Instructor instructor) {
            this.instructor = instructor;
            return this;
        }

        public Course build() {
            // Assertions for invariants 
            assert code != null && !code.trim().isEmpty() : "Course code must not be null or empty.";
            assert credits > 0 && credits < 10 : "Credits must be between 1 and 9.";
            return new Course(this);
        }
    }
}