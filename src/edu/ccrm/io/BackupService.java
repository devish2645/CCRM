package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class BackupService {

    public void createBackup(Path sourceDir, Path backupRootDir) throws IOException {
        if (!Files.exists(sourceDir)) {
            System.out.println("Source data directory does not exist. Nothing to back up.");
            return;
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        Path targetDir = backupRootDir.resolve("backup_" + timestamp);
        Files.createDirectories(targetDir);

        try (Stream<Path> stream = Files.walk(sourceDir)) {
            stream.forEach(source -> {
                try {
                    // Use Path.resolve and Path.relativize to maintain structure
                    Files.copy(source, targetDir.resolve(sourceDir.relativize(source)), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.err.println("Failed to copy " + source + ": " + e.getMessage());
                }
            });
        }
        System.out.println("Backup created successfully at: " + targetDir);
    }
}