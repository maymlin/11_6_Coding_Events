package org.launchcode.codingevents.models;

//Must first add the following to build.gradle dependencies before javax.validation.constraints.* can be imported:
//        implementation 'org.springframework.boot:spring-boot-starter-validation'

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private int id;
//    private static int nextId = 1;    // Removed in 17.2 Accessing Data

    @NotBlank(message="Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @Size(max = 500, message = "Description too long!")
    private String eventDescription;

    // Added for 13.5 Exercises
    @NotBlank(message="Location is required")
    private String location;

    // Added for 13.5 Exercises
    // Cannot use @Size(min = 1) for some reason, gets error re. Integer validation
    // even though data type int is used
    @Positive(message = "Number of attendees must be one or more")
    private int numberOfAttendees;

    @AssertTrue(message="Button must be checked")
    private boolean mustRegister;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email. Try again")
    private String contactEmail;

    private EventType type;

    // Created in 13.4 Thymeleaf Form Tools
    public Event() {
//        this.id = this.nextId;        // Removed in 17.2 Accessing Data
//        nextId++;                     // When we added @Entity @Generated Value and @Id
    }

    // Added location, numberOfAtteendees and mustRegister for 13.5 Exercises
    public Event(String eventName, String eventDescription, String location,
                 int numberOfAttendees, boolean mustRegister, String contactEmail,
                 EventType type) {
//        this();       // Removed in 17.2 Accessing Data
        this.name = eventName;
        this.eventDescription = eventDescription;
        this.location = location;
        this.numberOfAttendees = numberOfAttendees;
        this.mustRegister = mustRegister;
        this.contactEmail = contactEmail;
        this.type = type;
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

    // Added for 13.5 Exercises
    public String getLocation() {
        return location;
    }

    // Added for 13.5 Exercises
    public void setLocation(String location) {
        this.location = location;
    }

    // Added for 13.5 Exercises
    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    // Added for 13.5 Exercises
    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    // Added for 13.5 Exercises
    public boolean isMustRegister() {
        return mustRegister;
    }

    // Added for 13.5 Exercises
    public void setMustRegister(boolean mustRegister) {
        this.mustRegister = mustRegister;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    // Added for 14.2 Enums in Model Classes
    public EventType getType() {
        return type;
    }

    // Added for 14.2 Enums in Model Classes
    public void setType(EventType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + name + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                '}';
    }
}
