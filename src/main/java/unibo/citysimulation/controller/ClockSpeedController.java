package unibo.citysimulation.controller;

import unibo.citysimulation.model.clock.ClockModel;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;

public class ClockSpeedController {
    private ClockModel clockModel;

    public ClockSpeedController(ClockModel clockModel) {
        this.clockModel = clockModel;
    }

    public void setClockSpeed(int speed) {
        clockModel.startSimulation(ConstantAndResourceLoader.TIME_UPDATE_RATE/speed);
    }
}
