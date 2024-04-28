package unibo.citysimulation.model.transport;

/**
 * A class representing a zone in the city.
 */
public class Zone {
    private String name;
    private TransportLine transportLine;

    public Zone(String name, TransportLine transportLine) {
        this.name = name;
        this.transportLine = transportLine;
    }

    // getters and setters for the fields
    public void setName(String name) {
        this.name = name;
    }
    
    public void setTransportLine(TransportLine transportLine) {
        this.transportLine = transportLine;
    }

    public String getName() {
        return name;
    }
    public TransportLine getTransportLine() {
        return transportLine;
    }
}
