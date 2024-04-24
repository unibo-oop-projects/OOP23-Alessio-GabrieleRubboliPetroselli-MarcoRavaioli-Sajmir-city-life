package unibo.citysimulation.model.transport;

public interface TransportLine {
    /**
     * Get the name of the transport line.
     * 
     * @return The name of the transport line.
     */
    String getName();

    /**
     * Get the starting hour of service for the transport line.
     * 
     * @return The starting hour of service.
     */
    int getStartHour();

    /**
     * Get the ending hour of service for the transport line.
     * 
     * @return The ending hour of service.
     */
    int getEndHour();

    /**
     * Calculate the congestion level of the transport line.
     * 
     * @return The congestion level as a percentage.
     */
    double getCongestion();
}