/**
 * An interface for creating transports.
 */
public interface TransportCreation extends TransportManagement {
    /**
     * Creates a new transport.
     *
     * @param type     the type of the transport
     * @param route    the route of the transport
     * @param capacity the capacity of the transport
     */
    void createTransport(String type, String route, int capacity);
}
