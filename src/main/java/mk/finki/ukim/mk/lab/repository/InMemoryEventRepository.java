package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Event;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryEventRepository {

    public List<Event> findAll() {
        return DataHolder.events;
    }

    public Event findById(Long id) {
        for (Event event : DataHolder.events) {
            if (event.getId().equals(id)) {
                return event;
            }
        }
        return null;
    }


    public void deleteById(Long id) {
        DataHolder.events.removeIf(event -> event.getId().equals(id));
    }

    public void saveEvent(Event event) {
        DataHolder.events.add(event);
    }


}
