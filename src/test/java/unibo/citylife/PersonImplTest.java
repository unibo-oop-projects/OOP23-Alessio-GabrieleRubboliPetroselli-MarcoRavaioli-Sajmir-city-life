package unibo.citylife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.citysimulation.controller.ClockController;
import unibo.citysimulation.model.ClockModel;
import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.person.PersonImpl;
import unibo.citysimulation.model.transport.Zone;
import unibo.citysimulation.model.transport.ZoneTable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;

public class PersonImplTest {
    private ClockModel clock;
    private Business business;
    private Zone residenceZone;
    private ZoneTable zoneTable;
    private PersonImpl person;

    @BeforeAll
    public void setUp() {
        clock = person.
        business = mock(Business.class);
        residenceZone = mock(Zone.class);
        zoneTable = mock(ZoneTable.class);
        person = new PersonImpl(100, business, residenceZone, clock);
    }

    @Test
    public void testGetCurrentZone_Working() {
        when(business.getZone()).thenReturn(mock(Zone.class));
        person.setState(PersonState.WORKING);
        Optional<Zone> currentZone = person.getCurrentZone();
        assertTrue(currentZone.isPresent());
        assertEquals(business.getZone(), currentZone.get());
    }

    @Test
    public void testGetCurrentZone_AtHome() {
        person.setState(PersonState.AT_HOME);
        Optional<Zone> currentZone = person.getCurrentZone();
        assertTrue(currentZone.isPresent());
        assertEquals(residenceZone, currentZone.get());
    }

    @Test
    public void testGetCurrentZone_NotPresent() {
        person.setState(PersonState.MOVING);
        Optional<Zone> currentZone = person.getCurrentZone();
        assertFalse(currentZone.isPresent());
    }

    // Aggiungi altri test per i metodi checkTimeToMove, checkTimeToGoToWork, checkTimeToGoHome, incrementLastArrivingTime, checkArrivingTime, checkState, ecc.
}


