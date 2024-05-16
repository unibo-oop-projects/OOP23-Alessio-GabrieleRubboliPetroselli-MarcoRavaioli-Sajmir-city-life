package unibo.citylife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.transport.TransportLineImpl;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTable;
import unibo.citysimulation.model.zone.ZoneTableCreation;
import unibo.citysimulation.utilities.Pair;

import java.util.List;

class ZoneTableTest {
    private List<Zone> zones;
    private List<TransportLine> transports;

    @BeforeEach
    void setUp() {
        zones = ZoneFactory.createZonesFromFile();
        transports = TransportFactory.createTransportsFromFile(zones);
        ZoneTableCreation.createAndAddPairs(zones, transports);
    }

    @Test
    void testAddPair() {
        Zone zone1 = zones.get(0);
        Zone zone2 = zones.get(1);
        TransportLine transportLine = new TransportLineImpl("transportTest", 50, 20, new Pair<Zone,Zone>(zone1, zone2));

        ZoneTable.getInstance().addPair(zone1, zone2, transportLine);

        assertEquals(transportLine, ZoneTable.getTransportLine(zone1, zone2), "Transport line should be correctly added");
        assertEquals(transportLine, ZoneTable.getTransportLine(zone2, zone1), "Transport line should be correctly added in reverse");
    }

    @Test
    void testGetTransportLine() {
        Zone zone1 = zones.get(0);
        Zone zone2 = zones.get(1);
        TransportLine expectedLine = transports.get(0);

        ZoneTable.getInstance().addPair(zone1, zone2, expectedLine);

        TransportLine actualLine = ZoneTable.getTransportLine(zone1, zone2);

        assertEquals(expectedLine, actualLine, "Should return the correct transport line");
    }

    @Test
    void testGetTripDuration() {
        TransportLine transportLine = transports.get(0);
        int expectedDuration = transportLine.getDuration();

        int actualDuration = ZoneTable.getTripDuration(transportLine);

        assertEquals(expectedDuration, actualDuration, "Should return the correct trip duration");
    }
}
