package org.launchcode.codingevents.models;

//Must first add the following to build.gradle dependencies before javax.validation.constraints.* can be imported:
//        implementation 'org.springframework.boot:spring-boot-starter-validation'

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class Event {
    private int id;
    private static int nextId = 1;

    @NotBlank(message="Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @Size(max = 500, message = "Description too long!")
    private String eventDescription;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email. Try again")
    private String contactEmail;

    // Created in 13.4 Thymeleaf Form Tools
    public Event() {
        this.id = this.nextId;
        nextId++;
    }

    public Event(String eventName, String eventDescription, String contactEmail) {
        this();
        this.name = eventName;
        this.eventDescription = eventDescription;
        this.contactEmail = contactEmail;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String eventName) {
        this.name = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + name + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                '}';
    }
}
