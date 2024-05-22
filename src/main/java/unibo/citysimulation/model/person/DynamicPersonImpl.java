package unibo.citysimulation.model.person;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;

import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;

public class DynamicPersonImpl extends StaticPersonImpl implements DynamicPerson {
    private int lastArrivingTime;
    private PersonState lastDestination;
    private boolean late;
    private Random random = new Random();


    public DynamicPersonImpl(PersonData personData, int money) {
        super(personData, money);
        this.lastDestination = PersonState.WORKING;
        late = false;
    }

    private static final int MAX_ATTEMPTS_TO_MOVE = 3;
    private int attemptsToMove = 0;
    
    private boolean checkTimeToMove(int currentTime, int timeToMove, int lineDuration) {
        boolean congestioned = false;
        if (currentTime == timeToMove || late) {
            for (var line : transportLine) {
                if (line.getCongestion() > 98) {
                    congestioned = true;
                    late = true;
                    attemptsToMove++;
                }
            }
            if (!congestioned || attemptsToMove >= MAX_ATTEMPTS_TO_MOVE) {
                this.lastArrivingTime = currentTime + lineDuration;
                late = false;
                attemptsToMove = 0;
                return true;
            }
        }
        return false;
    }
    private void checkTimeToGoToWork(LocalTime currentTime) {
        if (this.checkTimeToMove(currentTime.toSecondOfDay(),
                updatedTime(personData.business().getOpLocalTime()) - tripDuration,
                tripDuration)) {
            movePerson(PersonState.WORKING);
        }
    }

    private void checkTimeToGoHome(LocalTime currentTime) {
        if (this.checkTimeToMove(currentTime.toSecondOfDay(), updatedTime(personData.business().getClLocalTime()),
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
        if (currentTime.toSecondOfDay() == 0 && this.state == PersonState.MOVING) {
            // It's the start of a new day and the person is still in line.
            // Force them to go home or to work instantly.
            if (this.lastDestination == PersonState.WORKING) {
                this.setState(PersonState.AT_HOME);
            } else {
                this.setState(PersonState.WORKING);
            }
            // Update lastArrivingTime to the start of the new day
            this.lastArrivingTime = 0;
            // Update the transport line to reflect the person's arrival
            Arrays.stream(transportLine).forEach(TransportLine::decrementPersonInLine);
        } else {
            // Normal behavior.
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
