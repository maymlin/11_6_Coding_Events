package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;

// https://www.youtube.com/watch?v=0yNIbAcd4ng
// Created in 18.4 Creating a One-to-One Relationship

@Entity
public class EventDetails extends AbstractEntity {

    /*
    // Established inverse relationship but won't create additional fields
    // Per video, this application doesn't require this
    @OneToOne(mappedBy="eventDetails")
    private Event event;
     */

    @Size(max = 500, message = "Description too long!")
    private String eventDescription;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email. Try again")
    private String contactEmail;

    @NotBlank(message="Location is required")
    private String location;

    @Positive(message = "Number of attendees must be one or more")
    private int numberOfAttendees;

    @AssertTrue(message="Button must be checked")
    private boolean mustRegister;
    
    public EventDetails() {}

    public EventDetails(String eventDescription, String contactEmail,
                        String location, int numberOfAttendees,
                        boolean mustRegister) {
        this.eventDescription = eventDescription;
        this.contactEmail = contactEmail;
        this.location = location;
        this.numberOfAttendees = numberOfAttendees;
        this.mustRegister = mustRegister;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    public boolean isMustRegister() {
        return mustRegister;
    }

    public void setMustRegister(boolean mustRegister) {
        this.mustRegister = mustRegister;
    }
}
