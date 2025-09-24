package edu.ccrm.exception;

// Custom checked exception [cite: 88]
public class MaxCreditLimitExceededException extends Exception {
    public MaxCreditLimitExceededException(String message) {
        super(message);
    }
}