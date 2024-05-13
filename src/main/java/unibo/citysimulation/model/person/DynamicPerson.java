package unibo.citysimulation.model.person;

import java.time.LocalTime;

public interface DynamicPerson extends StaticPerson {
    
    void incrementLastArrivingTime(int duration);

    void checkState(LocalTime currentTime);
}
