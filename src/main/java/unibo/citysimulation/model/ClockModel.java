package unibo.citysimulation.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

import unibo.citysimulation.controller.ClockController;

public class ClockModel {

    private int totalDays;
    private long hourDuration = 1000;
    private Timer timer;
    private int currentDay;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private ClockController clockController;
    private LocalDateTime currentTime;

    public ClockModel(int totalDays, ClockController clockController) {
        this.totalDays = totalDays;
        this.timer = new Timer();
        this.clockController = clockController;
    }

    public void startSimulation() {
        currentTime = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        currentDay = 1;
    
        TimerTask task = new TimerTask() {
    
            @Override
            public void run() {
                if (currentDay <= totalDays) {
                    currentTime = currentTime.plusHours(1);
    
                    if (currentTime.getHour() == 0) {
                        currentDay++;
                    }

                    clockController.updateTime(getFormattedCurrentTime(), getCurrentDay());

                } else {
                    timer.cancel();
                }
            }
        };
    
        timer.scheduleAtFixedRate(task, 0, hourDuration);
    }
    
    public void stopSimulation() {
        timer.cancel();
    }

    public String getFormattedCurrentTime() {
        return currentTime.format(formatter);
    }

    public int getCurrentDay() {
        return currentDay;
    }
}
