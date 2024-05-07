package unibo.citysimulation.model.zone;
import java.util.List;

/**
 * Represents a zone in the city simulation.
 */
public class Zone {
    private String name;
    private Boundary boundary;

    /**
     * Constructs a new Zone object with the specified name and boundary.
     *
     * @param name     the name of the zone
     * @param boundary the boundary of the zone
     */
    public Zone(String name, Boundary boundary) {
        this.name = name;
        this.boundary = boundary;
    }

    /**
     * Returns the name of the zone.
     *
     * @return the name of the zone
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the zone.
     *
     * @param name the name of the zone
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the boundary of the zone.
     *
     * @return the boundary of the zone
     */
    public Boundary getBoundary() {
        return boundary;
    }
}
