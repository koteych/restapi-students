package org.restapi;

/**
 * This model is used while serialization
 */
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String group;
    private String dateOfBirth;

    public Student(int id, String firstName, String lastName, String middleName,
        String group, String dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.group = group;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return firstName;
    }
}