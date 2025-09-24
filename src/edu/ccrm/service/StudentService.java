package edu.ccrm.service;

import edu.ccrm.domain.Student;
import edu.ccrm.exception.DuplicateResourceException;
import edu.ccrm.exception.ResourceNotFoundException;
import java.util.UUID;

public class StudentService {
    private final DataStore dataStore;

    public StudentService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public Student addStudent(String regNo, String fullName, String email) throws DuplicateResourceException {
        if (dataStore.students.values().stream().anyMatch(s -> s.getRegNo().equalsIgnoreCase(regNo))) {
            throw new DuplicateResourceException("Student with Registration No '" + regNo + "' already exists.");
        }
        String id = "S" + UUID.randomUUID().toString().substring(0, 8);
        Student student = new Student(id, regNo, fullName, email);
        dataStore.students.put(id, student);
        return student;
    }

    public Student findStudentById(String id) {
        Student student = dataStore.students.get(id);
        if (student == null) {
            throw new ResourceNotFoundException("Student with ID '" + id + "' not found.");
        }
        return student;
    }
}