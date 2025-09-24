package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import edu.ccrm.exception.DuplicateResourceException;
import edu.ccrm.exception.ResourceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class CourseService {
    private final DataStore dataStore;

    public CourseService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void addCourse(Course course) throws DuplicateResourceException {
        if (dataStore.courses.containsKey(course.getCode())) {
            throw new DuplicateResourceException("Course with code '" + course.getCode() + "' already exists.");
        }
        dataStore.courses.put(course.getCode(), course);
    }

    public Course findCourseByCode(String code) {
        Course course = dataStore.courses.get(code);
        if (course == null) {
            throw new ResourceNotFoundException("Course with code '" + code + "' not found.");
        }
        return course;
    }

    // Demonstrates Stream API for filtering [cite: 23]
    public List<Course> filterCoursesBySemester(Semester semester) {
        return dataStore.courses.values().stream()
            .filter(course -> course.getSemester() == semester)
            .collect(Collectors.toList());
    }
}