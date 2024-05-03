
package unibo.citysimulation.model.zone;

import java.util.HashMap;
import java.util.Map;


import unibo.citysimulation.model.transport.TransportLine;

public class ZoneTable {
    private Map<Pair<Zone, Zone>, TransportLine> zonePairs;

    public ZoneTable() {
        this.zonePairs = new HashMap<>();
    }

    public void addPair(Zone zone1, Zone zone2, TransportLine transportLine) {
        zonePairs.put(new Pair<>(zone1, zone2), transportLine);
    }

    public TransportLine getTransportLine(Zone zone1, Zone zone2) {
        return zonePairs.get(new Pair<>(zone1, zone2));
    }

    public Map<Pair<Zone, Zone>, TransportLine> getZonePairs() {
        return zonePairs;
    }

    public void setZonePairs(Map<Pair<Zone, Zone>, TransportLine> zonePairs) {
        this.zonePairs = zonePairs;
    }

    public int getMinutesForPair(Zone a, Zone b) {
        TransportLine line = getTransportLine(a, b);
        if (line != null) {
            return line.getMinutes();
        } else {
            return -1;
        }
    }

    


}


    

