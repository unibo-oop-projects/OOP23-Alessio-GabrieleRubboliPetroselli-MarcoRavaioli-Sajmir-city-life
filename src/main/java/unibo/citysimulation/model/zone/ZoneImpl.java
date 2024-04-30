package unibo.citysimulation.model.zone;

/**
 * A class representing a zone in the city.
 */
public class ZoneImpl implements Zone{
    private String name;

    public ZoneImpl(String name) {
        this.name = name;
    }

    // getters and setters for the fields
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
