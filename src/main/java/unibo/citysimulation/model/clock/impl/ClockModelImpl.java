package unibo.citysimulation.model.clock.impl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

import unibo.citysimulation.model.clock.api.ClockModel;
import unibo.citysimulation.model.clock.api.ClockObserver;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents the clock model for the simulation.
 */
public class ClockModelImpl implements ClockModel {

    private final int totalDays;
    private int hourDuration = ConstantAndResourceLoader.TIME_UPDATE_RATE;
    private Timer timer;
    private int currentDay;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private LocalTime currentTime;
    private final List<ClockObserver> observers;
    private boolean isPaused;

    /**
     * Constructs a ClockModel object with the specified total number of simulation days.
     * 
     * @param totalDays The total number of simulation days
     */
    public ClockModelImpl(final int totalDays) {
        this.totalDays = totalDays;
        this.observers = new ArrayList<>();
    }

    /**
     * Adds an observer to the clock model.
     * 
     * @param observer The observer to add
     */
    @Override
    public void addObserver(final ClockObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the clock model.
     * 
     * @param observer The observer to remove
     */
    @Override
    public void removeObserver(final ClockObserver observer) {
        observers.remove(observer);
    }

    /**
     * Starts the simulation with the specified hour duration.
     * 
     * @param hourDuration The duration of each simulated hour in milliseconds
     */
    @Override
    public void startSimulation(final int hourDuration) {
        if (timer != null) {
            timer.cancel();
        }
        this.hourDuration = hourDuration;
        this.timer = new Timer();
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!isPaused) {
                    if (currentDay <= totalDays) {
                        currentTime = currentTime.plusMinutes(ConstantAndResourceLoader.MINUTES_IN_A_SECOND); 
                        if (currentTime.getHour() == 0 && currentTime.getMinute() == 0) {
                            currentDay++;
                        }
                        notifyObservers();
                    } else {
                        timer.cancel();
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, hourDuration);
    }

    /**
     * Restarts the simulation.
     */
    @Override
    public void restartSimulation() {
        if (timer != null) {
            timer.cancel();
        }
        isPaused = false;
        currentTime = LocalTime.of(0, 0);
        currentDay = 1;
        this.startSimulation(hourDuration);
    }

    /**
     * Pauses or resumes the simulation.
     */
    @Override
    public void pauseSimulation() {
        isPaused = !isPaused;
    }

    /**
     * Stops the simulation and permits the user to change input values.
     */
    @Override
    public void stopSimulation() {
        if (timer != null) {
            timer.cancel();
        }
        isPaused = true;
    }

    /**
     * Notifies all observers of a time update.
     */
    private void notifyObservers() {
        final List<ClockObserver> observersCopy = new ArrayList<>(observers);
        for (final ClockObserver observer : observersCopy) {
            observer.onTimeUpdate(currentTime, currentDay);
        }
    } 

    /**
     * Gets the current time of the simulation.
     * 
     * @return The current time
     */
    @Override
    public LocalTime getCurrentTime() {
        return currentTime;
    }

    /**
     * Gets the formatted current time of the simulation.
     * 
     * @return The formatted current time
     */
    @Override
    public String getFormattedCurrentTime() {
        return currentTime.format(formatter);
    }

    /**
     * Gets the current time of the simulation as a double.
     * 
     * @return The current time as a double
     */
    @Override
    public double getDoubleCurrentTime() {
        return (double) currentTime.toSecondOfDay();
    }

    /**
     * Gets the current day of the simulation.
     * 
     * @return The current day
     */
    @Override
    public int getCurrentDay() {
        return currentDay;
    }

    /**
     * Gets the hour duration of the simulation.
     * 
     * @return The hour duration
     */
    @Override
    public int getHourDuration() {
        return hourDuration;
    }

    /**
     * Sets the hour duration of the simulation.
     * 
     * @param hourDuration The hour duration to set
     */
    @Override
    public void setHourDuration(final int hourDuration) {
        this.hourDuration = hourDuration;
    }

    /**
     * Gets the paused state of the simulation.
     * 
     * @return True if the simulation is paused, false otherwise
     */
    @Override
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * Gets the timer of the simulation.
     * 
     * @return The timer
     */
    @Override
    public Timer getTimer() {
        return timer;
    }
}
