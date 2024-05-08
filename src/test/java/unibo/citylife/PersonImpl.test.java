package unibo.citylife;
// ```java
/*
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonImplTest {

    @Test
    public void testGetName() {
        // Arrange
        PersonImpl person = new PersonImpl(100, new Business(), new Zone(), new ClockModel(), new ZoneTable());

        // Act
        String name = person.getName();

        // Assert
        assertEquals("", name);
    }

    @Test
    public void testGetState() {
        // Arrange
        PersonImpl person = new PersonImpl(100, new Business(), new Zone(), new ClockModel(), new ZoneTable());

        // Act
        PersonState state = person.getState();

        // Assert
        assertEquals(PersonState.AT_HOME, state);
    }

    @Test
    public void testGetMoney() {
        // Arrange
        PersonImpl person = new PersonImpl(100, new Business(), new Zone(), new ClockModel(), new ZoneTable());

        // Act
        int money = person.getMoney();

        // Assert
        assertEquals(100, money);
    }

    @Test
    public void testGetClock() {
        // Arrange
        PersonImpl person = new PersonImpl(100, new Business(), new Zone(), new ClockModel(), new ZoneTable());

        // Act
        ClockModel clock = person.getClock();

        // Assert
        assertNotNull(clock);
    }

    @Test
    public void testSetMoney() {
        // Arrange
        PersonImpl person = new PersonImpl(100, new Business(), new Zone(), new ClockModel(), new ZoneTable());

        // Act
        person.setMoney(200);

        // Assert
        assertEquals(200, person.getMoney());
    }

    @Test
    public void testGetBusiness() {
        // Arrange
        PersonImpl person = new PersonImpl(100, new Business(), new Zone(), new ClockModel(), new ZoneTable());

        // Act
        Business business = person.getBusiness();

        // Assert
        assertNotNull(business);
    }

    @Test
    public void testGetResidenceZone() {
        // Arrange
        PersonImpl person = new PersonImpl(100, new Business(), new Zone(), new ClockModel(), new ZoneTable());

        // Act
        Zone residenceZone = person.getResidenceZone();

        // Assert
        assertNotNull(residenceZone);
    }

    @Test
    public void testGetCurrentZone() {
        // Arrange
        PersonImpl person = new PersonImpl(100, new Business(), new Zone(), new ClockModel(), new ZoneTable());

        // Act
        Optional<Zone> currentZone = person.getCurrentZone();

        // Assert
        assertTrue(currentZone.isPresent());
        assertEquals(person.getResidenceZone(), currentZone.get());
    }

    @Test
    public void testGetBusinessZone() {
        // Arrange
        PersonImpl person = new PersonImpl(100, new Business(), new Zone(), new ClockModel(), new ZoneTable());

        // Act
        Zone businessZone = person.getBusinessZone();

        // Assert
        assertNotNull(businessZone);
    }

    @Test
    public void testCheckTimeToMove() {
        // Arrange
        PersonImpl person = new PersonImpl(100, new Business(), new Zone(), new ClockModel(), new ZoneTable());
        int currentTime = 1000;
        int timeToMove = 2000;
        int lineDuration = 100;

        // Act
        boolean moveBool = person.checkTimeToMove(currentTime, timeToMove, lineDuration);

        // Assert
        assertTrue(moveBool);
        assertEquals(PersonState.MOVING, person.getState());
        assertEquals(3000, person.getLastArrivingTime());
    }

    @Test
    public void testCheckTimeToGoToWork() {
        // Arrange
        PersonImpl person = new PersonImpl(100, new Business(), new Zone(), new ClockModel(), new ZoneTable());

        // Act
        boolean timeToGoToWork = person.checkTimeToGoToWork();

        // Assert
        assertTrue(timeToGoToWork);
        assertEquals(PersonState.WORKING, person.getLastDestination());
    }

    @Test
    public void testCheckTimeToGoHome() {
        // Arrange
        PersonImpl person = new PersonImpl(100, new Business(), new Zone(), new ClockModel(), new ZoneTable());

        // Act
        boolean timeToGoHome = person.checkTimeToGoHome();

        // Assert
        assertFalse(timeToGoHome);
    }

    @Test
    public void testIncrementLastArrivingTime() {
        // Arrange
        PersonImpl person = new PersonImpl(100, new Business(), new Zone(), new ClockModel(), new ZoneTable());
        int duration = 100;

        // Act
        person.incrementLastArrivingTime(duration);

        // Assert
        assertEquals(person.getLastArrivingTime(), person.getClock().getCurrentTime().toSecondOfDay() + duration);
    }

    @Test
    public void testCheckArrivingTime() {
        // Arrange
        PersonImpl person = new PersonImpl(100, new Business(), new Zone(), new ClockModel(), new ZoneTable());

        // Act
        boolean arrivingTime = person.checkArrivingTime();

        // Assert
        assertFalse(arrivingTime);
    }

    @Test
    public void testCheckState() {
        // Arrange
        PersonImpl person = new PersonImpl(100, new Business(), new Zone(), new ClockModel(), new ZoneTable());

        // Act
        person.checkState();

        // Assert
        assertEquals(PersonState.AT_HOME, person.getState());
    }
}*/
// ```