package unibo.citysimulation.model.transport;

import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.model.zone.Zone;
import java.awt.Color;


/**
 * Represents a transport line within the city simulation.
 */
public class TransportLineImpl implements TransportLine {
    private int capacity;
    private String name;
    private int personInLine=0;
    private int duration;
    private Pair<Zone,Zone> link;
    private double congestion;
    private Color color=Color.GREEN;

    public TransportLineImpl(String name,int capacity,int duration, Pair<Zone,Zone> link) {
        this.name = name;
        this.capacity=capacity;
        this.duration=duration;
        this.link = link;
    }
    public Pair<Zone,Zone> getLink(){
        return link;
    }
    public int getCapacity(){
        return capacity;
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
        updateCongestion((double)(personInLine*100/capacity));
        updateColor();
    }

    @Override
    public void decrementPersonInLine() {
        personInLine--;
        updateCongestion((double)(personInLine*100/capacity));
        updateColor();

    }

    public void updateCongestion(double newCongestion) {
        this.congestion = newCongestion;
        updateColor();
    }
    
    public void updateColor() {
        if (congestion < 10) {
            color = Color.GREEN;
        } else if (congestion < 30) {
            color = Color.YELLOW;
        } else {
            color = Color.RED;
        }
    }

    public Color getColor() {
        return color;
    }
    
}
