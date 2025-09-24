package edu.ccrm.exception;

// Custom unchecked exception [cite: 84]
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}