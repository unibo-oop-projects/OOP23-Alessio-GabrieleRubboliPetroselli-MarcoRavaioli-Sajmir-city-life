
package unibo.citysimulation.model.zone;

import java.util.HashMap;
import java.util.Map;
import unibo.citysimulation.model.transport.TransportLine;

public class ZoneTable {
    private Map<Pair<ZoneImpl, ZoneImpl>, TransportLine> zonePairs;

    public ZoneTable() {
        this.zonePairs = new HashMap<>();
    }

    public void addPair(ZoneImpl zone1, ZoneImpl zone2, TransportLine TransportLine) {
        this.zonePairs.put(new Pair<>(zone1, zone2), TransportLine);
        this.zonePairs.put(new Pair<>(zone2, zone1), TransportLine); // to ensure the table works both ways
    }

    public int getMinutesForPair(ZoneImpl zone1, ZoneImpl zone2) {
        return this.zonePairs.get(new Pair<>(zone1, zone2)).getDuration();
    }
}

