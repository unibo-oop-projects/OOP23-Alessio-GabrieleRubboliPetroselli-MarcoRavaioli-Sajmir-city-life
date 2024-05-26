package unibo.citylife;

import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.business.impl.BusinessFactory;
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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DynamicPersonImplTest {
    private final List<Zone> zones = ZoneFactory.createZonesFromFile();
    private final List<TransportLine> transports = TransportFactory.createTransportsFromFile(zones);
    @BeforeEach
    void setUp() {
        ZoneTableCreation.createAndAddPairs(zones, transports);
    }

    @Test
    void testCheckTimeToGoToWork() throws InterruptedException {
        final Zone residenceZone = zones.get(2);
        Business business;
        do {
            business = BusinessFactory.getRandomBusiness(zones).get();
        } while (business.getZone().equals(residenceZone));
        // Creazione di un oggetto DynamicPersonImpl da testare
        final PersonData personData = new PersonData("alberto casa", 60, business, residenceZone);

        final DynamicPersonImpl person = new DynamicPersonImpl(personData, 100);

        final int businessBeginning = person.getBusinessBegin();

        final int tripDuration = person.getTripDuration();

        assertSame(person.getState(), PersonState.AT_HOME);
        assertTrue(person.getPersonData().residenceZone().boundary().isInside(person.getPosition().get().getFirst(),
            person.getPosition().get().getSecond()));
        assertEquals(0, person.getTransportLine()[0].getPersonInLine());

        person.checkState(LocalTime.ofSecondOfDay(businessBeginning - tripDuration));

        assertSame(person.getState(), PersonState.MOVING);
        assertSame(person.getPosition(), Optional.empty());
        assertEquals(1, person.getTransportLine()[0].getPersonInLine());
    }
}

