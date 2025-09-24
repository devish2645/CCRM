package edu.ccrm.io;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Student;
import edu.ccrm.service.DataStore;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileService {
    public void exportData(DataStore dataStore, Path dir) throws IOException {
        Files.createDirectories(dir);
        
        // Export Students
        Path studentsFile = dir.resolve("students.csv");
        List<String> studentLines = dataStore.students.values().stream()
            .map(s -> String.join(",", s.getId(), s.getRegNo(), s.getFullName(), s.getEmail()))
            .collect(Collectors.toList());
        Files.write(studentsFile, studentLines);

        // Export Courses
        Path coursesFile = dir.resolve("courses.csv");
        List<String> courseLines = dataStore.courses.values().stream()
            .map(c -> String.join(",", c.getCode(), c.getTitle(), String.valueOf(c.getCredits()), c.getSemester().name()))
            .collect(Collectors.toList());
        Files.write(coursesFile, courseLines);
        
        System.out.println("Data exported successfully to " + dir);
    }

    public void importData(DataStore dataStore, Path dir) throws IOException {
        // Import Students
        Path studentsFile = dir.resolve("students.csv");
        if(Files.exists(studentsFile)) {
            try (Stream<String> lines = Files.lines(studentsFile)) {
                lines.forEach(line -> {
                    String[] parts = line.split(",");
                    if(parts.length == 4) {
                       Student s = new Student(parts[0], parts[1], parts[2], parts[3]);
                       dataStore.students.put(s.getId(), s);
                    }
                });
            }
        }

        // Import Courses
        Path coursesFile = dir.resolve("courses.csv");
        if (Files.exists(coursesFile)) {
            try(Stream<String> lines = Files.lines(coursesFile)) {
                 lines.forEach(line -> {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        Course c = new Course.Builder(parts[0], parts[1])
                            .credits(Integer.parseInt(parts[2]))
                            .semester(Semester.valueOf(parts[3]))
                            .build();
                        dataStore.courses.put(c.getCode(), c);
                    }
                });
            }
        }
        System.out.println("Data imported successfully from " + dir);
    }
}