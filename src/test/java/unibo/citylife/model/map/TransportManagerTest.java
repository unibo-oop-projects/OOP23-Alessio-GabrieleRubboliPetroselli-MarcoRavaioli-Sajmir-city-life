package unibo.citylife.model.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unibo.citysimulation.model.transport.api.TransportLine;
import unibo.citysimulation.model.transport.creation.TransportCreation;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTableCreation;
import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.business.impl.BusinessFactoryImpl;
import unibo.citysimulation.model.map.impl.TransportManager;
import unibo.citysimulation.utilities.Pair;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

 

class TransportManagerTest {
    private TransportManager transportManager;
    private List<TransportLine> lines;
    private List<Business> businesses = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        final List<Zone> zones = ZoneFactory.createZonesFromFile();
        lines = TransportCreation.createTransportsFromFile(zones);
        ZoneTableCreation.createAndAddPairs(zones, lines);
        businesses.addAll(BusinessFactoryImpl.createMultipleBusiness(zones, 100));

        transportManager = new TransportManager();
    }

    @Test
    void testGetTransportNames() {
        transportManager.setTransportInfo(lines);

        List<String> names = transportManager.getTransportNames();

        assertFalse(names.isEmpty());
        assertEquals(lines.size(), names.size());
        for (int i = 0; i < lines.size(); i++) {
            assertEquals(lines.get(i).getName(), names.get(i));
        }
    }

    @Test
    void testGetCongestionList() {
        transportManager.setTransportCongestion(lines);

        List<Double> congestions = transportManager.getCongestionList();

        assertFalse(congestions.isEmpty());
        assertEquals(lines.size(), congestions.size());
        for (int i = 0; i < lines.size(); i++) {
            assertEquals(lines.get(i).getCongestion(), congestions.get(i));
        }
    }

    @Test
    void testGetLinesPointsCoordinates() {
        transportManager.setTransportInfo(lines);

        List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> coordinates = transportManager.getLinesPointsCoordinates();

        assertFalse(coordinates.isEmpty());
        assertEquals(lines.size(), coordinates.size());
        for (int i = 0; i < lines.size(); i++) {
            Pair<Integer, Integer> startPoint = lines.get(i).getLinkedZones().getFirst().boundary().getCenter();
            Pair<Integer, Integer> endPoint = lines.get(i).getLinkedZones().getSecond().boundary().getCenter();
            assertEquals(new Pair<>(startPoint, endPoint), coordinates.get(i));
        }
    }

    @Test
    void testSetTransportInfo() {
        transportManager.setTransportInfo(lines);

        List<String> names = transportManager.getTransportNames();
        assertFalse(names.isEmpty());
        assertEquals(lines.size(), names.size());

        List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> coordinates = transportManager.getLinesPointsCoordinates();
        assertFalse(coordinates.isEmpty());
        assertEquals(lines.size(), coordinates.size());
    }

    @Test
    void testSetTransportCongestion() {
        transportManager.setTransportCongestion(lines);

        List<Double> congestions = transportManager.getCongestionList();
        assertFalse(congestions.isEmpty());
        assertEquals(lines.size(), congestions.size());

        assertFalse(transportManager.isSimulationStarted());

        transportManager.setTransportCongestion(lines);

        congestions = transportManager.getCongestionList();
        assertFalse(congestions.isEmpty());
        assertEquals(lines.size(), congestions.size());
    }

    @Test
    void testIsSimulationStarted() {
        assertFalse(transportManager.isSimulationStarted());

        transportManager.setTransportCongestion(lines);

        assertFalse(transportManager.isSimulationStarted());

        transportManager.setSimulationStarted();
        assertTrue(transportManager.isSimulationStarted());
    }
}
