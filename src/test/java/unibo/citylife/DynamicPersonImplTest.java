/*package unibo.citylife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.BusinessFactory;
import unibo.citysimulation.model.clock.ClockModel;
import unibo.citysimulation.model.clock.ClockObserver;
import unibo.citysimulation.model.clock.ClockObserverPerson;
import unibo.citysimulation.model.person.DynamicPersonImpl;
import unibo.citysimulation.model.person.PersonData;
import unibo.citysimulation.model.person.StaticPerson.PersonState;
import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTableCreation;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;

import java.util.List;
import java.util.Random;

public class DynamicPersonImplTest {
    private List<Zone> zones = ZoneFactory.createZonesFromFile();
    private List<TransportLine> transports = TransportFactory.createTransportsFromFile(zones);
    private List<Business> businesses = BusinessFactory.createBusinessesFromFile(zones);
    private Random random = new Random();
    private ClockModel clockModel;
    private ClockObserver clockObserverPerson;
    
    @BeforeEach
    void setUp() {
        ZoneTableCreation.createAndAddPairs(zones, transports);
        clockModel = new ClockModel(5);
    }

    @Test
    void testCheckTimeToGoToWork() throws InterruptedException {
        
        // Creazione di un oggetto DynamicPersonImpl da testare
        Business business = businesses.get(0);
        Zone residenceZone = zones.get(2);
        PersonData personData = new PersonData("alberto casa", 60, business, residenceZone);

        DynamicPersonImpl person = new DynamicPersonImpl(personData, 100);

        assertTrue(person.getState() == PersonState.AT_HOME);
        assertTrue(person.getPersonData().residenceZone().boundary().isInside(person.getPosition().get().getFirst(),
            person.getPosition().get().getSecond()));
        assertEquals(0, person.getTransportLine()[0].getPersonInLine());

        clockObserverPerson = new ClockObserverPerson(List.of(List.of(person)));
        clockModel.addObserver(clockObserverPerson);

        clockModel.setHourDuration(ConstantAndResourceLoader.TIME_UPDATE_RATE / 10);

        clockModel.restartSimulation();

        Thread.sleep(10000);

        clockModel.pauseSimulation();


        assertTrue(person.getState() == PersonState.WORKING);
        assertTrue(person.getPersonData().business().getZone().boundary().isInside(person.getPosition().get().getFirst(),
            person.getPosition().get().getSecond()));
        assertEquals(0, person.getTransportLine()[0].getPersonInLine());
    }
}*/

