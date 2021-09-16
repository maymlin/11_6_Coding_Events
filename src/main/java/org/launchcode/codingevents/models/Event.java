package org.launchcode.codingevents.models;

public class Event {
    private String name;
    private String eventDescription;

    public Event(String eventName, String eventDescription) {
        this.name = eventName;
        this.eventDescription = eventDescription;
    }

    public String getName() {
        return name;
    }

    public void setEventName(String eventName) {
        this.name = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + name + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                '}';
    }
}
