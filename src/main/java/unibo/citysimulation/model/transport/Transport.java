package unibo.citysimulation.model.transport;

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
     * Sets the status of the transportation line.
     * 
     * @param status the status to set for the transportation line
     */
    void setStatus(TransportStatus status);

    /**
     * Gets the status of the transportation line.
     * 
     * @return the status of the transportation line
     */
    TransportStatus getStatus();
}
