package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private final Long id;
    private String name;
    private String address;
    private String capacity;
    private String description;


    @OneToMany(mappedBy = "location")
    private List<Event> events;

    public Location(String name, String address, String capacity, String description) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.description = description;
    }

    public Location() {

        id = 0L;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
