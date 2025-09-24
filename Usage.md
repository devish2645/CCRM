# CCRM Usage Guide

This guide provides instructions on how to compile, run, and interact with the Campus Course & Records Manager (CCRM) application.

---
## 1. Running the Application

**Prerequisites**:
* JDK 11 or higher.

**Instructions**:

1.  **Compile**: From the project's root folder, compile all source files.
    ```bash
    javac -d bin src/edu/ccrm/**/*.java
    ```

2.  **Run**: Execute the main class. The `-ea` flag is required to enable assertions.
    ```bash
    java -ea -cp bin edu.ccrm.CCRM_Main
    ```

---
## 2. Sample Data Files

For the import feature to work, you can create sample data files in a `test-data` folder.

**`test-data/sample_students.csv`**:
```csv
1,24bce10000,new student,newstudent.24bce10000@vitbhopal.ac.in
2,24bce10001,new student2,newstudent2.24bce10001@vitbhopal.ac.in



### ## Sample Interaction Flow

Here is a typical workflow for using the application's menu.

1.  **Add a New Course**:
    * At the Main Menu, select option `2. Manage Courses`.
    * Select `1. Add New Course`.
    * Enter the required details (e.g., Code: `CS102`, Title: `Data Structures`, Credits: `4`, Semester: `SPRING`).

2.  **Add a New Student**:
    * Return to the Main Menu and select option `1. Manage Students`.
    * Select `1. Add New Student`.
    * Enter the student's details (e.g., RegNo: `R25001`, Name: `John Doe`, Email: `john.doe@example.com`).
    * Note the unique Student ID that is generated (e.g., `S...`).

3.  **Enroll the Student**:
    * From the Student Menu, select `3. Enroll Student in Course`.
    * Enter the Student ID for John Doe.
    * Enter the Course Code `CS102`.

4.  **Record a Grade**:
    * From the Student Menu, select `4. Record Grade`.
    * Enter the Student ID and Course Code.
    * Enter a grade (e.g., `A`).

5.  **Print a Transcript**:
    * From the Student Menu, select `5. Print Student Transcript`.
    * Enter the Student ID for John Doe to see his complete academic record and GPA.

6.  **Create a Backup**:
    * Return to the Main Menu and select `4. Create Backup`.
    * A success message will confirm that a timestamped backup has been created in the `backups` folder.

7.  **Exit**:
    * Select `0. Exit` to close the application.
