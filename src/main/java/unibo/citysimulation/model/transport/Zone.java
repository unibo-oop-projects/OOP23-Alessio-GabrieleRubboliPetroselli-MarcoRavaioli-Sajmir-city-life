package unibo.citysimulation.model.transport;

/**
 * A class representing a zone in the city.
 */
public class Zone {
    private String name;

    public Zone(String name) {
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
