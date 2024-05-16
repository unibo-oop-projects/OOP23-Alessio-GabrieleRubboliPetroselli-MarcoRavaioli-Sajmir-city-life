package unibo.citylife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTable;
import unibo.citysimulation.model.zone.ZoneTable.Path;
import unibo.citysimulation.model.zone.ZoneTableCreation;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;


class ZoneTableTest {
    private List<Zone> zones = ZoneFactory.createZonesFromFile();
    private List<TransportLine> transports = TransportFactory.createTransportsFromFile(zones);

    @BeforeEach
    void setUp() {
        ZoneTableCreation.createAndAddPairs(zones, transports);
    }

    @Test
    public void testShortestPath0to1() {
        Zone startZone = zones.get(0);
        Zone endZone = zones.get(1);
        Path path = ZoneTable.findBestPath(startZone, endZone);

        assertFalse(path == null, "No path found from 0 to 1");

        List<Zone> expectedPath = Arrays.asList(zones.get(0), zones.get(3),zones.get(4), zones.get(1));
        TransportLine[] expectedTransportLines = {transports.get(2), transports.get(6), transports.get(4)};
        int expectedDuration = 24;

        assertEquals(expectedPath, path.getZones(), "Shortest path from 0 to 1 is incorrect");
        assertEquals(expectedDuration, path.getTotalDuration(), "Total duration for shortest path from 0 to 1 is incorrect");
        assertArrayEquals(expectedTransportLines, path.getLinesPath(), "Transport lines for shortest path from 0 to 1 are incorrect");
    }

    @Test
    public void testShortestPath0to1Congestion() {
        Zone startZone = zones.get(0);
        Zone endZone = zones.get(1);

        for (int i = 0; i < 50; i++) {
            transports.get(4).incrementPersonInLine();
        }

        assertEquals(transports.get(4).getCongestion(), 100.0, "Congestion for transport line 4 is incorrect");

        Path path = ZoneTable.findBestPath(startZone, endZone);

        assertFalse(path == null, "No path found from 0 to 1");

        List<Zone> expectedPath = Arrays.asList(zones.get(0), zones.get(1));
        TransportLine[] expectedTransportLines = {transports.get(0)};
        int expectedDuration = 60;

        assertEquals(expectedPath, path.getZones(), "Shortest path from 0 to 1 is incorrect");
        assertEquals(expectedDuration, path.getTotalDuration(), "Total duration for shortest path from 0 to 1 is incorrect");
        assertArrayEquals(expectedTransportLines, path.getLinesPath(), "Transport lines for shortest path from 0 to 1 are incorrect");

        for (int i = 0; i < 100; i++) {
            transports.get(0).incrementPersonInLine();
        }

        assertEquals(transports.get(0).getCongestion(), 100.0, "Congestion for transport line 0 is incorrect");

        assertNull(ZoneTable.findBestPath(startZone, endZone), "Path found from 0 to 1 with 100% congestion");
        assertEquals(Optional.empty(), ZoneTable.getBestLinesPath(startZone, endZone), "Transport lines for path from 0 to 1 with 100% congestion");
    }

    @Test
    public void testShortestPath2to4() {
        Zone startZone = zones.get(2);
        Zone endZone = zones.get(4);
        Path path = ZoneTable.findBestPath(startZone, endZone);

        assertFalse(path == null, "No path found from 2 to 4");

        List<Zone> expectedPath = Arrays.asList(zones.get(2), zones.get(0), zones.get(3), zones.get(4));
        Optional<TransportLine[]> expectedTransportLines = Optional.of(new TransportLine[]{transports.get(1), transports.get(2), transports.get(6)});
        int expectedDuration = 14;

        assertEquals(expectedPath, path.getZones(), "Shortest path from 2 to 4 is incorrect");
        assertEquals(expectedDuration, path.getTotalDuration(), "Total duration for shortest path from 2 to 4 is incorrect");
        assertArrayEquals(expectedTransportLines.get(), ZoneTable.getBestLinesPath(startZone, endZone).get(), "Transport lines for shortest path from 2 to 4 are incorrect");
    }
}