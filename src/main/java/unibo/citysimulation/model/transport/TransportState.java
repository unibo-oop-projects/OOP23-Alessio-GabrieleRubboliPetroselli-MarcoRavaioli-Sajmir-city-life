package unibo.citysimulation.model.transport;

/**
 * An interface for monitoring the state of transports.
 */
public interface TransportState extends TransportLine {
    /**
     * Monitors the state of transports.
     */
    void monitorTransportState();
}
