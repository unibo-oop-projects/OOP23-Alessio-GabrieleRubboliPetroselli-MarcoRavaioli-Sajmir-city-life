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
    private Map<Pair<Zone, Zone>, TransportLine> zonePairs; // Changed Zone to ZoneImpl

    /**
     * Constructs an empty ZoneTable.
     */
    public ZoneTable() {
        this.zonePairs = new HashMap<>();
    }

    public void addPair(Zone zone1, Zone zone2, TransportLine transportLine) { // Changed Zone to ZoneImpl
        this.zonePairs.put(new Pair<>(zone1, zone2), transportLine);
        this.zonePairs.put(new Pair<>(zone2, zone1), transportLine); // to ensure the table works both ways
    }

    public int getMinutesForPair(ZoneImpl zone1, ZoneImpl zone2) { // Changed Zone to ZoneImpl
        return this.zonePairs.get(new Pair<>(zone1, zone2)).getDuration();
    }
}


    


    

