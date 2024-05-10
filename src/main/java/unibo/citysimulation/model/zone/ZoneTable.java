
package unibo.citysimulation.model.zone;

import java.util.HashMap;
import java.util.Map;

import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.Pair;

/**
 * The ZoneTable class represents a table that stores pairs of zones and their corresponding transport lines in a city simulation.
 */
public class ZoneTable {
    private Map<Pair<Zone, Zone>, TransportLine> zonePairs;

    /**
     * Constructs an empty ZoneTable.
     */
    public ZoneTable() {
        this.zonePairs = new HashMap<>();
    }

    public void addPair(Zone zone1, Zone zone2, TransportLine TransportLine) {
        this.zonePairs.put(new Pair<>(zone1, zone2), TransportLine);
        this.zonePairs.put(new Pair<>(zone2, zone1), TransportLine); // to ensure the table works both ways
    }

    public TransportLine getTransportLine(Zone zone1, Zone zone2) {
        return this.zonePairs.get(new Pair<>(zone1, zone2));
    }
}


    

