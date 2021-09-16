package org.launchcode.codingevents.data;

// https://www.youtube.com/watch?v=8AQtYZ_q57M
// 12.3.3. Create a Data Layer - Video

import org.launchcode.codingevents.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EventData {

    // need a place to put events

    public static final Map<Integer, Event> events = new HashMap<>();

    // get all events

    public static Collection<Event> getAll() {
        return events.values();
    }

    // get a single event

    public static Event getById(int id) {
        return events.get(id);
    }

    // add an event

    public static void add(Event event) {
        events.put(event.getId(), event);
    }

    // remove an event

    public static void remove(int id) {
        events.remove(id);
    }
}
