package unibo.citylife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.BusinessFactory;
import unibo.citysimulation.model.person.DynamicPersonImpl;
import unibo.citysimulation.model.person.PersonData;
import unibo.citysimulation.model.person.StaticPerson.PersonState;
import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTable;
import java.util.Optional;

import java.util.List;
import java.time.LocalTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicPersonImplTest {
    private List<Zone> zones = ZoneFactory.createZonesFromFile();
    private List<TransportLine> transports = TransportFactory.createTransportsFromFile(zones);
    private List<Business> businesses = BusinessFactory.createBusinesses(zones);
    private Random random = new Random();
    
    @BeforeEach
    void setUp() {
        ZoneTable.getInstance().addPair(zones.get(0), zones.get(1), transports.get(0));
        ZoneTable.getInstance().addPair(zones.get(1), zones.get(2), transports.get(1));
        ZoneTable.getInstance().addPair(zones.get(0), zones.get(2),transports.get(2));
    }

    @Test
    void testCheckTimeToGoToWork() {
        
        // Creazione di un oggetto DynamicPersonImpl da testare
        Business business = businesses.get(1);
        Zone residenceZone = zones.get(2);
        PersonData personData = new PersonData("alberto casa", 60, business, residenceZone);

        DynamicPersonImpl person = new DynamicPersonImpl(personData, 100);

        person.checkState(LocalTime.of(8,0));

        assertTrue(person.getState() == PersonState.AT_HOME);
        assertTrue(person.getPersonData().residenceZone().boundary().isInside(person.getPosition().get().getFirst(),
            person.getPosition().get().getSecond()));
        assertEquals(0, person.getTransportLine().getPersonInLine());

        person.checkState(LocalTime.of(10,50));

        assertTrue(person.getState() == PersonState.AT_HOME);
        assertTrue(person.getPersonData().residenceZone().boundary().isInside(person.getPosition().get().getFirst(),
            person.getPosition().get().getSecond()));
        assertEquals(0, person.getTransportLine().getPersonInLine());

        person.checkState(LocalTime.of(10,55));

        assertTrue(person.getState() == PersonState.MOVING);
        assertTrue(person.getPosition().isEmpty());
        assertEquals(1, person.getTransportLine().getPersonInLine());


    }
}

