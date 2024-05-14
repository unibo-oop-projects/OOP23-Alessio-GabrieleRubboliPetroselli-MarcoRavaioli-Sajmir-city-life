package unibo.citylife;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.BusinessFactory;
import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.transport.TransportLineImpl;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTable;
import unibo.citysimulation.utilities.Pair;
import java.util.List;


class ZoneTableTest {
    //add the list for making the test
    private List<Zone> zones = ZoneFactory.createZonesFromFile();
    private List<TransportLine> transports = TransportFactory.createTransportsFromFile(zones);
    private List<Business> businesses = BusinessFactory.createBusinesses(zones);

    @Test
    void testAddPairAndGetTransportLine() {
        // Ensure there are at least two zones and one transport line
        if (zones.size() < 2 || transports.size() < 1) {
            fail("Not enough data to run test");
        }
    
        Zone zone1 = zones.get(0);
        Zone zone2 = zones.get(1);
        TransportLine transportLine = transports.get(0);
    
        ZoneTable zoneTable = ZoneTable.getInstance();
        zoneTable.addPair(zone1, zone2, transportLine);
    
        TransportLine actualTransportLine = zoneTable.getTransportLine(zone1, zone2);
        assertSame(transportLine, actualTransportLine, "The transport line returned by getTransportLine() should be the same as the one added with addPair()");
    
        actualTransportLine = zoneTable.getTransportLine(zone2, zone1);
        assertSame(transportLine, actualTransportLine, "The transport line returned by getTransportLine() should be the same as the one added with addPair(), even when the zones are reversed");
    }
}