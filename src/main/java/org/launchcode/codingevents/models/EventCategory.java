package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class EventCategory extends AbstractEntity {
//    @Id               // Removed in 17.5 Studio Abstract Entity
//    @GeneratedValue   // Removed in 17.5 Studio Abstract Entity
//    private int id;   // Removed in 17.5 Studio Abstract Entity

    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    public EventCategory() {}

    public EventCategory(String name) {
        this.name =name;
    }

//    public int getId() {      // Removed in 17.5 Studio Abstract Entity
//        return id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

//    @Override     // Remove in 17.5 Studio Abstract Entity
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        EventCategory that = (EventCategory) o;
//        return getId() == that.getId();
//    }
//
//    @Override     // Remove in 17.5 Studio Abstract Entity
//    public int hashCode() {
//        return Objects.hash(getId());
//    }
}
