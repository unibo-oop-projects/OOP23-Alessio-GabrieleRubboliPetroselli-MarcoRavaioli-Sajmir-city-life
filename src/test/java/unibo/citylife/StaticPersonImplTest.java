package unibo.citylife;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.BusinessFactory;
import unibo.citysimulation.model.person.PersonData;
import unibo.citysimulation.model.person.StaticPerson;
import unibo.citysimulation.model.person.StaticPerson.PersonState;
import unibo.citysimulation.model.person.StaticPersonImpl;
import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTable;
import unibo.citysimulation.model.zone.ZoneTableCreation;
import unibo.citysimulation.utilities.Pair;
import java.util.Optional;
import java.util.Random;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StaticPersonImplTest {
    private List<Zone> zones = ZoneFactory.createZonesFromFile();
    private List<TransportLine> transports = TransportFactory.createTransportsFromFile(zones);
    private List<Business> businesses = BusinessFactory.createBusinessesFromFile(zones);
    private Random random = new Random();

    private StaticPerson staticPerson;

    @BeforeEach
    void setUp() {
        Zone residenceZone = zones.get(random.nextInt(zones.size()));
        Business business = businesses.get(random.nextInt(businesses.size()));
        ZoneTableCreation.createAndAddPairs(zones, transports);
        // Simuliamo un dato di una persona per i test
        PersonData personData = new PersonData("Mario", 30, business, residenceZone);
        staticPerson = new StaticPersonImpl(personData, 100);
    }

    @Test
    void testGetPersonData() {
        PersonData personData = staticPerson.getPersonData();
        assertEquals("Mario", personData.name());
        assertEquals(30, personData.age());
        // Testa altri attributi di personData se necessario
    }

    @Test
    void testGetPosition() {
        Optional<Pair<Integer, Integer>> position = staticPerson.getPosition();
        assertTrue(position.isPresent());
        assertTrue(staticPerson.getPersonData().residenceZone().boundary().isInside(position.get().getFirst(),
            position.get().getSecond()));
    }

    @Test
    void testGetMoney() {
        assertEquals(100, staticPerson.getMoney()); // La persona dovrebbe avere 100 di denaro inizialmente
    }

    @Test
    void testAddMoney() {
        staticPerson.addMoney(50); // Aggiungiamo 50 di denaro
        assertEquals(150, staticPerson.getMoney()); // Assicura che il denaro sia aumentato di 50
    }

    @Test
    void testGetState() {
        assertEquals(PersonState.AT_HOME, staticPerson.getState()); // La persona dovrebbe essere a casa inizialmente
    }
}