package unibo.citysimulation.model.clock;
import java.time.LocalTime;

public interface ClockObserver {
    void onTimeUpdate(LocalTime currentTime, int currentDay);

    
}

