package unibo.citysimulation.model.zone;

import unibo.citysimulation.model.transport.TransportLine;

import java.util.List;
/**
 * This class creates and adds pairs of zones and transport lines to the zone table.
 */
public final class ZoneTableCreation {
    private static final int TRANSPORT_LINE_0 = 0;
    private static final int TRANSPORT_LINE_1 = 1;
    private static final int TRANSPORT_LINE_2 = 2;
    private static final int TRANSPORT_LINE_3 = 3;
    private static final int TRANSPORT_LINE_4 = 4;
    private static final int TRANSPORT_LINE_5 = 5;
    private static final int TRANSPORT_LINE_6 = 6;
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
        zoneTable.addPair(zones.get(0), zones.get(1), new TransportLine[]{transports.get(TRANSPORT_LINE_0)});
        zoneTable.addPair(zones.get(0), zones.get(2), new TransportLine[]{transports.get(TRANSPORT_LINE_1)});
        zoneTable.addPair(zones.get(0), zones.get(3), new TransportLine[]{transports.get(TRANSPORT_LINE_2)});
        zoneTable.addPair(zones.get(0), zones.get(4), new TransportLine[]{transports.get(TRANSPORT_LINE_3)});
        zoneTable.addPair(zones.get(1), zones.get(2), new TransportLine[]{transports.get(TRANSPORT_LINE_0),
            transports.get(TRANSPORT_LINE_1)});
        zoneTable.addPair(zones.get(1), zones.get(3), new TransportLine[]{transports.get(TRANSPORT_LINE_4),
            transports.get(TRANSPORT_LINE_6)});
        zoneTable.addPair(zones.get(1), zones.get(4), new TransportLine[]{transports.get(TRANSPORT_LINE_4)});
        zoneTable.addPair(zones.get(2), zones.get(3), new TransportLine[]{transports.get(TRANSPORT_LINE_5)});
        zoneTable.addPair(zones.get(2), zones.get(4), new TransportLine[]{transports.get(TRANSPORT_LINE_5),
            transports.get(TRANSPORT_LINE_6)});
        zoneTable.addPair(zones.get(3), zones.get(4), new TransportLine[]{transports.get(TRANSPORT_LINE_6)});
    }
}

