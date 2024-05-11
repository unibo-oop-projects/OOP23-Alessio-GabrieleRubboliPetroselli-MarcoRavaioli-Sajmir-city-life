package unibo.citysimulation.model.person;

import unibo.citysimulation.model.zone.ZoneTable;
import unibo.citysimulation.utilities.Pair;
import java.util.Optional;
import java.time.LocalTime;

public class PersonImpl extends StaticPersonImpl implements Person{
    private int lastArrivingTime;
    private PersonState lastDestination;
    private Optional<Pair<Integer, Integer>> position;

    public PersonImpl(PersonData personData) {
        super(personData);
        this.lastDestination = PersonState.WORKING;
        this.position = homePosition;
        this.transportLine = ZoneTable.getTransportLine(getPersonData().residenceZone(), personData.business().getZone());
    }

    private boolean checkTimeToMove(int currentTime, int timeToMove, int lineDuration) {
        boolean moveBool = (currentTime == timeToMove);
        if (moveBool) {
            this.lastArrivingTime = currentTime + lineDuration;
        }
        return moveBool;
    }

    private void checkTimeToGoToWork(LocalTime currentTime) {
        if (this.checkTimeToMove(currentTime.toSecondOfDay(), personData.business().getOpeningTime().toSecondOfDay() - tripDuration,
                tripDuration)) {
            movePerson(PersonState.WORKING);
        }
    }

    private void checkTimeToGoHome(LocalTime currentTime) {
        if (this.checkTimeToMove(currentTime.toSecondOfDay(), personData.business().getClosingTime().toSecondOfDay(),
                tripDuration)) {
            movePerson(PersonState.AT_HOME);
        }
    }

    public void incrementLastArrivingTime(int duration) {
        this.lastArrivingTime += duration;
    }

    private void checkArrivingTime(LocalTime currentTime) {
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
                this.position = Optional.of(personData.business().getPosition());
                break;
            case AT_HOME:
                this.position = homePosition;
                break;
        }
    }
}
