
package unibo.citysimulation.model.zone;

import java.util.HashMap;
import java.util.Map;

import unibo.citysimulation.model.transport.TransportLineImpl;
import unibo.citysimulation.utilities.Pair;

import unibo.citysimulation.model.transport.TransportLine;

/**
 * The ZoneTable class represents a table that stores pairs of zones and their corresponding transport lines in a city simulation.
 */
public class ZoneTable {
    private Map<Pair<Zone, Zone>, TransportLineImpl> zonePairs;
    private Map<Pair<Zone, Zone>, TransportLine> zonePairs;

    /**
     * Constructs an empty ZoneTable.
     */
    public ZoneTable() {
        this.zonePairs = new HashMap<>();
    }

    public void addPair(Zone zone1, Zone zone2, TransportLineImpl TransportLine) {
        this.zonePairs.put(new Pair<>(zone1, zone2), TransportLine);
        this.zonePairs.put(new Pair<>(zone2, zone1), TransportLine); // to ensure the table works both ways
    }

    public int getMinutesForPair(Zone zone1, Zone zone2) {
        return this.zonePairs.get(new Pair<>(zone1, zone2)).getDuration();
    }
}
    /**
     * Adds a pair of zones and their corresponding transport line to the ZoneTable.
     * 
     * @param zone1 The first zone in the pair.
     * @param zone2 The second zone in the pair.
     * @param transportLine The transport line connecting the two zones.
     */
    public void addPair(Zone zone1, Zone zone2, TransportLine transportLine) {
        zonePairs.put(new Pair<>(zone1, zone2), transportLine);
    }

    /**
     * Retrieves the transport line connecting the specified pair of zones.
     * 
     * @param zone1 The first zone in the pair.
     * @param zone2 The second zone in the pair.
     * @return The transport line connecting the specified pair of zones, or null if no such line exists.
     */
    public TransportLine getTransportLine(Zone zone1, Zone zone2) {
        return zonePairs.get(new Pair<>(zone1, zone2));
    }

    /**
     * Retrieves the map of zone pairs and their corresponding transport lines.
     * 
     * @return The map of zone pairs and their corresponding transport lines.
     */
    public Map<Pair<Zone, Zone>, TransportLine> getZonePairs() {
        return zonePairs;
    }

    /**
     * Sets the map of zone pairs and their corresponding transport lines.
     * 
     * @param zonePairs The map of zone pairs and their corresponding transport lines.
     */
    public void setZonePairs(Map<Pair<Zone, Zone>, TransportLine> zonePairs) {
        this.zonePairs = zonePairs;
    }

    /**
     * Retrieves the number of minutes it takes to travel between the specified pair of zones.
     * 
     * @param a The first zone in the pair.
     * @param b The second zone in the pair.
     * @return The number of minutes it takes to travel between the specified pair of zones, or -1 if no such line exists.
     */
    public int getMinutesForPair(Zone a, Zone b) {
        TransportLine line = getTransportLine(a, b);
        if (line != null) {
            return line.getMinutes();
        } else {
            return -1;
        }
    }
}


    


    

