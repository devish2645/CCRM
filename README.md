# CCRM
Campus Course & Records Manager (CCRM) is a console-based Java application designed for an educational institute to manage student and course records. It provides a command-line interface (CLI) for administrators to handle core academic operations, including: Managing students includes making, editing, and publishing their profiles.

Course management is the process of developing, maintaining, and looking for courses.

Grading and Enrollment: registering students for classes and keeping track of their grades.

File operations include importing beginning data and exporting the status of the application to files that resemble CSV files.

Timestamped backups of all application data are made for preservation purposes.

Built with Java SE, this project showcases a variety of fundamental Java ideas, such as Streams API, modern file I/O with NIO.2, Object-Oriented Programming (OOP), and fundamental design patterns. 


=### ## How to Run

**Prerequisites**:
* JDK 11 or higher.

**Instructions**:

1.  **Compile**: From the project's root folder, compile all source files.
    ```bash
    javac -d bin src/edu/ccrm/**/*.java
    ```

2.  **Run**: Execute the main class from the root folder. The `-ea` flag is required.
    ```bash
    java -ea -cp bin edu.ccrm.CCRM_Main
    ```

---

### ## Java Platform Explained

* **Evolution of Java**:
    * **1995**: Java 1.0 released.
    * **2004**: J2SE 5.0 added major features like Generics and Enums.
    * **2014**: Java SE 8 introduced Lambdas and the Streams API.
    * **2018**: Java SE 11 became a key Long-Term Support (LTS) version.

* **Java Editions**:
    * **Java SE (Standard Edition)**: The core platform for desktop and server applications.
    * **Java EE (Enterprise Edition)**: Built on top of SE for large-scale, web, and enterprise applications.
    * **Java ME (Micro Edition)**: A lightweight version for mobile and embedded devices.

* **Java Architecture**:
    * **JVM (Java Virtual Machine)**: Executes compiled Java bytecode.
    * **JRE (Java Runtime Environment)**: Provides the JVM and libraries needed to *run* Java applications.
    * **JDK (Java Development Kit)**: Includes the JRE plus tools needed to *develop* applications, like the compiler.

---

### ## Features Mapping

| Concept / Requirement | File(s) / Location(s) |
| --- | --- |
| **OOP (All Pillars)** | `domain/` package, e.g., `Student` extends `Person` |
| **Design Pattern: Singleton** | `config/AppConfig.java` |
| **Design Pattern: Builder** | `domain/Course.java` |
| **Custom Exceptions** | `exception/` package |
| **File I/O (NIO.2)** | `io/FileService.java`, `io/BackupService.java` |
| **Streams API** | `service/CourseService.java` (filtering) |
| **Date/Time API** | `domain/Student.java` (enrollment date) |
| **Recursion** | `util/RecursiveFileUtils.java` |


