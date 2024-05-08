package unibo.citysimulation.model.clock;

import java.time.LocalTime;
import java.util.List;

import unibo.citysimulation.model.person.Person;

public class ClockObserverPerson implements ClockObserver{
    private List<List<Person>> people;

    public ClockObserverPerson(List<List<Person>> people) {
        this.people = people;
    }

    @Override
    public void onTimeUpdate(LocalTime currentTime, int currentDay) {
        for (List<Person> group : people) {
            for (Person person : group) {
                person.checkState(currentTime);
            }
        }
    }
    
}
