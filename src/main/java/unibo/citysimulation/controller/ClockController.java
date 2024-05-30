package unibo.citysimulation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import unibo.citysimulation.model.clock.ClockModel;
import unibo.citysimulation.model.clock.ClockObserver;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.view.sidepanels.ClockPanel;
import unibo.citysimulation.view.sidepanels.InputPanel;

/**
 * Controller class responsible for managing the clock and its interactions with the user interface.
 */
public class ClockController implements ClockObserver {
    private ClockPanel clockPanel;
    private ClockModel clockModel;

    /**
     * Constructs a ClockController object.
     *
     * @param clockModel The ClockModel object representing the clock.
     * @param clockPanel The ClockPanel object representing the clock user interface.
     * @param inputPanel The InputPanel object representing the input user interface.
     */
    public ClockController(ClockModel clockModel, ClockPanel clockPanel, InputPanel inputPanel) {
        this.clockPanel = clockPanel;
        this.clockModel = clockModel;

        // Add action listener for the pause button
        clockPanel.getPauseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseSimulation();
            }
        });

        // Add action listener for the speed button
        clockPanel.getSpeedButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSpeed();
            }
        });

        // Add this controller as an observer of the clock model
        clockModel.addObserver(this);
    }

    /**
     * Updates the clock panel with the current time and day.
     *
     * @param currentTime The current time.
     * @param currentDay  The current day.
     */
    @Override
    public void onTimeUpdate(LocalTime currentTime, int currentDay) {
        // Update the clock panel text with current time and day
        clockPanel.setClockText(Integer.toString(currentDay), currentTime.toString());
        
    }

    /**
     * Changes the simulation speed.
     */
    private void changeSpeed() {
        // Increment the speed index and set the new speed
        changeClockSpeed();
    }

    /**
     * Sets the simulation speed based on the given speed value.
     *
     * @param speed The new simulation speed.
     */
    public void changeClockSpeed() {
        int speed = clockPanel.changeSpeed();
        // Start the simulation with the new speed
        if (clockModel.getTimer() != null) {
            clockModel.startSimulation(ConstantAndResourceLoader.TIME_UPDATE_RATE / speed);
        } else {
            clockModel.setHourDuration(ConstantAndResourceLoader.TIME_UPDATE_RATE / speed);
        }
    }

    /**
     * Pauses the simulation.
     */
    public void pauseSimulation() {
        // Pause the simulation
        clockModel.pauseSimulation();
        clockPanel.updatePauseButton(clockModel.getIsPaused());
    }
}
