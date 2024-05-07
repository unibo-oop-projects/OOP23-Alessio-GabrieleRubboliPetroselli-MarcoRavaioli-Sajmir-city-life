package unibo.citysimulation.model.zone;

import unibo.citysimulation.model.transport.TransportLine;

/**
 * The ZoneCreation interface provides methods for creating zones and pairs in a city simulation.
 */
public interface ZoneCreation {
    
    /**
     * Creates a zone with the specified name and coordinates.
     * 
     * @param name the name of the zone
     * @param x1 the x-coordinate of the top-left corner of the zone
     * @param y1 the y-coordinate of the top-left corner of the zone
     * @param x2 the x-coordinate of the bottom-right corner of the zone
     * @param y2 the y-coordinate of the bottom-right corner of the zone
     */
    void createZone(String name, int x1, int y1, int x2, int y2);
    
    /**
     * Creates a pair between two zones with the specified names and a transport line.
     * 
     * @param name1 the name of the first zone
     * @param name2 the name of the second zone
     * @param transportLine the transport line connecting the two zones
     */
    void createPairs(String name1, String name2, TransportLine transportLine);
}

