/*
package unibo.citylife.model.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.business.impl.BusinessFactoryImpl;
import unibo.citysimulation.model.person.api.PersonData;
import unibo.citysimulation.model.person.api.StaticPerson.PersonState;
import unibo.citysimulation.model.person.impl.DynamicPersonImpl;
import unibo.citysimulation.model.transport.api.TransportLine;
import unibo.citysimulation.model.transport.creation.TransportCreation;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTableCreation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DynamicPersonImplTest {
    private List<TransportLine> lines;
    DynamicPersonImpl person;
    private List<Business> businesses = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        final List<Zone> zones = ZoneFactory.createZonesFromFile();
        lines = TransportCreation.createTransportsFromFile(zones);
        ZoneTableCreation.createAndAddPairs(zones, lines);
        businesses.addAll(BusinessFactoryImpl.createMultipleBusiness(zones, 100));

        person = new DynamicPersonImpl(new PersonData("Vittorio Feltri",
                75, Optional.of(businesses.get(0)),
                zones.stream().filter(z -> z.equals(businesses.get(0).getBusinessData().zone())).findFirst().get()),
                1000);

        LocalTime opTime = person.getPersonData().business().get().getBusinessData().opLocalTime();
        LocalTime clTime = person.getPersonData().business().get().getBusinessData().clLocalTime();


    }

    @Test
    void testCheckStateAtHomeToWorking() {
        /*
         * assertEquals(person.getPersonData().business().get().getBusinessData().
         * opLocalTime(),
         * person.getPersonData().business().get().getBusinessData().clLocalTime());
         *//*

        int tripDuration = person.getTripDuration();

        person.setState(PersonState.AT_HOME);
        LocalTime currentTime = opTime;
        person.checkState(currentTime);
        assertEquals(PersonState.AT_HOME, person.getState());
        assertTrue(person.getPersonData().residenceZone().boundary().isInside(person.getPosition().get().getFirst(),
                person.getPosition().get().getSecond()));

        assertEquals(0, person.getTransportLine()[0].getPersonInLine());

        currentTime = opTime.minusMinutes(tripDuration);
        person.checkState(currentTime);
        assertEquals(PersonState.MOVING, person.getState());

        currentTime = clTime;
        person.checkState(currentTime);
        assertEquals(PersonState.WORKING, person.getState());
    }

    @Test
    void testCheckStateWorkingToHome() {
        person.setState(PersonState.WORKING);
        LocalTime currentTime = LocalTime.of(16, 30);
        person.checkState(currentTime);
        assertEquals(PersonState.WORKING, person.getState());

        currentTime = LocalTime.of(16, 59);
        person.checkState(currentTime);
        assertEquals(PersonState.WORKING, person.getState());
    }

    @Test
    void testCheckStateArrivalAtWork() {
        person.setState(PersonState.MOVING);
        LocalTime currentTime = LocalTime.of(9, 0);
        person.checkState(currentTime);
        assertEquals(PersonState.MOVING, person.getState());
    }

    @Test
    void testCheckStateArrivalAtHome() {
        person.setState(PersonState.MOVING);
        LocalTime currentTime = LocalTime.of(17, 0);
        person.checkState(currentTime);
        assertEquals(PersonState.MOVING, person.getState());
    }

    @Test
    void testCalculateUpdatedTime() {
        LocalTime testTime = LocalTime.of(12, 0);
        try {
            Method calculateUpdatedTimeMethod = DynamicPersonImpl.class.getDeclaredMethod("calculateUpdatedTime",
                    LocalTime.class);
            calculateUpdatedTimeMethod.setAccessible(true);
            int updatedTime = (int) calculateUpdatedTimeMethod.invoke(person, testTime);
            assertTrue(updatedTime > testTime.toSecondOfDay());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testShouldMove() {
        try {
            Method shouldMoveMethod = DynamicPersonImpl.class.getDeclaredMethod("shouldMove", int.class, int.class,
                    int.class);
            shouldMoveMethod.setAccessible(true);

            boolean result = (boolean) shouldMoveMethod.invoke(person, 3600, 3600, 600);
            assertTrue(result);

            Field lateField = DynamicPersonImpl.class.getDeclaredField("late");
            lateField.setAccessible(true);
            lateField.set(person, true); // Simulate lateness

            result = (boolean) shouldMoveMethod.invoke(person, 3600, 3600, 600);
            assertTrue(result);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testHandleArrival() {
        try {
            Method handleArrivalMethod = DynamicPersonImpl.class.getDeclaredMethod("handleArrival", LocalTime.class);
            handleArrivalMethod.setAccessible(true);

            Field lastArrivingTimeField = DynamicPersonImpl.class.getDeclaredField("lastArrivingTime");
            lastArrivingTimeField.setAccessible(true);
            lastArrivingTimeField.set(person, LocalTime.of(17, 0).toSecondOfDay());

            Field lastDestinationField = DynamicPersonImpl.class.getDeclaredField("lastDestination");
            lastDestinationField.setAccessible(true);
            lastDestinationField.set(person, PersonState.AT_HOME);

            person.setState(PersonState.MOVING);
            LocalTime arrivalTime = LocalTime.ofSecondOfDay((int) lastArrivingTimeField.get(person));
            handleArrivalMethod.invoke(person, arrivalTime);

            assertEquals(lastDestinationField.get(person), person.getState());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
*/