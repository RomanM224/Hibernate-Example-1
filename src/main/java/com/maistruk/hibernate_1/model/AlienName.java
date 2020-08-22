package com.maistruk.hibernate_1.model;

import javax.persistence.Embeddable;

@Embeddable
public class AlienName {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "AlienName [firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
