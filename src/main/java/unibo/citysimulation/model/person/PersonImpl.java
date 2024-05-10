package unibo.citysimulation.model.person;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneTable;
import unibo.citysimulation.utilities.Pair;
import java.time.LocalTime;
import java.util.Optional;

public class PersonImpl implements Person {
    public record PersonData(String name, int age, int money, Business business, Zone residenceZone, ZoneTable zoneTable) {
    }

    private final PersonData personData;
    private PersonState state;
    private int lastArrivingTime = 0;
    private PersonState lastDestination;
    private Optional<Pair<Integer, Integer>> homePosition;
    private Optional<Pair<Integer, Integer>> position;
    private TransportLine transportLine;
    private int tripDuration;

    public PersonImpl(PersonData personData) {
        this.personData = personData;
        this.state = PersonState.AT_HOME;
        this.lastDestination = PersonState.WORKING;
        this.homePosition = Optional.of(personData.residenceZone.getRandomPosition());
        this.position = homePosition;
        this.transportLine = personData.zoneTable.getTransportLine(personData.residenceZone, personData.business.getZone());
        this.getTrip();
    }

    @Override
    public PersonState getState() {
        return state;
    }

    public PersonData getPersonData() {
        return personData;
    }

    @Override
    public void setState(PersonState state) {
        this.state = state;
    }

    public  Zone getBusinessZone() {
        return personData.business.getZone();
    }

    public boolean checkTimeToMove(int currentTime, int timeToMove, int lineDuration) {
        boolean moveBool = (currentTime == timeToMove);
        if (moveBool) {
            this.lastArrivingTime = currentTime + lineDuration;
        }
        return moveBool;
    }

    public void checkTimeToGoToWork(LocalTime currentTime) {
        if (this.checkTimeToMove(currentTime.toSecondOfDay(), personData.business.getOpeningTime().toSecondOfDay() - tripDuration,
                tripDuration)) {
            movePerson(PersonState.WORKING);
        }
    }

    public void checkTimeToGoHome(LocalTime currentTime) {
        if (this.checkTimeToMove(currentTime.toSecondOfDay(), personData.business.getClosingTime().toSecondOfDay(),
                tripDuration)) {
            movePerson(PersonState.AT_HOME);
        }
    }

    public void incrementLastArrivingTime(int duration) {
        this.lastArrivingTime += duration;
    }

    public void checkArrivingTime(LocalTime currentTime) {
        if (currentTime.toSecondOfDay() == this.lastArrivingTime) {
            this.setState(this.lastDestination);
            updatePosition();
            this.transportLine.decrementPersonInLine();
        }
    }

    public void checkState(LocalTime currentTime) {
        switch (this.state) {
            case MOVING:
                this.checkArrivingTime(currentTime);
                break;
            case WORKING:
                this.checkTimeToGoHome(currentTime);
                break;
            case AT_HOME:
                this.checkTimeToGoToWork(currentTime);
                break;
        }
    }

    private void getTrip() {
        if (personData.residenceZone == personData.business.getZone()) {
            this.tripDuration = 0;
        } else {
            this.transportLine = personData.zoneTable.getTransportLine(personData.residenceZone, personData.business.getZone());
            tripDuration = this.transportLine.getDuration() * 60;
        }
    }

    private void movePerson(PersonState newState) {
        if (this.tripDuration == 0) {
            this.setState(newState);
        } else {
            this.setState(PersonState.MOVING);
            this.transportLine.incrementPersonInLine();
        }
        this.lastDestination = newState;
        this.updatePosition();
    }

    private void updatePosition() {
        switch (this.state) {
            case MOVING:
                this.position = Optional.empty();
                break;
            case WORKING:
                this.position = Optional.of(personData.business.getPosition());
                break;
            case AT_HOME:
                this.position = homePosition;
                break;
        }
    }
}
