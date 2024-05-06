package unibo.citysimulation.controller;

import unibo.citysimulation.model.clock.ClockModel;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;

public class ClockUserController {
    private ClockModel clockModel;

    public ClockUserController(ClockModel clockModel) {
        this.clockModel = clockModel;
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
