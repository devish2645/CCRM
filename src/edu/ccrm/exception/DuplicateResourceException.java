package edu.ccrm.exception;

// Custom checked exception [cite: 84]
public class DuplicateResourceException extends Exception {
    public DuplicateResourceException(String message) {
        super(message);
    }
}