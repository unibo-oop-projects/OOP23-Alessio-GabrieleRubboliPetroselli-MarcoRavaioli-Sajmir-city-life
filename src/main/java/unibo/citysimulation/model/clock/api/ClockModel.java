package unibo.citysimulation.model.clock.api;

import java.time.LocalTime;
import java.util.Timer;

/**
 * Represents the clock model for the simulation.
 * This model has the responsibility to manage the simulation time.
 * It can start, pause, stop and restart the simulation.
 * It can also change the speed of the simulation.
 */
public interface ClockModel {
    /**
     * Adds an observer to the clock model.
     * 
     * @param observer The observer to add
     */
    void addObserver(ClockObserver observer);

    /**
     * Removes an observer from the clock model.
     * 
     * @param observer The observer to remove
     */
    void removeObserver(ClockObserver observer);

    /**
     * Starts the simulation with the specified hour duration.
     * 
     * @param hourDuration The duration of each simulated hour in milliseconds
     */
    void startSimulation(int hourDuration);

    /**
     * Pauses the simulation.
     */
    void pauseSimulation();

    /**
     * Stops the simulation and makes possible to change input value.
     */
    void stopSimulation();

    /**
     * Sets the current hourDuration of the simulation.
     * 
     * @param hourDuration The new hourDuration of the simulation
     */
    void setHourDuration(int hourDuration);

    /**
     * re-start the simulation with the same hourDuration.
     */
    void restartSimulation();

    /**
     * @return The current hour duration of the simulation.
     */
    int getHourDuration();

    /**
     * @return The current day of the simulation.
     */
    int getCurrentDay();

    /**
     * @return The current time of the simulation.
     */
    LocalTime getCurrentTime();

    /**
     * @return a boolean indicating either the simulation is paused or not.
     */
    boolean isPaused();

    /**
     * @return the current time formatted as a string.
     */
    String getFormattedCurrentTime();

    /**
     * @return the current time as a double.
     */
    double getDoubleCurrentTime();

    /**
     * @return the timer of the simulation.
     */
    Timer getTimer();
}
