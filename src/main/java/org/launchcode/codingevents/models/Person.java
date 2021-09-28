package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Person extends AbstractEntity {

    @Size(min=2, max=20, message="First name must be 2 to 20 characters long")
    @NotBlank(message="First name is required")
    private String firstName;

    @Size(min=2, max=50, message="Last name must be 2 to 50 characters long")
    @NotBlank(message="Last name is required")
    private String lastName;

    @Email
    @NotNull
    private String email;
    
    @Size(min=2, max=50, message="Password must be 2 to 50 characters long")
    @NotNull
    private String password;

    public Person() { }

    public Person(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
