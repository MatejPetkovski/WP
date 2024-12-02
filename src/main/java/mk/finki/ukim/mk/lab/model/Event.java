package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    private String name;
    private String description;
    private double popularityScore;



    @ManyToOne
    private Location location;

    public Event(String name, String description, double popularityScore, Location location) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
    }

    public Event() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPopularityScore() {
        return popularityScore;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setPopularityScore(double popularityScore) {
        this.popularityScore = popularityScore;
    }
}