package unibo.citysimulation.controller;

import java.time.LocalTime;

import unibo.citysimulation.model.clock.ClockObserver;
import unibo.citysimulation.view.sidePanels.ClockPanel;

public class ClockController implements ClockObserver{
    private ClockPanel clockPanel;

    public ClockController(ClockPanel clockPanel) {
        this.clockPanel = clockPanel;
    }

    @Override
    public void onTimeUpdate(LocalTime currentTime, int currentDay) {
        
        clockPanel.setClockText("Giorno: " + currentDay + " ora: " + currentTime);
    }
}
