package unibo.citysimulation.model.clock;

import java.time.LocalTime;

import unibo.citysimulation.model.person.PersonImpl;

public class ClockObserverPerson implements ClockObserver{
    private PersonImpl person;

    public ClockObserverPerson(PersonImpl person) {
        this.person = person;
    }

    @Override
    public void onTimeUpdate(LocalTime currentTime, int currentDay) {
        person.checkState(currentTime);
    }
    
}
