package unibo.citysimulation.model.transport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransportImpl implements Transport {
    private int congestion;
    private int capacity;
    public Map<Zone, Map<Zone, List<Transport>>> predefinedLines;

    public TransportImpl(int congestion, int capacity) {
        this.congestion = congestion;
        this.capacity = capacity;
        this.predefinedLines = new HashMap<>();
    }

    @Override
    public int getCongestion() {
        return congestion;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    public void addPredefinedLine(Zone originZone, Zone destinationZone) {
        List<Transport> transports = new ArrayList<>();
        transports.add(this);

        predefinedLines.computeIfAbsent(originZone, k -> new HashMap<>())
                .put(destinationZone, transports);
    }
}
