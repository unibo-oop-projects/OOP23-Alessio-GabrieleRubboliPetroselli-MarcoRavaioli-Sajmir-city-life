package unibo.citysimulation.model.clock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

import unibo.citysimulation.utilities.ConstantAndResourceLoader;

import java.util.List;
import java.util.ArrayList;

public class ClockModel {

    private int totalDays;
    private long hourDuration = ConstantAndResourceLoader.TIME_UPDATE_RATE;
    private Timer timer;
    private int currentDay;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private LocalTime currentTime;
    private List<ClockObserver> observers;
    private boolean isPaused;
    private TimerTask task;

    public ClockModel(int totalDays) {
        this.totalDays = totalDays;
        this.observers = new ArrayList<>();
    }

    public void addObserver(ClockObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ClockObserver observer) {
        observers.remove(observer);
    }

    public void startSimulation() {
        if(timer!=null){
            timer.cancel();
            task.cancel();
        }   
        this.timer = new Timer();
        isPaused=false;
        currentTime = LocalTime.of(0,0);
        //currentDay kept int for convenience
        currentDay = 1;
    
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
    
    public void pauseSimulation(){
        isPaused=!isPaused;
    }

    private void notifyObservers() {
        for (ClockObserver observer : observers) {
            observer.onTimeUpdate(currentTime, currentDay);
        }
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    public String getFormattedCurrentTime() {
        return currentTime.format(formatter);
    }

    public int getCurrentDay() {
        return currentDay;
    }
}
