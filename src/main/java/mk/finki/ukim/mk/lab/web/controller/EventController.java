package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.service.impl.EventServiceImpl;
import mk.finki.ukim.mk.lab.model.Location;


import java.util.List;

import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EventController {

    private final EventServiceImpl eventService;
    private final LocationService locationService;

    public EventController(EventServiceImpl eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping({"/", "/event-list", "/events"})
    public String getEventPage(
            @RequestParam(value = "searchText", required = false) String searchText,
            @RequestParam(value = "searchScore", required = false) String searchScoreParam,
            Model model) {

        List<Event> eventList;
        List<Location> locationList = locationService.findAll();

        Double searchScore = null;
        if (searchScoreParam != null && !searchScoreParam.trim().isEmpty()) {
            try {
                searchScore = Double.parseDouble(searchScoreParam);
            } catch (NumberFormatException e) {
                searchScore = null;
            }
        }

        if (searchText != null && !searchText.isEmpty() && searchScore != null) {
            eventList = eventService.searchByNameAndScore(searchText, searchScore);
        } else if (searchText != null && !searchText.isEmpty()) {
            eventList = eventService.searchByName(searchText);
        } else if (searchScore != null) {
            eventList = eventService.searchByScore(searchScore);
        } else {
            eventList = eventService.listAll();
        }

        model.addAttribute("events", eventList);
        model.addAttribute("locations", locationList);
        return "listEvents";
    }


    @GetMapping("/events/edit-form/{eventId}")
    public String getEditEventForm(@PathVariable Long eventId, Model model) {
        Event event = eventService.findById(eventId);

        List<Location> locations = locationService.findAll();

        model.addAttribute("event", event);
        model.addAttribute("locations", locations);

        return "add-event";
    }

    @PostMapping("/events/edit-form/{eventId}")
    public String editEvent(@PathVariable Long eventId,
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam double popularityScore,
                            @RequestParam Long locationId) {

        Event existingEvent = eventService.findById(eventId);

        existingEvent.setName(name);
        existingEvent.setDescription(description);
        existingEvent.setPopularityScore(popularityScore);

        Location location = locationService.findById(locationId);
        if (location != null) {
            existingEvent.setLocation(location);
        }

        eventService.saveEvent(existingEvent);

        return "redirect:/event-list";
    }

    @PostMapping("/evnts/delete/{id}")
    public String deleteEvent(@PathVariable Long id, Model model){
        eventService.delete(id);

        return "redirect:/event-list";
    }

    @PostMapping("/events/add-form")
    public String saveEvent(@RequestParam String name, @RequestParam String description,
                            @RequestParam double popularityScore, @RequestParam Long locationId,
                            Model model){
        eventService.saveEvent(name, description, popularityScore, locationId);

        return "redirect:/event-list";
    }



    @GetMapping("/events/add-form")
    public String showAddEventPage(Model model) {
        model.addAttribute("locations", locationService.findAll()); // Populate locations for the dropdown
        return "add-event"; // Render addEvent.html
    }
}
