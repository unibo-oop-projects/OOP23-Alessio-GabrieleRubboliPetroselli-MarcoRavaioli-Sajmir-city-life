package unibo.citysimulation.model.transport;

/**
 * A class representing a zone in the city.
 */
public class Zone {
    private String name;

    /**
     * Constructs a new Zone with the specified name.
     *
     * @param name the name of the zone
     */
    public Zone(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the zone.
     *
     * @return the name of the zone
     */
    public String getName() {
        return name;
    }
}
