package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;



@Controller
@RequestMapping("events")
public class EventController {


    // 11.6.7. Add a Form Handler Method - Text
    private static List<String> events = new ArrayList<>();

    /**
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

    // https://www.youtube.com/watch?v=LnpJcq33uoM
    // 11.6.7. Add a Form Handler Method - Text
    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("events", events);
        return "events/index";
    }

    // https://www.youtube.com/watch?v=lgT962si4eQ
    // 11.6.5. Create and Render a Form - Text
    // lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm() {
        return "events/create";
    }


    // https://www.youtube.com/watch?v=5dtyojtADbk
    // 12.2. Create a Model
    @PostMapping("create")
    public String processCreateEventForm(@RequestParam String eventName,
                                         @RequestParam String eventDescription) {
        events.add(new Event(eventName, eventDescription));
        return "redirect:";
    }

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

//    @PostMapping("create")
//    public String processForm(@RequestParam String event, Model model) {
//
//        return "events/index";
//    }
}
