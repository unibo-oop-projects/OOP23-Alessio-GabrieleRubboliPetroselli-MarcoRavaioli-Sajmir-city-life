package unibo.citysimulation.model.person;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;

import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;

public class DynamicPersonImpl extends StaticPersonImpl implements DynamicPerson {
    private int lastArrivingTime;
    private PersonState lastDestination;
    private Random random = new Random();

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
        if (this.checkTimeToMove(currentTime.toSecondOfDay(), updatedTime(personData.business().getOpeningTime()) - tripDuration,
                tripDuration)) {
            movePerson(PersonState.WORKING);
        }
    }

    private void checkTimeToGoHome(LocalTime currentTime) {
        if (this.checkTimeToMove(currentTime.toSecondOfDay(),updatedTime(personData.business().getClosingTime()),
                tripDuration)) {
            movePerson(PersonState.AT_HOME);
        }
    }

    private int updatedTime(LocalTime movingTime) {
        return movingTime.toSecondOfDay() + (random.nextInt(13) * ConstantAndResourceLoader.MINUTES_IN_A_SECOND * 60);
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
        System.out.println("Current time: " + currentTime);
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
