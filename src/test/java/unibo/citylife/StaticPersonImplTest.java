package unibo.citylife;

import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.business.impl.BusinessFactory;
import unibo.citysimulation.model.person.PersonData;
import unibo.citysimulation.model.person.StaticPerson;
import unibo.citysimulation.model.person.StaticPerson.PersonState;
import unibo.citysimulation.model.person.StaticPersonImpl;
import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTableCreation;
import unibo.citysimulation.utilities.Pair;

import java.util.Optional;
import java.util.Random;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StaticPersonImplTest {
    private final List<Zone> zones = ZoneFactory.createZonesFromFile();
    private final List<TransportLine> transports = TransportFactory.createTransportsFromFile(zones);
    private final Random random = new Random();
    private StaticPerson staticPerson;

    @BeforeEach
    void setUp() {
        final Zone residenceZone = zones.get(random.nextInt(zones.size()));
        final Business business = BusinessFactory.getRandomBusiness(zones).get();
        ZoneTableCreation.createAndAddPairs(zones, transports);
        // Simuliamo un dato di una persona per i test
        final PersonData personData = new PersonData("Mario", 30, business, residenceZone);
        staticPerson = new StaticPersonImpl(personData, 100);
    }

    @Test
    void testGetPersonData() {
        final PersonData personData = staticPerson.getPersonData();
        final int expectedAge = 30;
        assertEquals("Mario", personData.name());
        assertEquals(expectedAge, personData.age());
    }

    @Test
    void testGetPosition() {
        final Optional<Pair<Integer, Integer>> position = staticPerson.getPosition();
        assertTrue(position.isPresent());
        assertTrue(staticPerson.getPersonData().residenceZone().boundary().isInside(position.get().getFirst(),
            position.get().getSecond()));
    }

    @Test
    void testGetMoney() {
        assertEquals(100, staticPerson.getMoney());
    }

    @Test
    void testAddMoney() {
        final int moneyToAdd = 50;
        final int expectedMoney = 150;
        staticPerson.addMoney(moneyToAdd);
        assertEquals(expectedMoney, staticPerson.getMoney());
    }

    @Test
    void testGetState() {
        assertEquals(PersonState.AT_HOME, staticPerson.getState());
    }
}

