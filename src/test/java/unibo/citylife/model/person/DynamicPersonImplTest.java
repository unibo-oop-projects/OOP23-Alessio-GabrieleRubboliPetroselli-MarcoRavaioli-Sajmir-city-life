package unibo.citylife.model.person;
import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.business.impl.BusinessFactoryImpl;
import unibo.citysimulation.model.person.impl.DynamicPersonImpl;
import unibo.citysimulation.model.person.api.PersonData;
import unibo.citysimulation.model.person.api.StaticPerson.PersonState;
import unibo.citysimulation.model.transport.api.TransportLine;
import unibo.citysimulation.model.transport.impl.TransportFactoryImpl;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneCreation;
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
    private final List<Zone> zones = ZoneCreation.createZonesFromFile();
    private final List<TransportLine> transports =  new TransportFactoryImpl().createTransportsFromFile(zones);
    @BeforeEach
    void setUp() {
        ZoneTableCreation.createAndAddPairs(zones, transports);
    }

    @Test
    void testCheckTimeToGoToWork() throws InterruptedException {
        final Zone residenceZone = zones.get(2);
        Business business;
        do {
            business = BusinessFactoryImpl.createRandomBusiness(zones).get();
        } while (business.getBusinessData().zone().equals(residenceZone));
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
