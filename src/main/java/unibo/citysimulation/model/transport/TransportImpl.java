package unibo.citysimulation.model.transport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of the Transport interface representing a mode of transportation within the city simulation.
 */
public class TransportImpl implements Transport {
    private int congestion;
    private int capacity;
    public Map<Zone, Map<Zone, List<Transport>>> predefinedLines;
    private Map<String, Transport> transportMap; // Map associating the name of the physical vehicle to the transport
    private TransportLine transportLine; // Instance representing the current transport line

    /**
     * Constructs a new TransportImpl instance with the specified congestion and capacity.
     *
     * @param congestion The congestion level of the transport.
     * @param capacity   The maximum capacity of the transport.
     */
    public TransportImpl(int congestion, int capacity) {
        this.congestion = congestion;
        this.capacity = capacity;
        this.predefinedLines = new HashMap<>();
        this.transportMap = new HashMap<>();
    }

    /**
     * Retrieves the congestion level of the transport.
     *
     * @return The congestion level.
     */
    @Override
    public int getCongestion() {
        return congestion;
    }

    /**
     * Retrieves the maximum capacity of the transport.
     *
     * @return The maximum capacity.
     */
    @Override
    public int getCapacity() {
        return capacity;
    }

    /**
     * Adds a predefined line between two zones to the transport.
     *
     * @param originZone      The origin zone of the predefined line.
     * @param destinationZone The destination zone of the predefined line.
     */
    public void addPredefinedLine(Zone originZone, Zone destinationZone) {
        List<Transport> transports = new ArrayList<>();
        transports.add(this);

        predefinedLines.computeIfAbsent(originZone, k -> new HashMap<>())
                .put(destinationZone, transports);
    }

    /**
     * Associates a physical transport vehicle with this transport line.
     *
     * @param vehicleName The name of the physical vehicle.
     * @param transport   The transport instance associated with the vehicle.
     */
    public void addTransportVehicle(String vehicleName, Transport transport) {
        transportMap.put(vehicleName, transport);
    }

    /**
     * Retrieves the transport associated with a specific physical vehicle.
     *
     * @param vehicleName The name of the physical vehicle.
     * @return The associated transport instance, or null if not found.
     */
    public Transport getTransportByVehicleName(String vehicleName) {
        return transportMap.get(vehicleName);
    }

    /**
     * Sets the current transport line.
     *
     * @param transportLine The transport line to set.
     */
    public void setTransportLine(TransportLine transportLine) {
        this.transportLine = transportLine;
    }

    /**
     * Retrieves the current transport line.
     *
     * @return An Optional containing the current transport line if present, otherwise an empty Optional.
     */
    public Optional<TransportLine> getTransportLine() {
        return Optional.ofNullable(transportLine);
    }
}
