package unibo.citylife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import unibo.citysimulation.model.PathFinder;
import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTable;
import unibo.citysimulation.model.zone.ZoneTableCreation;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

class PathFinderTest {
    private Random random;
    private List<Zone> zones;
    private List<TransportLine> transports;

    @BeforeEach
    void setUp() {
        random = new Random();
        zones = ZoneFactory.createZonesFromFile();
        transports = TransportFactory.createTransportsFromFile(zones);
        ZoneTableCreation.createAndAddPairs(zones, transports);
        PathFinder.getInstance().calculateAllPaths(zones);
    }

    @Test
    void testShortestPath0to1() {
        Zone startZone = zones.get(0);
        Zone endZone = zones.get(1);
        TransportLine[] path = PathFinder.getInstance().findBestPath(startZone, endZone);

        TransportLine[] expectedTransportLines = {transports.get(2), transports.get(6), transports.get(4)};
        int expectedDuration = 24;
        int actualDuration = calculateTotalDuration(path);

        assertEquals(expectedDuration, actualDuration, "Total duration for shortest path from 0 to 1 is incorrect");
        assertArrayEquals(expectedTransportLines, path, "Transport lines for shortest path from 0 to 1 are incorrect");
    }

    @Test
    void testShortestPath0to1Congestion() {
        Zone startZone = zones.get(0);
        Zone endZone = zones.get(1);

        for (int i = 0; i < 50; i++) {
            transports.get(4).incrementPersonInLine();
        }

        assertEquals(100.0, transports.get(4).getCongestion(), "Congestion for transport line 4 is incorrect");

        TransportLine[] path = PathFinder.getInstance().findBestPath(startZone, endZone);

        TransportLine[] expectedTransportLines = {transports.get(0)};
        int expectedDuration = 60;
        int actualDuration = calculateTotalDuration(path);

        assertEquals(expectedDuration, actualDuration, "Total duration for shortest path from 0 to 1 is incorrect");
        assertArrayEquals(expectedTransportLines, path, "Transport lines for shortest path from 0 to 1 are incorrect");

        for (int i = 0; i < 100; i++) {
            transports.get(0).incrementPersonInLine();
        }

        assertEquals(100.0, transports.get(0).getCongestion(), "Congestion for transport line 0 is incorrect");

        assertArrayEquals(new TransportLine[0], PathFinder.getInstance().findBestPath(startZone, endZone), "Transport lines for path from 0 to 1 with 100% congestion");
    }

    @Test
    void testShortestPath2to4() {
        Zone startZone = zones.get(2);
        Zone endZone = zones.get(4);
        TransportLine[] path = PathFinder.getInstance().findBestPath(startZone, endZone);

        TransportLine[] expectedTransportLines = {transports.get(1), transports.get(2), transports.get(6)};
        int expectedDuration = 14;
        int actualDuration = calculateTotalDuration(path);

        assertEquals(expectedDuration, actualDuration, "Total duration for shortest path from 2 to 4 is incorrect");
        assertArrayEquals(expectedTransportLines, path, "Transport lines for shortest path from 2 to 4 are incorrect");
    }

    @Test
    void testNoPathExists() {

        Zone startZone = zones.get(0); // Selezioniamo casualmente la prima zona
        Zone endZone = zones.get(1);   // Selezioniamo la seconda zona

        TransportLine[] path = PathFinder.getInstance().findBestPath(startZone, endZone);

        assertArrayEquals(new TransportLine[0], path, "Path should be empty for isolated zones");
    }

    @Test
    void testCyclicPath() {
        Zone startZone = zones.get(0);
        Zone intermediateZone = zones.get(1);
        Zone endZone = zones.get(2);

        ZoneTable.getInstance().addPair(startZone, intermediateZone, transports.get(0));
        ZoneTable.getInstance().addPair(intermediateZone, endZone, transports.get(1));
        ZoneTable.getInstance().addPair(endZone, startZone, transports.get(2));

        PathFinder.getInstance().calculateAllPaths(zones);

        TransportLine[] path = PathFinder.getInstance().findBestPath(startZone, endZone);

        assertNotNull(path, "Path should not be null");
        assertTrue(path.length > 0, "Path should not be empty despite the cycle");
    }

    @Test
    void testPartialCongestion() {
        Zone startZone = zones.get(0);
        Zone endZone = zones.get(3);

        transports.get(0).incrementPersonInLine();
        for (int i = 0; i < 50; i++) {
            transports.get(1).incrementPersonInLine();
        }

        TransportLine[] path = PathFinder.getInstance().findBestPath(startZone, endZone);

        boolean containsHighlyCongestedLine = Arrays.stream(path)
                .anyMatch(line -> line.getCongestion() >= 100.0);

        assertFalse(containsHighlyCongestedLine, "Path should avoid highly congested transport lines");
    }

    @Test
    void testMultiplePathsSameDuration() {
        Zone startZone = zones.get(0);
        Zone endZone = zones.get(2);

        ZoneTable.getInstance().addPair(startZone, zones.get(1), transports.get(0));
        ZoneTable.getInstance().addPair(zones.get(1), endZone, transports.get(1));
        ZoneTable.getInstance().addPair(startZone, endZone, transports.get(2));

        PathFinder.getInstance().calculateAllPaths(zones);

        TransportLine[] path = PathFinder.getInstance().findBestPath(startZone, endZone);

        assertNotNull(path, "Path should not be null");
        assertTrue(path.length > 0, "Path should not be empty");
        assertEquals(20, calculateTotalDuration(path), "Path duration should be 20");
    }

    @Test
    void testSingleZone() {
        List<Zone> singleZoneList = List.of(zones.get(random.nextInt(0)));

        PathFinder.getInstance().calculateAllPaths(singleZoneList);

        TransportLine[] path = PathFinder.getInstance().findBestPath(singleZoneList.get(0), singleZoneList.get(0));

        assertArrayEquals(new TransportLine[0], path, "Path should be empty for a single zone");
    }

    @Test
    void testNoZones() {
        List<Zone> emptyZoneList = List.of();

        PathFinder.getInstance().calculateAllPaths(emptyZoneList);

        assertThrows(NullPointerException.class, () -> {
            PathFinder.getInstance().findBestPath(null, null);
        }, "Finding path with no zones should throw NullPointerException");
    }

    private int calculateTotalDuration(TransportLine[] path) {
        return Arrays.stream(path).mapToInt(TransportLine::getDuration).sum();
    }
}
