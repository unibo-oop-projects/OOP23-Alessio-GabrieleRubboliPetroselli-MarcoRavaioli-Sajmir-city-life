package unibo.citysimulation.model.clock;

import java.time.LocalTime;
import java.util.List;

import unibo.citysimulation.model.person.DynamicPerson;

public class ClockObserverPerson implements ClockObserver{
    private final List<List<DynamicPerson>> people;

    public ClockObserverPerson(final List<List<DynamicPerson>> people) {
        this.people = people;
    }

    @Override
    public void onTimeUpdate(final LocalTime currentTime, final int currentDay) {
        for (final List<DynamicPerson> group : people) {
            for (final var person : group) {
                person.checkState(currentTime);
                
            }
        }
    }
    
}
