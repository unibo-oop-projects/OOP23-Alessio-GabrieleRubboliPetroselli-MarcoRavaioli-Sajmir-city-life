package unibo.citysimulation.model.transport;

import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;


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
    /**
     * Increments the number of people in line by one.
     */
    void incrementPersonInLine();
    /**
     * Decrements the number of people in line by one.
     */
    void decrementPersonInLine();
    /**
     * Returns the capacity of the transport line.
     *
     * @return the capacity of the transport line
     */
    int getCapacity();
    /**
     * Returns the pair of zones that the transport line links.
     *
     * @return the pair of zones that the transport line links
     */
    Pair<Zone, Zone> getLink();
    /**
     * Returns the number of people in line.
     *
     * @return the number of people in line
     */
    int getPersonInLine();
    /**
     *  Resets the personInLine field.
     * 
     */
    void resetPersonInLine();
    /**
     * Returns the pair of zones that the transport line links.
     *
     * @return the pair of zones that the transport line links
     */
    Pair<Zone, Zone> getLinkedZones();
    /**
     * Sets the capacity of the transport line.
     *
     * @param capacity the new capacity of the transport line
     */
    void setCapacity(int capacity);
}
