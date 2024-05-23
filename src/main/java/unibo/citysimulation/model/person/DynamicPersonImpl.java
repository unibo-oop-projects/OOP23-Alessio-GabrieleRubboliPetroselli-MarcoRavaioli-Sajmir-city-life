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
    private final Random random = new Random();

    public DynamicPersonImpl(final PersonData personData, final int money) {
        super(personData, money);
        this.lastDestination = PersonState.WORKING;
        late = false;
    }

    private boolean checkTimeToMove(final int currentTime, final int timeToMove, final int lineDuration) {
        boolean congestioned = false;
        if (currentTime == timeToMove || late) {
            for (final var line : transportLine) {
                if (line.getCongestion() > 98) {
                    congestioned = true;
                    late = true;
                }
            }
            if (!congestioned) {
                this.lastArrivingTime = currentTime + lineDuration;

                late = false;

                return true;
            }
        }
        return false;
    }

    private void checkTimeToGoToWork(final LocalTime currentTime) {
        if (this.checkTimeToMove(currentTime.toSecondOfDay(),
                updatedTime(personData.business().getOpLocalTime()) - tripDuration,
                tripDuration)) {
            movePerson(PersonState.WORKING);
        }
    }

    private void checkTimeToGoHome(final LocalTime currentTime) {
        if (this.checkTimeToMove(currentTime.toSecondOfDay(), updatedTime(personData.business().getClLocalTime()),
                tripDuration)) {
            movePerson(PersonState.AT_HOME);
        }
    }

    private int updatedTime(final LocalTime movingTime) {
        return movingTime.toSecondOfDay() + (random.nextInt(13) * ConstantAndResourceLoader.MINUTES_IN_A_SECOND * 60);
    }

    private void checkArrivingTime(final LocalTime currentTime) {
        if (currentTime.toSecondOfDay() == this.lastArrivingTime) {
            this.setState(this.lastDestination);
            updatePosition();
            Arrays.stream(transportLine)
                    .forEach(TransportLine::decrementPersonInLine);

        }
    }

    @Override
    public void checkState(final LocalTime currentTime) {
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

    private void movePerson(final PersonState newState) {
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
