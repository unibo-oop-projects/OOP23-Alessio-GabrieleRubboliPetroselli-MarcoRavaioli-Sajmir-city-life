package unibo.citysimulation.model.clock;

import java.time.LocalTime;
import java.util.List;

import unibo.citysimulation.model.person.DynamicPerson;

public class ClockObserverPerson implements ClockObserver{
    private List<List<DynamicPerson>> people;

    public ClockObserverPerson(List<List<DynamicPerson>> people) {
        this.people = people;
    }

    @Override
    public void onTimeUpdate(LocalTime currentTime, int currentDay) {
        for (List<DynamicPerson> group : people) {
            for (var person : group) {
                person.checkState(currentTime);
                
            }
        }
    }
    
}
