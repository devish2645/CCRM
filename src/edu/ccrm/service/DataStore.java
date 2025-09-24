package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;
import java.util.HashMap;
import java.util.Map;

public class DataStore {
    public final Map<String, Student> students = new HashMap<>();
    public final Map<String, Course> courses = new HashMap<>();
    // In a real app, instructors would also be stored here.
}