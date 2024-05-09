package unibo.citysimulation.model.clock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents the clock model for the simulation.
 */
public class ClockModel {

    private int totalDays;
    private int hourDuration = ConstantAndResourceLoader.TIME_UPDATE_RATE;
    private Timer timer;
    private int currentDay;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private LocalTime currentTime;
    private List<ClockObserver> observers;
    private boolean isPaused;
    private TimerTask task;

    /**
     * Constructs a ClockModel object with the specified total number of simulation days.
     * 
     * @param totalDays The total number of simulation days.
     */
    public ClockModel(int totalDays) {
        this.totalDays = totalDays;
        this.observers = new ArrayList<>();
    }

    /**
     * Adds an observer to the clock model.
     * 
     * @param observer The observer to add.
     */
    public void addObserver(ClockObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the clock model.
     * 
     * @param observer The observer to remove.
     */
    public void removeObserver(ClockObserver observer) {
        observers.remove(observer);
    }

    /**
     * Starts the simulation with the specified hour duration.
     * 
     * @param hourDuration The duration of each simulated hour in milliseconds.
     */
    public void startSimulation(int hourDuration) {
        System.out.println("Simulation started");
        if(timer!=null){
            timer.cancel();
            task.cancel();
        }
        
        this.hourDuration = hourDuration;

        this.timer = new Timer();
    
        task = new TimerTask() {
    
            @Override
            public void run() {
                if(!isPaused){
                    if (currentDay <= totalDays) {
                        currentTime = currentTime.plusMinutes(ConstantAndResourceLoader.MINUTES_IN_A_SECOND);
        
                        if (currentTime.getHour() == 0 && currentTime.getMinute() == 0){
                            currentDay++;
                        }
    
                        System.out.println("Current time: " + currentTime.format(formatter) + " Day: " + currentDay);
                        System.out.println("hour duration : "+ hourDuration);
                        
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
    public void restartSimulation(){
        if(timer!=null){
            timer.cancel();
            task.cancel();
        }
        isPaused=false;
        currentTime = LocalTime.of(0,0);
        currentDay = 1;
        this.startSimulation(hourDuration);
    }
    
    /**
     * Pauses or resumes the simulation.
     */
    public void pauseSimulation(){
        isPaused=!isPaused;
    }

    /**
     * Notifies all observers of a time update.
     */
    private void notifyObservers() {
        for (ClockObserver observer : observers) {
            observer.onTimeUpdate(currentTime, currentDay);
        }
    }

    /**
     * Gets the current time of the simulation.
     * 
     * @return The current time.
     */
    public LocalTime getCurrentTime() {
        return currentTime;
    }

    /**
     * Gets the formatted current time of the simulation.
     * 
     * @return The formatted current time.
     */
    public String getFormattedCurrentTime() {
        return currentTime.format(formatter);
    }

    /**
     * Gets the current day of the simulation.
     * 
     * @return The current day.
     */
    public int getCurrentDay() {
        return currentDay;
    }

    /**
     * Gets the hour duration of the simulation.
     * 
     * @return The hour duration.
     */
    public int getHourDuration() {
        return hourDuration;
    }

    /**
     * Sets the hour duration of the simulation.
     * 
     * @param hourDuration The hour duration to set.
     */
    public void setHourDuration(int hourDuration) {
        this.hourDuration = hourDuration;
    }

    /**
     * Gets the paused state of the simulation.
     * 
     * @return True if the simulation is paused, false otherwise.
     */
    public boolean getIsPaused(){
        return isPaused;
    }

    public Timer getTimer(){
        return timer;
    }
}
