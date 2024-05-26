package unibo.citysimulation.model.zone;

import unibo.citysimulation.model.transport.TransportLine;

import java.util.List;

public final class ZoneTableCreation {
    
    private ZoneTableCreation() {
    }

    /**
     * Creates and adds pairs of zones and transport lines to the zone table.
     * 
     * @param zones      the list of zones
     * @param transports the list of transport lines
     */
    public static void createAndAddPairs(final List<Zone> zones, final List<TransportLine> transports) {
        final ZoneTable zoneTable = ZoneTable.getInstance();
        zoneTable.addPair(zones.get(0), zones.get(1), new TransportLine[]{transports.get(0)});
        zoneTable.addPair(zones.get(0), zones.get(2), new TransportLine[]{transports.get(1)});
        zoneTable.addPair(zones.get(0), zones.get(3), new TransportLine[]{transports.get(2)});
        zoneTable.addPair(zones.get(0), zones.get(4), new TransportLine[]{transports.get(3)});
        zoneTable.addPair(zones.get(1), zones.get(2), new TransportLine[]{transports.get(0),
            transports.get(1)});
        zoneTable.addPair(zones.get(1), zones.get(3), new TransportLine[]{transports.get(4),
            transports.get(6)});
        zoneTable.addPair(zones.get(1), zones.get(4), new TransportLine[]{transports.get(4)});
        zoneTable.addPair(zones.get(2), zones.get(3), new TransportLine[]{transports.get(5)});
        zoneTable.addPair(zones.get(2), zones.get(4), new TransportLine[]{transports.get(5),
            transports.get(6)});
        zoneTable.addPair(zones.get(3), zones.get(4), new TransportLine[]{transports.get(6)});
    }
}

