package unibo.citysimulation.model.transport;

// Represents an observer of a transport line.
public class TransportLineObserver {
    private TransportLineImpl transportLine;

    public TransportLineObserver(TransportLineImpl transportLine) {
        this.transportLine = transportLine;
    }

    public void onCongestionUpdate() {
        System.out.println("Congestion of " + transportLine.getName() + " is " + transportLine.getCongestion() + "%");
    }
}
