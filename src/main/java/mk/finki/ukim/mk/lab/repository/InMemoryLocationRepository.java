package mk.finki.ukim.mk.lab.repository;


import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class InMemoryLocationRepository {
    private final List<Location> locations;

    public InMemoryLocationRepository() {
        this.locations = new ArrayList<>();
        init();
    }

    private void init() {
        locations.add(new Location("London", "10 Downing Street", "500", "A historic government building"));
        locations.add(new Location("Paris", "Champ de Mars", "200", "A famous event space near the Eiffel Tower"));
        locations.add(new Location("Berlin", "Berliner Strasse 1", "1000", "A cutting-edge exhibition center"));
        locations.add(new Location("Barcelona", "La Rambla 50", "300", "A vibrant conference venue with seaside views"));
        locations.add(new Location("Amsterdam", "Dam Square 5", "400", "A popular venue for international trade shows"));
    }

    public List<Location> findAll() {


        return new ArrayList<>(locations);
    }

    public Location findById(Long locationId) {
        return locations.stream().filter(location -> Objects.equals(location.getId(), locationId)).findFirst().get();
    }
}