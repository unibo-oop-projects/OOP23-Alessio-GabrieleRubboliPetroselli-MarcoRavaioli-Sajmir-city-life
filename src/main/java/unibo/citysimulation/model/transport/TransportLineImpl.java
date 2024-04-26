package unibo.citysimulation.model.transport;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a transport line within the city simulation.
 */
public class TransportLineImpl {
    private int capacity;
    private String name;
    private int startHour; // Starting hour of service
    private int endHour; // Ending hour of service
    private int personInLine=0;
    @SuppressWarnings("unused")
    private int durationLine; // Duration Line in minutes
    private Map<Zone, Zone> predefinedLines;
    private Map<Zone, Integer> predefinedDurations;

    public TransportLineImpl(String name, int startHour, int endHour,int capacity,int durationLine) {
        this.predefinedLines = new HashMap<>();
        this.predefinedDurations = new HashMap<>();
        this.name = name;
        this.startHour = startHour;
        this.endHour = endHour;
        this.capacity=capacity;
        this.durationLine=durationLine;
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
    public void addPredefinedLine(Zone originZone, Zone destinationZone, int durationLine) {
        predefinedLines.put(originZone, destinationZone);
        predefinedDurations.put(originZone, durationLine);
    }
    public Map<Zone, Zone> getPredefinedLines() {
        return predefinedLines;
    }

    public Map<Zone, Integer> getPredefinedDurations() {
        return predefinedDurations;
    }
}
