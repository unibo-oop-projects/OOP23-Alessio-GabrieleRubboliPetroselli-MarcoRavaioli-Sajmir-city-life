package unibo.citylife;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.BusinessFactory;
import unibo.citysimulation.model.person.DynamicPersonImpl;
import unibo.citysimulation.model.person.PersonData;
import unibo.citysimulation.model.person.StaticPerson.PersonState;
import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTableCreation;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DynamicPersonImplTest {
    private final List<Zone> zones = ZoneFactory.createZonesFromFile();
    private final List<TransportLine> transports = TransportFactory.createTransportsFromFile(zones);
    private Business business;
    
    @BeforeEach
    void setUp() {
        ZoneTableCreation.createAndAddPairs(zones, transports);
        business = BusinessFactory.getRandomBusiness(zones).get();
    }

    @Test
    void testCheckTimeToGoToWork() throws InterruptedException {
        // Creazione di un oggetto DynamicPersonImpl da testare
        Zone residenceZone = zones.get(2);
        PersonData personData = new PersonData("alberto casa", 60, business, residenceZone);

        DynamicPersonImpl person = new DynamicPersonImpl(personData, 100);

        int businessBeginning = person.getBusinessBegin();

        int tripDuration = person.getTripDuration();

        assertTrue(person.getState() == PersonState.AT_HOME);
        assertTrue(person.getPersonData().residenceZone().boundary().isInside(person.getPosition().get().getFirst(),
            person.getPosition().get().getSecond()));
        assertEquals(0, person.getTransportLine()[0].getPersonInLine());

        person.checkState(LocalTime.ofSecondOfDay(businessBeginning - tripDuration));

        assertTrue(person.getState() == PersonState.MOVING);
        assertTrue(person.getPosition().equals(Optional.empty()));
        assertEquals(1, person.getTransportLine()[0].getPersonInLine());
    }
}

