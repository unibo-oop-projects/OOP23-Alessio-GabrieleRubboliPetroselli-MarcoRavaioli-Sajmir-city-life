package unibo.citysimulation.model.zone;

import unibo.citysimulation.model.transport.TransportLine;

import java.util.List;

public class ZoneTableCreation {
    public static void createAndAddPairs(List<Zone> zones, List<TransportLine> transports) {
        ZoneTable zoneTable = ZoneTable.getInstance();
        
        zoneTable.addPair(zones.get(0), zones.get(1), new TransportLine[]{transports.get(0)});
        zoneTable.addPair(zones.get(0), zones.get(2), new TransportLine[]{transports.get(1)});
        zoneTable.addPair(zones.get(0), zones.get(3), new TransportLine[]{transports.get(2)});
        zoneTable.addPair(zones.get(0), zones.get(4), new TransportLine[]{transports.get(3)});
        zoneTable.addPair(zones.get(1), zones.get(2), new TransportLine[]{transports.get(0),
            transports.get(1)});
        zoneTable.addPair(zones.get(1), zones.get(3), new TransportLine[]{transports.get(0),
            transports.get(2)});
        zoneTable.addPair(zones.get(1), zones.get(4), new TransportLine[]{transports.get(4)});
        zoneTable.addPair(zones.get(2), zones.get(3), new TransportLine[]{transports.get(5)});
        zoneTable.addPair(zones.get(2), zones.get(4), new TransportLine[]{transports.get(1),
            transports.get(3)});
        zoneTable.addPair(zones.get(3), zones.get(4), new TransportLine[]{transports.get(6)});
    }
}

