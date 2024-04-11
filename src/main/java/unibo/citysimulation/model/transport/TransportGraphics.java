package unibo.citysimulation.model.transport;

/**
 * An interface for managing the graphical representation of transports.
 */
public interface TransportGraphics extends Transport {
    /**
     * Displays transports on the map.
     */
    void displayTransportsOnMap();

    /**
     * Updates the graphical representation of transports on the map.
     */
    void updateTransportGraphics();
}

