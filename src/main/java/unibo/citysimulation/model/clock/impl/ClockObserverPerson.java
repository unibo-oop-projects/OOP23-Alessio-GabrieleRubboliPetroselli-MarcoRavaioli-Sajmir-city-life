package unibo.citysimulation.model.clock.impl;

import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

import unibo.citysimulation.model.clock.api.ClockObserver;
import unibo.citysimulation.model.person.api.DynamicPerson;

/**
 * Represents an observer for the clock model that observes people.
 */
public class ClockObserverPerson implements ClockObserver {
    private final List<List<DynamicPerson>> people;

    /**
     * Constructs a ClockObserverPerson object with the specified list of people.
     * 
     * @param people The list of people to observe.
     */
    public ClockObserverPerson(final List<List<DynamicPerson>> people) {
        this.people = new ArrayList<>(people);
    }

    /**
     * Called when the time is updated in the clock model.
     * This method checks the state of all people in the list of people.
     * 
     * @param currentTime The current time.
     * @param currentDay The current day.
     */
    @Override
    public void onTimeUpdate(final LocalTime currentTime, final int currentDay) {
        people.stream()
              .flatMap(List::stream)
              .forEach(person -> person.checkState(currentTime));
    }
}
