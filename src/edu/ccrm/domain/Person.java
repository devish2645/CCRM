package edu.ccrm.domain;

public abstract class Person {
    private final String id;
    private String fullName;
    private String email;

    public Person(String id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Abstract method to be implemented by subclasses
    public abstract String getProfileDetails();

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + fullName + ", Email: " + email;
    }
}