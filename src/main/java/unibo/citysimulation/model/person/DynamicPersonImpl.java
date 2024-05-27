package unibo.citysimulation.model.person;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;

import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;

/**
 * Represents a dynamic person that can change state based on the current time
 * and move in order to work.
 */
public class DynamicPersonImpl extends StaticPersonImpl implements DynamicPerson {
    private int lastArrivingTime;
    private PersonState lastDestination;
    private boolean late;
    private final Random random = new Random();
    private final int businessBegin;
    private final int businessEnd;

    /**
     * Constructs a new dynamic person with the given person data and money.
     * At the beginning, the person is at home.
     * 
     * @param personData the data of the person.
     * @param money      the money of the person.
     */
    public DynamicPersonImpl(final PersonData personData, final int money) {
        super(personData, money);
        this.lastDestination = PersonState.WORKING;
        late = false;
        businessBegin = updatedTime(super.getPersonData().business().get().getOpLocalTime());
        businessEnd = updatedTime(super.getPersonData().business().get().getClLocalTime());
    }

    private boolean checkTimeToMove(final int currentTime, final int timeToMove, final int lineDuration) {
        boolean congestioned = false;
        if (currentTime == timeToMove || late) {
            for (final var line : super.getTransportLine()) {
                if (line.getCongestion() > ConstantAndResourceLoader.CONGESTION_VALUE) {
                    congestioned = true;
                    late = true;
                }
            }
            if (!congestioned) {
                this.lastArrivingTime = (currentTime + lineDuration) % ConstantAndResourceLoader.SECONDS_IN_A_DAY;

                late = false;
                return true;
            }
        }
        return false;
    }

    private void checkTimeToGoToWork(final LocalTime currentTime) {
        if (this.checkTimeToMove(currentTime.toSecondOfDay(),
                businessBegin - super.getTripDuration(),
                super.getTripDuration())) {
            movePerson(PersonState.WORKING);
        }
    }

    private void checkTimeToGoHome(final LocalTime currentTime) {
        if (this.checkTimeToMove(currentTime.toSecondOfDay(),
                businessEnd,
                super.getTripDuration())) {
            movePerson(PersonState.AT_HOME);
        }
    }

    private int updatedTime(final LocalTime movingTime) {
        return movingTime.toSecondOfDay() + (random.nextInt(ConstantAndResourceLoader.MAX_MOVING_TIME_VARIATION)
                * ConstantAndResourceLoader.MINUTES_IN_A_SECOND * ConstantAndResourceLoader.SECONDS_IN_A_MINUTE);
    }

    private void checkArrivingTime(final LocalTime currentTime) {
        if (currentTime.toSecondOfDay() == this.lastArrivingTime) {
            this.setState(this.lastDestination);
            updatePosition();
            Arrays.stream(super.getTransportLine())
                    .forEach(TransportLine::decrementPersonInLine);

        }
    }

    /**
     * Checks the state of the person based on the current time.
     * If the person is moving, it checks if the person has arrived at the destination.
     * If the person is working, it checks if it is time to go home.
     * If the person is at home, it checks if it is time to go to work.
     * 
     * @param currentTime the current time.
     */
    @Override
    public void checkState(final LocalTime currentTime) {
        switch (super.getState()) {
            case MOVING:
                this.checkArrivingTime(currentTime);
                break;
            case WORKING:
                this.checkTimeToGoHome(currentTime);
                break;
            case AT_HOME:
                this.checkTimeToGoToWork(currentTime);
                break;
            default:
                throw new IllegalStateException("Invalid state.");
        }
    }

    private void movePerson(final PersonState newState) {
        if (super.getTripDuration() == 0) {
            this.setState(newState);
        } else {
            this.setState(PersonState.MOVING);
            Arrays.stream(super.getTransportLine())
                    .forEach(TransportLine::incrementPersonInLine);
        }
        this.lastDestination = newState;
        this.updatePosition();
    }

    /**
     * Gets the business beginning time.
     */
    @Override
    public int getBusinessBegin() {
        return businessBegin;
    }

    /**
     * Gets the business ending time.
     */
    @Override
    public int getBusinessEnd() {
        return businessEnd;
    }
}
