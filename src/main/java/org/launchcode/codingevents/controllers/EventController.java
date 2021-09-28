package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.data.TagRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.launchcode.codingevents.models.EventType;
import org.launchcode.codingevents.models.Tag;
import org.launchcode.codingevents.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

//import java.util.ArrayList;
//import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired  // Spring Boot should auto-populate this field // dependency injection
    private EventRepository eventRepository;    // Created for 17.3 Repositories

    @Autowired
    private EventCategoryRepository eventCategoryRepository;    // Created for 18.2

    @Autowired
    private TagRepository tagRepository; // Created for 18.5.5. Many-to-Many Forms and Data Transfer Objects

    // findAll, save, findById

//    replaced by EventData class in 12.3.3 Create a Data Layer
//    private static List<Event> events = new ArrayList<>();

    // https://www.youtube.com/watch?v=RLykFBY9Rys
    // 18.3. Creating a One-to-Many Relationship
    @GetMapping
    public String displayEvents(@RequestParam(required=false) Integer categoryId, Model model) {
        if (categoryId == null) {
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());
        } else {
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Category ID: " + categoryId);
            } else {
                EventCategory category = result.get();
                model.addAttribute("title", "Events in category: " + category.getName());
                model.addAttribute("events", category.getEvents());
            }
        }

        return "events/index";
    }

    /*
    // https://www.youtube.com/watch?v=RLykFBY9Rys
    // Replaced with displayEvents in 18.3. Creating a One-to-Many Relationship
    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
//        model.addAttribute("events", EventData.getAll());     // replaced with eventRepository in 17.3
        model.addAttribute("events", eventRepository.findAll());
        return "events/index";
    }
     */

    /*
    // https://www.youtube.com/watch?v=8AQtYZ_q57M
    // Re-factored in 12.3 Models and Data
    // https://www.youtube.com/watch?v=LnpJcq33uoM
    // 11.6.7. Add a Form Handler Method - Text
    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");  // Added in 12.2
        model.addAttribute("events", events);
        return "events/index";
    }
     */

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute("event", new Event());
//        model.addAttribute("types", EventType.values());    // Added for 14.2 Enums in Model Classes // Removed for
//        18.2
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/create";
    }

    /*
    // Replaced by new displayCreateEventForm in 13.4 ThymleafForm Tools
    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        return "events/create";
    }
     */

    // 13.3 Validating Models in a Controller
    // 12.4 Model-Binding
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent,
                                         Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
//            model.addAttribute("errorMsg", "Bad data!");
            return "events/create";
        }
//        EventData.add(newEvent);  // replaced in 17.3 by eventRepository.save()
        eventRepository.save(newEvent);
        return "redirect:";
    }

    /*
    //Re-write method in 12.4 Model-Binding
    // https://www.youtube.com/watch?v=5dtyojtADbk
    // 12.2. Create a Model
    @PostMapping("create")
    public String processCreateEventForm(@RequestParam String eventName,
                                         @RequestParam String eventDescription) {

        EventData.add(new Event(eventName, eventDescription));
        return "redirect:";
    }
    */

    // 12.3.5. Delete an Event - Video
    // https://www.youtube.com/watch?v=orsBBbDaJMM
    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("tittle", "Delete Events");
//        model.addAttribute("events", EventData.getAll()); // replaced in 17.3 by eventRepository.findAll();
        model.addAttribute("events", eventRepository.findAll());

        return "events/delete";
    }

    // 12.3.5. Delete an Event - Video
    // https://www.youtube.com/watch?v=orsBBbDaJMM
    @PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required=false) int[] eventIds) {
        if (eventIds != null) {
            for (int id: eventIds) {
//                EventData.remove(id);     // replaced in 17.3 by eventRepository.deleteById();
                eventRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

    // 18.5.1. Persistent Tags
    // https://github.com/LaunchCodeEducation/coding-events-demo/blob/add-tags/src/main/java/org/launchcode/codingevents/controllers/EventController.java
    @GetMapping("detail")
    public String displayEventDetails(@RequestParam Integer eventId, Model model) {
        Optional<Event> result = eventRepository.findById(eventId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Event ID: " + eventId);
        } else {
            Event event = result.get();
            model.addAttribute("title", event.getName() + " Details");
            model.addAttribute("event", event);
            model.addAttribute("tags", event.getTags());
        }
        return "events/detail";
    }

    // 18.5.5. Many-to-Many Forms and Data Transfer Objects
    // https://www.youtube.com/watch?v=1qMaEv_CJ6k
    // responds to /events/add-tag?eventId=13 for example
    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer eventId, Model model) {
        Optional<Event> result = eventRepository.findById(eventId);
        Event event = result.get();
        model.addAttribute("title", "Add Tag to " + event.getName());
        model.addAttribute("tags", tagRepository.findAll());
        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTag", eventTag);

        return "events/add-tag";
    }

    // 18.5.5. Many-to-Many Forms and Data Transfer Objects
    // https://www.youtube.com/watch?v=1qMaEv_CJ6k
    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag,
                                    Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Tag to: Error");
            return "events/add-tag";
        } else {
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();
            if (!event.getTags().contains(tag)) {
                event.addTag(tag);
                eventRepository.save(event);
            }
            return "redirect:detail?eventId=" + event.getId();
        }
    }

    /*
    // Replaced by a new events List variable in 12.2 Create a Model
    // 11.6.7. Add a Form Handler Method - Text
    private static List<String> events = new ArrayList<>();
    */

    /*
    // https://www.youtube.com/watch?v=hmgxMOf51JU
    // 11.6.2 Coding Events Setup - Video
    // replaced in "11.6.7. Add a Form Handler Method - Text" below
    @GetMapping
    public String displayAllEvents(Model model) {
        List<String> events = new ArrayList<>(
                List.of("Coding With Pride",
                        "Strange Loop",
                        "Apple WWDC",
                        "SpringOne Platform")
        );
        model.addAttribute("events", events);

        return "events/index";
    }
    */

    /*
    // replaced by processCreateEventForm() in 12.2 Create a Model to accommodate models
    // https://www.youtube.com/watch?v=LnpJcq33uoM
    // 11.6.7. Add a Form Handler Method - Text
    // lives at /events/create  -> Okay because renderCreateEventForm handles GET event
    // and this controller handles POST event
    @PostMapping("create")
    public String createEvent(@RequestParam String eventName) {
        events.add(eventName);
        return "redirect:"; // return a 300 redirect response
    }
     */

    /*
    // replaced in 12.2 Create a Model by displayCreateEventForm()
    // https://www.youtube.com/watch?v=lgT962si4eQ
    // 11.6.5. Create and Render a Form - Text
    // lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm() {
        return "events/create";
    }
     */

//    @PostMapping("create")
//    public String processForm(@RequestParam String event, Model model) {
//
//        return "events/index";
//    }
}
