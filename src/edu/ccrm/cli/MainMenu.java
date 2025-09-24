package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.FileService;
import edu.ccrm.service.*;
import edu.ccrm.util.RecursiveFileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final DataStore dataStore = new DataStore();
    private final AppConfig config = AppConfig.getInstance();
    
    // Services
    private final StudentService studentService = new StudentService(dataStore);
    private final CourseService courseService = new CourseService(dataStore);
    private final EnrollmentService enrollmentService = new EnrollmentService();
    private final TranscriptService transcriptService = new TranscriptService();
    private final FileService fileService = new FileService();
    private final BackupService backupService = new BackupService();
    
    // Sub-menus
    private final StudentMenu studentMenu = new StudentMenu(scanner, studentService, enrollmentService, transcriptService, dataStore);
    private final CourseMenu courseMenu = new CourseMenu(scanner, courseService, dataStore);

    public void display() {
        System.out.println("Welcome to Campus Course & Records Manager (CCRM)");
        loadInitialData();

        // Labeled jump demonstration [cite: 37]
        main_loop:
        while (true) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    studentMenu.display();
                    break;
                case 2:
                    courseMenu.display();
                    break;
                case 3:
                    handleDataExport();
                    break;
                case 4:
                    handleBackup();
                    break;
                case 5:
                    showBackupSize();
                    break;
                case 0:
                    System.out.println("Exiting application...");
                    break main_loop; // Jumps out of the labeled loop
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
    
    private void printMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Export Data");
        System.out.println("4. Create Backup");
        System.out.println("5. Show Total Backup Size (Recursive)");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private void loadInitialData() {
        // try-with-resources and multi-catch block [cite: 85]
        try {
            fileService.importData(dataStore, config.getDataDirectory());
        } catch (IOException e) {
            System.err.println("Could not load initial data: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during data load: " + e.getMessage());
        } finally {
            System.out.println("Initial data load process finished.");
        }
    }

    private void handleDataExport() {
        try {
            fileService.exportData(dataStore, config.getDataDirectory());
        } catch (IOException e) {
            System.err.println("Error exporting data: " + e.getMessage());
        }
    }

    private void handleBackup() {
        try {
            backupService.createBackup(config.getDataDirectory(), config.getBackupDirectory());
        } catch (IOException e) {
            System.err.println("Error creating backup: " + e.getMessage());
        }
    }

    private void showBackupSize() {
        Path backupDir = config.getBackupDirectory();
        try {
            if (Files.exists(backupDir)) {
                long totalSize = RecursiveFileUtils.calculateDirectorySize(backupDir);
                System.out.printf("Total size of all backups is: %.2f KB%n", totalSize / 1024.0);
            } else {
                System.out.println("Backup directory does not exist yet.");
            }
        } catch (IOException e) {
            System.err.println("Could not calculate backup size: " + e.getMessage());
        }
    }
}