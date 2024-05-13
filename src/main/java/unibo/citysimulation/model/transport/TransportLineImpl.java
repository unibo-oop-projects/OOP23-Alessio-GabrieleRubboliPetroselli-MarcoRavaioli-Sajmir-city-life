package unibo.citysimulation.model.transport;

import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.model.zone.Zone;

/**
 * Represents a transport line within the city simulation.
 */
public class TransportLineImpl implements TransportLine {
    private int capacity;
    private String name;
    private int personInLine=0;
    private int duration;
    private Pair<Zone,Zone> link;

    public TransportLineImpl(String name,int capacity,int duration, Pair<Zone,Zone> link) {
        this.name = name;
        this.capacity=capacity;
        this.duration=duration;
        this.link = link;
    }

    // Getter and setter methods for other fields, if needed
    public String getName() {
        return name;
    }

    public int getPersonInLine(){
        return personInLine;
    }
    public double getCongestion(){
        return (double)(personInLine*100/capacity);
    }
    public int getDuration(){
        return duration;
    }

    public Pair<Zone,Zone> getLinkedZones(){
        return link;
    }

    @Override
    public void incrementPersonInLine() {
        personInLine++;
    }

    @Override
    public void decrementPersonInLine() {
        personInLine--;
    }
}
