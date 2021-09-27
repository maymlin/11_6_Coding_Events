package org.launchcode.codingevents.models;

//Must first add the following to build.gradle dependencies before javax.validation.constraints.* can be imported:
//        implementation 'org.springframework.boot:spring-boot-starter-validation'

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
public class Event extends AbstractEntity {
//    @Id                       // Removed in 17.5 Studio
//    @GeneratedValue           // Removed in 17.5 Studio
//    private int id;           // Removed in 17.5 Studio
//    private static int nextId = 1;    // Removed in 17.2 Accessing Data

    @NotBlank(message="Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @ManyToOne  // Created in 18.2 one eventCategory for an event
    @NotNull(message="Category is required")
    private EventCategory eventCategory;

    // Created in 18.4 one eventDetails for one event
    @OneToOne(cascade = CascadeType.ALL) // Need to re-watch video https://www.youtube.com/watch?v=0yNIbAcd4ng
    @Valid  // Need to re-watch video https://www.youtube.com/watch?v=0yNIbAcd4ng
    @NotNull
    private EventDetails eventDetails;

    /*
    // Move to EventDetails class in 18.4
    @Size(max = 500, message = "Description too long!")
    private String eventDescription;
     */

    /*
    // Move to EventDetails class in 18.4
    // Added for 13.5 Exercises
    @NotBlank(message="Location is required")
    private String location;
     */

    /*
    // Move to EventDetails class in 18.4
    // Added for 13.5 Exercises
    // Cannot use @Size(min = 1) for some reason, gets error re. Integer validation
    // even though data type int is used
    @Positive(message = "Number of attendees must be one or more")
    private int numberOfAttendees;
     */

    /*
    // Move to EventDetails class in 18.4
    @AssertTrue(message="Button must be checked")
    private boolean mustRegister;
     */

    /*
    // Move to EventDetails class in 18.4
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email. Try again")
    private String contactEmail;
    */

//    private EventType type; // Removed in 18.2

    // Created in 13.4 Thymeleaf Form Tools
    public Event() {
//        this.id = this.nextId;        // Removed in 17.2 Accessing Data
//        nextId++;                     // When we added @Entity @Generated Value and @Id
    }

    // Rewrote in 18.4 Creating a One-to-One Relationship
    public Event(String eventName, EventCategory eventCategory) {
        this.name = eventName;
        this.eventCategory = eventCategory;
    }

    /*
    // Modified constructor in 18.4
    // Added location, numberOfAtteendees and mustRegister for 13.5 Exercises
    public Event(String eventName, String eventDescription, String location,
                 int numberOfAttendees, boolean mustRegister, String contactEmail,
                 EventCategory eventCategory) {
//        this();       // Removed in 17.2 Accessing Data
        this.name = eventName;
        this.eventDescription = eventDescription;
        this.location = location;
        this.numberOfAttendees = numberOfAttendees;
        this.mustRegister = mustRegister;
        this.contactEmail = contactEmail;
        this.eventCategory = eventCategory;
    }
    */


//    public int getId() {      // removed in 17.5 Studio Abstract Entity
//        return id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String eventName) {
        this.name = eventName;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    // Created in 18.4
    public EventDetails getEventDetails() {
        return eventDetails;
    }

    // Created in 18.4
    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    /*
    // Moved to EventDetails in 18.4
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
    */

    // Added for 14.2 Enums in Model Classes
//    public EventType getType() {  // Removed in 18.2
//        return type;
//    }

    // Added for 14.2 Enums in Model Classes
//    public void setType(EventType type) {     // Removed in 18.2
//        this.type = type;
//    }

    /* Removed in 18.4
    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + name + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                '}';
    }
     */
}
