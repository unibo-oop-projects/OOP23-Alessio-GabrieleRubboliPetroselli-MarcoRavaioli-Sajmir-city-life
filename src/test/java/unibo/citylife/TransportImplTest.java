package unibo.citylife;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import unibo.citysimulation.model.transport.Transport;
import unibo.citysimulation.model.transport.TransportImpl;
import unibo.citysimulation.model.transport.Zone;

import java.util.List;

/**
 * Test class for {@link TransportImpl}.
 */
public class TransportImplTest {

    /**
     * Test method for {@link TransportImpl#addPredefinedLine(Zone, Zone)}.
     */
    @Test
    public void testPredefinedLineAssociation() {
        // Creation of an origin zone and a destination zone
        Zone originZone = new Zone("Zone 1");
        Zone destinationZone = new Zone("Zone 4");

        // Creation of a transport with specific capacity and congestion
        TransportImpl bus = new TransportImpl(90, 100); // Example: congestion 90, capacity 100

        // Adding a predefined line between zones
        bus.addPredefinedLine(originZone, destinationZone);

        // Verifying that the transport is correctly associated with the predefined line between zones
        List<Transport> transports = bus.predefinedLines.get(originZone).get(destinationZone);
        assertFalse(transports.isEmpty(), "The list of transports must not be empty");
        Transport transport = transports.get(0);
        assertEquals(100, transport.getCapacity(), "The capacity of the transport must be 100");
        assertEquals(90, transport.getCongestion(), "The congestion of the transport must be 90");
    }
}