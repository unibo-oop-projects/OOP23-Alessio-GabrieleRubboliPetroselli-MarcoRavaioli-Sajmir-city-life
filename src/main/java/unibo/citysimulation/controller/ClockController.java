package unibo.citysimulation.controller;

import java.time.LocalTime;
import java.util.Objects;

import unibo.citysimulation.model.clock.api.ClockModel;
import unibo.citysimulation.model.clock.api.ClockObserver;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.view.sidepanelsfffff.clock.ClockPanel;

/**
 * Controller class responsible for managing the clock and its interactions with the user interface.
 */
public class ClockController implements ClockObserver {
    private final ClockPanel clockPanel;
    private final ClockModel clockModel;

    /**
     * Constructs a ClockController object.
     *
     * @param clockModel The ClockModel object representing the clock.
     * @param clockPanel The ClockPanel object representing the clock user interface.
     */
    public ClockController(final ClockModel clockModel, final ClockPanel clockPanel) {
        this.clockModel = Objects.requireNonNull(clockModel, "clockModel must not be null");
        this.clockPanel = Objects.requireNonNull(clockPanel, "clockPanel must not be null");

        // Add action listener for the pause button
        clockPanel.addPauseButtonActionListener(e -> pauseSimulation());

        // Add action listener for the speed button
        clockPanel.addSpeedButtonActionListener(e -> changeClockSpeed());
    }

    /**
     * Updates the clock panel with the current time and day.
     *
     * @param currentTime The current time.
     * @param currentDay  The current day.
     */
    @Override
    public void onTimeUpdate(final LocalTime currentTime, final int currentDay) {
        // Update the clock panel text with current time and day
        clockPanel.setClockText(String.valueOf(currentDay), currentTime.toString());
    }

    /**
     * Sets the simulation speed based on the given speed value.
     */
    private void changeClockSpeed() {
        final int speed = clockPanel.changeSpeed();
        final int updateRate = ConstantAndResourceLoader.TIME_UPDATE_RATE / speed;
        // Start or update the simulation with the new speed
        if (clockModel.getTimer() != null) {
            clockModel.startSimulation(updateRate);
        } else {
            clockModel.setUpdateRate(updateRate);
        }
    }

    /**
     * Pauses the simulation.
     */
    private void pauseSimulation() {
        // Pause the simulation
        clockModel.pauseSimulation();
        clockPanel.updatePauseButton(clockModel.isPaused());
    }
}
