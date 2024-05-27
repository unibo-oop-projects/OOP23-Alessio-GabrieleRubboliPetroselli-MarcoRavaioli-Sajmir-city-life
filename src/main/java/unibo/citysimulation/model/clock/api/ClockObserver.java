package unibo.citysimulation.model.clock.api;

import java.time.LocalTime;

/**
 * Interface for classes that observe the clock model.
 */
public interface ClockObserver {

    /**
     * Called when the time is updated in the clock model.
     * 
     * @param currentTime The current time.
     * @param currentDay The current day.
     */
    void onTimeUpdate(LocalTime currentTime, int currentDay);
}
