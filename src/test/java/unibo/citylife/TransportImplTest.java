package unibo.citylife;

import org.junit.jupiter.api.Test;
import unibo.citysimulation.model.transport.Transport;
import unibo.citysimulation.model.transport.TransportImpl;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.transport.Zone;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        // Verify that the transport line is empty after adding a predefined line
        Optional<TransportLine> transportLineOptional = transport.getTransportLine();
        assertFalse(transportLineOptional.isPresent(), "The transport line must not be present");

        // Adding a physical vehicle and verifying its association with the transport
        bus.addTransportVehicle("Bus", transport);
        Transport associatedTransport = bus.getTransportByVehicleName("Bus");
        assertEquals(transport, associatedTransport, "The associated transport must be the same as the one added");
    }
    /**
     * Test method for adding and removing vehicles from the transport line.
     */
    @Test
    public void testVehicleAdditionAndRemoval() {
        // Create a transport line
        TransportLine transportLine = new TransportLine("Line 1", 10, 8, 20);

        // Create a vehicle
        Transport vehicle = new TransportImpl(80, 120);

        // Add the vehicle to the transport line
        transportLine.addVehicle(vehicle);

        // Check if the vehicle was added successfully
        assertTrue(transportLine.getVehicles().contains(vehicle), "The vehicle must be added to the transport line");

        // Remove the vehicle from the transport line
        boolean removed = transportLine.removeVehicle(vehicle);

        // Check if the vehicle was removed successfully
        assertTrue(removed, "The vehicle must be removed from the transport line");
        assertFalse(transportLine.getVehicles().contains(vehicle), "The vehicle must not be present in the transport line");
    }

    /**
     * Test method for calculating the average congestion level of the transport line.
     */
    @Test
    public void testAverageCongestionCalculation() {
        // Create a transport line
        TransportLine transportLine = new TransportLine("Line 2", 15, 7, 21);

        // Create vehicles with different congestion levels
        Transport vehicle1 = new TransportImpl(90, 100);
        Transport vehicle2 = new TransportImpl(70, 150);
        Transport vehicle3 = new TransportImpl(80, 120);

        // Add vehicles to the transport line
        transportLine.addVehicle(vehicle1);
        transportLine.addVehicle(vehicle2);
        transportLine.addVehicle(vehicle3);

        // Calculate the average congestion level
        double averageCongestion = transportLine.calculateAverageCongestion();

        // Verify the average congestion calculation
        assertEquals((90 + 70 + 80) / 3.0, averageCongestion, "The average congestion level must be calculated correctly");
    }
}

