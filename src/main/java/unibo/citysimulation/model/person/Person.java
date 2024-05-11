package unibo.citysimulation.model.person;

import java.time.LocalTime;

public interface Person extends StaticPerson {
    
    void incrementLastArrivingTime(int duration);

    void checkState(LocalTime currentTime);
}
