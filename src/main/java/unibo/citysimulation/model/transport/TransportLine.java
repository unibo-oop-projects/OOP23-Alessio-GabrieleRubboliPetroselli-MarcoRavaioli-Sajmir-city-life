package unibo.citysimulation.model.transport;


/**
 * Represents a transport line within the city simulation.
 */
public class TransportLine {
    private int capacity;
    private String name;
    private int personInLine=0;
    private int duration;

    public TransportLine(String name,int capacity,int duration) {
        this.name = name;
        this.capacity=capacity;
        this.duration=duration;
    }

    // Getter and setter methods for other fields, if needed
    public String getName() {
        return name;
    }
    public double getCongestion(){
        return (double)(personInLine*100/capacity);
    }
    public int getDuration(){
        return duration;
    }
}
