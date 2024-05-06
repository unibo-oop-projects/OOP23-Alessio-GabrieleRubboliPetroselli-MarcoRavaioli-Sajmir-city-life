package unibo.citysimulation.controller;

import unibo.citysimulation.model.clock.ClockModel;

public class ClockSpeedController {
    private ClockModel clockModel;

    public ClockSpeedController(ClockModel clockModel) {
        this.clockModel = clockModel;
    }

    public void setClockSpeed(int speed) {
        clockModel.setHourDuration(speed);
    }
}
