
package unibo.citysimulation.model.zone;

import java.util.HashMap;
import java.util.Map;

import unibo.citysimulation.model.transport.TransportLineImpl;
import unibo.citysimulation.utilities.Pair;

public class ZoneTable {
    private Map<Pair<Zone, Zone>, TransportLineImpl> zonePairs;

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


package unibo.citysimulation.model.zone;

import java.util.HashMap;
import java.util.Map;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.Pair;

public class ZoneTable {
    private static ZoneTable instance;
    private Map<Pair<Zone, Zone>, TransportLine> zonePairs;

    private ZoneTable() {
        zonePairs = new HashMap<>();
    }

    public static ZoneTable getInstance() {
        if (instance == null) {
            instance = new ZoneTable();
        }
        return instance;
    }

    public void addPair(Zone zone1, Zone zone2, TransportLine transportLine) {
        zonePairs.put(new Pair<>(zone1, zone2), transportLine);
        zonePairs.put(new Pair<>(zone2, zone1), transportLine); // to ensure the table works both ways
    }

    public TransportLine getTransportLine(Zone zone1, Zone zone2) {
        return zonePairs.get(new Pair<>(zone1, zone2));
    }
}
