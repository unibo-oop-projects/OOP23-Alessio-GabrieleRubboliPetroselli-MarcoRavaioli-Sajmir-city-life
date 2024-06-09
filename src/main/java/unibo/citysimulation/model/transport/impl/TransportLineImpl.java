package unibo.citysimulation.model.transport.impl;

import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.model.transport.api.TransportLine;
import unibo.citysimulation.model.zone.Zone;


/**
 * Represents a transport line within the city simulation.
 */
public class TransportLineImpl implements TransportLine {
    private int capacity;
    private final String name;
    private int personInLine;
    private final int duration;
    private final Pair<Zone, Zone> link;
    private static int count_increment = 0;
    private static int count_decrement = 0;
    /**
     * Constructor for TransportLineImpl.
     * @param name
     * @param capacity
     * @param duration
     * @param link
     */
    public TransportLineImpl(final String name, final int capacity, final int duration, final Pair<Zone, Zone> link) {
        this.name = name;
        this.capacity = capacity;
        this.duration = duration;
        this.link = link;
    }
     /**
     * Returns the link between zones for this transport line.
     * This method can be safely overridden in subclasses, if any.
     * Subclasses should ensure that the returned link is not null.
     *
     * @return the link between zones for this transport line
     */
    @Override
    public Pair<Zone, Zone> getLink() {
        return link;
    }
    /**
     * Returns the capacity of this transport line.
     * This method can be safely overridden in subclasses, if any.
     *
     * @return the capacity of this transport line
     */
    @Override
    public int getCapacity() {
        return capacity;
    }
    /**
     * Sets the capacity of this transport line.
     * This method can be safely overridden in subclasses, if any.
     *
     * @param capacity the new capacity of this transport line
     */
    @Override
    public void setCapacity(final int capacity) {
        this.capacity = capacity;
    }
    /**
     * Returns the name of this transport line.
     * This method can be safely overridden in subclasses, if any.
     *
     * @return the name of this transport line
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * Returns the number of people in line.
     * This method can be safely overridden in subclasses, if any.
     *
     * @return the number of people in line
     */
    @Override
    public int getPersonInLine() {
        return personInLine;
    }

    /**
     * Resets the personInLine field.
     */
    @Override
    public void resetPersonInLine() {
        personInLine = 0;
    }

    /**
     * Returns the congestion of the transport line as a percentage of the capacity.
     * This method can be safely overridden in subclasses, if any.
     *
     * @return the congestion of the transport line
     */
    @Override
    public double getCongestion() {
        //System.out.println("Congestion: " + (double) personInLine * 100 / capacity);
        return (double) personInLine * 100 / capacity;
    }
    /**
     * Returns the duration of the transport line.
     * This method can be safely overridden in subclasses, if any.
     *
     * @return the duration of the transport line
     */
    @Override
    public int getDuration() {
        return duration;
    }
    /**
     * Returns the pair of zones that the transport line links.
     * This method can be safely overridden in subclasses, if any.
     *
     * @return the pair of zones that the transport line links
     */
    @Override
    public Pair<Zone, Zone> getLinkedZones() {
        return link;
    }
    /**
     * Increments the number of people in line by one.
     * This method can be safely overridden in subclasses, if any.
     */
    @Override
    public void incrementPersonInLine() {
        //System.out.println("Incrementing person in line " + name + " " + count_increment++);
        personInLine++;
    }
    /**
     * Decrements the number of people in line by one.
     * This method can be safely overridden in subclasses, if any.
     */
    @Override
    public void decrementPersonInLine() {
        //System.out.println("Decrementing person");
        if (personInLine > 0){
            //System.out.println("Decrementing person in line " + name + " " + count_decrement++);
            personInLine--;
        }
    }
}
