package unibo.citysimulation.model.transport;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a transport line within the city simulation.
 */
public class TransportLine {
    private int capacity;
    private String name;
    private int startHour; // Starting hour of service
    private int endHour; // Ending hour of service
    private int personInLine=0;

    public TransportLine(String name, int serviceFrequency, int startHour, int endHour,int capacity) {
        this.name = name;
        this.startHour = startHour;
        this.endHour = endHour;
        this.capacity=capacity;
    
    }

    // Getter and setter methods for other fields, if needed
    public String getName() {
        return name;
    }
    public int getStartHour() {
        return startHour;
    }
    public int getEndHour() {
        return endHour;
    }

    public double getCongestion(){
        return (double)(personInLine*100/capacity);
    }
}
