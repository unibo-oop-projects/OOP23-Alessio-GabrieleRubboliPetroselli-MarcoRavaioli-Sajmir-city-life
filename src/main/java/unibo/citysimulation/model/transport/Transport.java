package unibo.citysimulation.model.transport;

import java.util.Optional;

/**
 * An interface for modeling a transportation line.
 */
public interface Transport {
    /**
     * Gets the congestion level of the transportation line.
     * 
     * @return the congestion level of the transportation line
     */
    int getCongestion();

    /**
     * Gets the capacity of the transportation line.
     * 
     * @return the capacity of the transportation line
     */
    int getCapacity();

    /**
     * Sets the transport line associated with this transport.
     * 
     * @param transportLine the transport line to set
     */
    void setTransportLine(TransportLine transportLine);

    /**
     * Gets the current transport line associated with this transport, if any.
     * 
     * @return an Optional containing the current transport line, or an empty Optional if none is set
     */
    Optional<TransportLine> getTransportLine();
}
