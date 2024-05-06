package unibo.citysimulation.controller;

import java.time.LocalTime;

import unibo.citysimulation.model.clock.ClockModel;
import unibo.citysimulation.model.clock.ClockObserver;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.view.sidePanels.ClockPanel;

public class ClockController implements ClockObserver{
    private ClockPanel clockPanel;
    private ClockModel clockModel;

    public ClockController(ClockPanel clockPanel, ClockModel clockModel) {
        this.clockPanel = clockPanel;
        this.clockModel = clockModel;

        this.clockModel.addObserver(this);
    }

    @Override
    public void onTimeUpdate(LocalTime currentTime, int currentDay) {
        
        clockPanel.setClockText("Giorno: " + currentDay + " ora: " + currentTime);
    }

    public void setClockSpeed(int speed) {
        clockModel.startSimulation(ConstantAndResourceLoader.TIME_UPDATE_RATE/speed);
    }

    public void pauseSimulation() {
        clockModel.pauseSimulation();
    }

    public void restartSimulation() {
        clockModel.restartSimulation();
    }

    
}
