package unibo.citylife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.zone.Boundary;
import unibo.citysimulation.model.zone.ZoneCreationImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class test {

    private ZoneCreationImpl zoneCreation;

    @BeforeEach
    void setUp() {
        zoneCreation = new ZoneCreationImpl();
    }

    @Test
    void testIsPointInZone() {
        Boundary boundary = zoneCreation.createBoundary(0, 0, 10, 10);
        zoneCreation.createZone("Centro", boundary);

        assertTrue(zoneCreation.isPointInZone("Centro", 5, 5));
        assertFalse(zoneCreation.isPointInZone("Centro", 15, 15));
    }
    
}
