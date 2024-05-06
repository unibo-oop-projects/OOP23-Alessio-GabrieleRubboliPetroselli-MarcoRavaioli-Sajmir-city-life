package unibo.citysimulation.model.transport;

/**
 * Represents a transport line within the city simulation.
 * Each transport line has a name, capacity, and duration.
 * It also maintains the number of people in line and can calculate the congestion.
 */
public interface TransportLine {
    /**
     * Returns the name of the transport line.
     *
     * @return the name of the transport line
     */
    String getName();

    /**
     * Returns the congestion of the transport line as a percentage of the capacity.
     *
     * @return the congestion of the transport line
     */
    double getCongestion();

    /**
     * Returns the duration of the transport line.
     *
     * @return the duration of the transport line
     */
    int getDuration();
}