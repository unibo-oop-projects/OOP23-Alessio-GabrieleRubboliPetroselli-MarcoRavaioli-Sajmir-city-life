package unibo.citysimulation.model.zone;

import java.util.List;

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
    Zone createZone(List<Object> infos);
    
   
}

