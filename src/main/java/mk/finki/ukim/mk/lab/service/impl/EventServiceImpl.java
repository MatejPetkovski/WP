package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.InMemoryEventRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final InMemoryEventRepository eventRepository;
    public final LocationService locationService;

    public EventServiceImpl(InMemoryEventRepository eventRepository, LocationService locationService) {
        this.eventRepository = eventRepository;
        this.locationService = locationService;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchByNameAndScore(String searchText, double searchScore) {
        List<Event> result = new ArrayList<>();
        for (Event event : eventRepository.findAll()) {
            if (event.getName().toLowerCase().contains(searchText.toLowerCase()) &&
                    event.getPopularityScore() >= searchScore) {
                result.add(event);
            }
        }
        return result;
    }

    @Override
    public List<Event> searchByName(String searchText) {
        List<Event> result = new ArrayList<>();
        for (Event event : eventRepository.findAll()) {
            if (event.getName().toLowerCase().contains(searchText.toLowerCase())) {
                result.add(event);
            }
        }
        return result;
    }

    @Override
    public List<Event> searchByScore(double searchScore) {
        List<Event> result = new ArrayList<>();
        for (Event event : eventRepository.findAll()) {
            if (event.getPopularityScore() >= searchScore) {
                result.add(event);
            }
        }
        return result;
    }
    @Override
    public Event findById(Long id) {
        Event event = eventRepository.findById(id);
        return event;
    }
    @Override
    public List<Event> delete(Long id) {
        eventRepository.deleteById(id);
        return null;
    }


    public void saveEvent(String name, String description, double popularityScore, Long locationId) {
        Location location = locationService.findById(locationId);
        Event event = new Event(name, description, popularityScore, location);
        eventRepository.saveEvent(event);

    }


    public void saveEvent(Event event) {
        //eventRepository.saveEvent(event);
        if (event.getId() != null) {
            // Event already exists, update it
            Event existingEvent = eventRepository.findById(event.getId());
            if (existingEvent != null) {
                // Update the existing event's fields with the new values
                existingEvent.setName(event.getName());
                existingEvent.setDescription(event.getDescription());
                existingEvent.setPopularityScore(event.getPopularityScore());
                existingEvent.setLocation(event.getLocation());  // Update location if needed


            }
        }
    }
}