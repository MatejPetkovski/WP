package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Location> locations = null;
    public static List<Event> events = null;

    @PostConstruct
    public void init() {
        locations = new ArrayList<>();
        locations.add(new Location("London", "10 Downing Street", "500", "A historic government building"));
        locations.add(new Location("Paris", "Champ de Mars", "200", "A famous event space near the Eiffel Tower"));
        locations.add(new Location("Berlin", "Berliner Strasse 1", "1000", "A cutting-edge exhibition center"));
        locations.add(new Location("Barcelona", "La Rambla 50", "300", "A vibrant conference venue with seaside views"));
        locations.add(new Location("Amsterdam", "Dam Square 5", "400", "A popular venue for international trade shows"));

        events = new ArrayList<>();
        events.add(new Event("Food Festival", "Enjoy a variety of foods", 4.7, locations.get(4)));
        events.add(new Event("Music Festival", "Live music performances", 3.6, locations.get(1)));
        events.add(new Event("Theater Show", "Watch a great play", 4.2, locations.get(3)));
        events.add(new Event("Art Exhibit", "Local artists showcase", 3.9, locations.get(2)));
        events.add(new Event("Film Screening", "Watch short films", 4.1, locations.get(1)));
        events.add(new Event("Comedy Night", "Stand-up comedy performances", 5.0, locations.get(4)));
        events.add(new Event("Dance Show", "Various dance performances", 2.5, locations.get(0)));
        events.add(new Event("Poetry Night", "Spoken word performances", 4.3, locations.get(3)));
        events.add(new Event("Charity Event", "Dinner for a good cause", 1.8, locations.get(2)));
        events.add(new Event("Tech Talk", "Discussions on new technology", 2.5, locations.get(0)));
    }
}




/*package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final LocationService locationService;

    public EventServiceImpl(EventRepository eventRepository, LocationService locationService) {
        this.eventRepository = eventRepository;
        this.locationService = locationService;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.findAll(); // 
    }

    @Override
    public List<Event> searchByNameAndScore(String searchText, double searchScore) {
        return eventRepository.findAll().stream()
                .filter(event -> event.getName().toLowerCase().contains(searchText.toLowerCase())
                        && event.getPopularityScore() >= searchScore)
                .toList();
    }

    @Override
    public List<Event> searchByName(String searchText) {
        return eventRepository.findAll().stream()
                .filter(event -> event.getName().toLowerCase().contains(searchText.toLowerCase()))
                .toList();
    }

    @Override
    public List<Event> searchByScore(double searchScore) {
        return eventRepository.findAll().stream()
                .filter(event -> event.getPopularityScore() >= searchScore)
                .toList();
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + id));
    }

    @Override
    public List<Event> delete(Long id) {
        eventRepository.deleteById(id);
        return eventRepository.findAll();
    }

    @Override
    public void saveEvent(String name, String description, double popularityScore, Long locationId) {
        Location location = locationService.findById(locationId);
        Event event = new Event(name, description, popularityScore, location);
        eventRepository.save(event);
    }

    @Override
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }
}*/

/*
@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location findById(Long locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location not found with id: " + locationId));
    }
}*/
