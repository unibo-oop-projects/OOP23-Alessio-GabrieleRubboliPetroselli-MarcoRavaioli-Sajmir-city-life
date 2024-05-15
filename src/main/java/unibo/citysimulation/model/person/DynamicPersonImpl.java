package unibo.citysimulation.model.person;

import java.time.LocalTime;
import java.util.Arrays;

import unibo.citysimulation.model.transport.TransportLine;

public class DynamicPersonImpl extends StaticPersonImpl implements DynamicPerson {
    private int lastArrivingTime;
    private PersonState lastDestination;

    public DynamicPersonImpl(PersonData personData, int money) {
        super(personData, money);
        this.lastDestination = PersonState.WORKING;
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
            Arrays.stream(transportLine)
                  .forEach(TransportLine::decrementPersonInLine);

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
            Arrays.stream(transportLine)
                  .forEach(TransportLine::incrementPersonInLine);
        }
        this.lastDestination = newState;
        this.updatePosition();
    }
}
